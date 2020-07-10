package com.zom.factory.zfactory.generator.data;

import com.zom.factory.zfactory.generator.data.model.ZoneIdAssign;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成百万或千万级别的测试数据 ，只生成20万用户，large_zone，dcg
 * 2020年7月8日16:44:21
 *
 * @author airson
 */
public class BigDataGeneratorUser {

    /**
     * 程序运行 环境
     */
    private static boolean windows = false;

    private static boolean showCmd = false;

    /**
     * 是否执行sql脚本
     */
    private static boolean doSql = true;

    /**
     * sql脚本文件的存放目录
     */
    private static final String output_dir = windows ? "E:\\zlab\\sql2\\" : "/home/itrunk/scripts/sql/";    //"/var/scripts_zom/sql/"
    private static final String file_dir   = windows ? "E:\\zlab\\file2\\" : "/home/itrunk/scripts/file/";    //"/home/itcit/file/"
    /**
     * 数据库配置
     */
    //private static final String	dbUrl		= windows ? "localhost" : "101.201.45.198";
    private static final String dbname     = windows ? "rtv_tmp" : "rtvitrunk";
    private static final String jdbc_user  = windows ? "root" : "itrunk";
    private static       String jdbc_pswd  = windows ? "root" : "!VDDdd357!";

    // --- 数据规模配置 ---
    private static boolean sm         = true;
    private static boolean large_zone = false;

    private static final long zone_count = sm ? 30 : 255;
    private static final long corp_count = sm ? 1 : 500;
    private static final long user_count = sm ? 210000 : 2000000;    //user,token,bms_params,version_user

    /**
     * 默认值：数据的结束ID，与起始ID相减得数据的总条数
     */
    //private static final long user_id_end = user_id_start + user_count;

    /**
     * 保存255个zone的数据，新增用户时从中取出用户ID值
     */
    private static Map<Integer, ZoneIdAssign> zmap = new HashMap<>();

    private static File       file1  = null;
    private static File       file2  = null;
    private static FileWriter w1     = null;
    private static FileWriter w2     = null;
    private static String     fname  = "";
    private static String     fname2 = "";
    /**
     * 批处理文件中的命令，一般为：mysql -proot -uroot < D:\zlab\sql\rtv_xxx.sql
     */
    private static String     sqlCmd = "";
    //private static String		sqlCmd2	= "";

    private static void init() {
        File file = new File(output_dir);
        if (!file.exists()) {
            file.mkdirs();
            file.setExecutable(true);
            file.setReadable(true);
            file.setWritable(true);
        }
        file = new File(file_dir);
        if (!file.exists()) {
            file.mkdirs();
            file.setExecutable(true);
            file.setReadable(true);
            file.setWritable(true);
        }
    }

    public static void main(String[] args) {
        try {

            if (null != args && args.length > 0 && null != args[0]) {
                jdbc_pswd = args[0];
            }

            System.out.println("---------- script start ---------- ");
            init();
            rtv_zone();
            rtv_corp_zone_map();
            rtv_user();
            rtv_group();
            System.out.println("---------- sleep 40S,wait rtv_user and rtv_group done ---------- ");
            Thread.sleep(40000);
            final_operation();
            System.out.println("---------- script end ---------- ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建255个zone - 满zone - 实际使用数为：{zone_count}
     */
    public static void rtv_zone() throws Exception {
        int count = 30;
        createFile("zone_id_assign", count);
        String sql;
        int id = 1;
        while (id <= count) {
            ZoneIdAssign zone = new ZoneIdAssign();
            if (large_zone) {
                zone.setMaxUid((long) (id << 16) + 0x7FFF);
                zone.setMaxTgid((long) (id << 16) + 0xFFFF);
                zone.setCurUid((long) (id << 16) + 0);
                zone.setCurTgid((long) (id << 16) + 0x7FFF + 1);
            } else {
                zone.setMaxUid((long) ((id << 16) + 0x1FFF));
                zone.setMaxTgid((long) ((id << 16) + 0xFFFF));
                zone.setCurUid((long) ((id << 16) + 1));
                zone.setCurTgid((long) ((id << 16) + 0x2000));
            }
            zone.setName("zone" + id);
            zone.setNum(id);
            zone.setId(id);
            zmap.put(id, zone);
            sql = "(" + id + "," + id + ",'" + zone.getName() + "'," + zone.getMaxUid() + "," + zone.getMaxTgid() + "," + zone.getCurUid() + "," + zone.getCurTgid() + "),";
            if (id == count)
                sql = sql.substring(0, sql.length() - 1);
            w1.append(sql);
            id++;
        }
        closeStream();
    }

    /**
     * 每个使用的公司 关联所有使用的zone. total=corp_count*zone_count
     */
    public static void rtv_corp_zone_map() throws Exception {
        long total = corp_count * zone_count;
        createFile("rtv_corp_zone_map", total);
        long id = 1;
        for (int corpId = 1; corpId <= corp_count; corpId++) {
            for (int zoneId = 1; zoneId <= zone_count; zoneId++) {
                String sql = "(" + id + ", " + corpId + "," + zoneId + "),";
                if (id == total)
                    sql = sql.substring(0, sql.length() - 1);
                w1.append(sql);
                id++;
            }
        }
        closeStream();
    }

    /**
     * 每zone最多8000个用户，255个zone，系统最大用户数：2040000，
     * sm=true:前5个公司各占满二个zone:5*8000*2=80000,6-10的公司各占满一个zone:5*8000=40000,1-50的公司每5个公司占满一个zone(每公司1600)，共占10个zone：10*8000=80000,总共2十万个用户
     * sm=false:前50个公司各占满二个zone:50*8000*2=800000,51-100的公司各占满一个zone:50*8000=400000,1-500的公司每5个公司占满一个zone(每公司1600)，共占100个zone：100*8000=800000,总共二百万个用户
     */
    public static void rtv_user() throws Exception {
        long total = user_count;
        System.out.println(" -------- rtv_user： -------- ");
        createFile("rtv_user", total);
        long phone = 13700000000l;//XXX 通过手机号来判断是否为最后一个新增用户 phone == (sm ? 13700210000l : 137020000l)
        int zoneId = 1;

        long id = 1;
        int count = 0;
        outer:
        for (; zoneId <= zone_count; zoneId++) {
            long start_id = (zoneId << 16) + 0;
            long cur_id = start_id;
            for (; cur_id <= (start_id + 32765); cur_id++) { //32767
                String sql = "(" + cur_id + ", 'u" + cur_id + "', 'dfeddeeadfe9', NULL, 'portable', NULL, '" + (phone++) + "', '2020-07-08 18:00:00', NULL, NULL, NULL, 'offline', 'ce7e970836ad6563', 1, 1, 0, 0, " + cur_id + ", 1, '" + cur_id + "', 5, 1, 1, 0, 1, 30, 0, " + zoneId + ", 0, 0, NULL, " + cur_id + ", 'itrunk_" + cur_id + "', NULL, NULL, '1', '1', 'u" + cur_id + "', 1, 1, NULL, 0, '2020-07-08 18:00:00'),";
                if (id == total)
                    sql = sql.substring(0, sql.length() - 1);
                w1.append(sql);
                id++;

                if (++count >= total) break outer;
            }
        }

        //INSERT INTO `rtvitrunk`.`rtv_user`(`id`, `display_name`, `user_password`, `client_version`, `device`, `img_url`, `phone`, `register_date`, `last_logon_date`, `last_access_date`, `last_logon_ip`, `logon_state`, `salt`, `ts_profile`, `ts_group`, `rank`, `admin_id`, `dcg_id`, `corp_id`, `logon_name`, `priority`, `status`, `preconfig`, `default_grp`, `gps_report`, `gps_interval`, `adm_ts`, `zone_id`, `ats`, `pts`, `imei`, `lmr_uid`, `ext_did`, `extension_property`, `code`, `unit_id`, `department_id`, `original_name`, `unit_fk_id`, `department_fk_id`, `iccid`, `logon_type`, `last_update_time`, `joinlinkage`, `role_id`, `nfc`, `ext`, `create_way`, `user_role_id`, `expire_date`, `abptype`, `hardware_bind_time`, `hardware_logo`, `current_name`) VALUES
        // (65537, '张文涛', 'dfeddeeadfe9', NULL, 'portable', NULL, '11900000001', '2020-06-10 09:45:55', NULL, NULL, NULL, 'offline', 'ce7e970836ad6563', 3, 1, 0, 0, 65537, 1, '658032', 5, 1, 1, 0, 1, 30, 0, 1, 0, 0, 'imei123123', 65537, 'itrunk_65537', NULL, 'code_current_name_code', '3300000001', '3326004000', '张文涛', 1, 3, NULL, 0, '2020-07-07 15:27:56', NULL, 0, NULL, NULL, 1, NULL, NULL, 'sphone', NULL, NULL, 'current_name123123');

        //INSERT INTO `rtv_itrunk_defuser`.`rtv_group`(`id`, `group_ts`, `owner_id`, `group_name`, `create_date`, `last_update_time`, `corp_id`, `rank`, `dcg`, `preconfig`, `status`, `zone_id`, `console_dtg`, `admin_id`, `ext_tgid`, `parent_type`, `parent_id`, `priority`, `timer`, `type`, `ext`, `plan_del_time`) VALUES
        // (65537, 2, 65537, 'temp', '2019-03-25 15:17:02', '2020-06-02 10:27:01', 1, 0, 1, 1, 1, 1, 0, NULL, '', 1, NULL, 3, 30, 0, NULL, NULL);

        closeStream();
    }

    public static void rtv_group() throws Exception {
        long total = user_count;
        System.out.println(" -------- rtv_group(dcg)： -------- ");
        createFile("rtv_group2", total);
        int zoneId = 1;

        int count = 0;
        outer:
        for (; zoneId <= zone_count; zoneId++) {
            long start_id = (zoneId << 16) + 0;
            long cur_id = start_id;
            for (; cur_id <= (start_id + 32765); cur_id++) { //32767
                String sql = "(" + cur_id + ", 1, " + cur_id + ", 'temp', '2020-07-08 18:00:00', 1, 0, 1, 1, 1, " + zoneId + ", 0, NULL, '', 1, NULL, 3),";
                ++count;
                /*if (count >= total) {
                    sql = sql.substring(0, sql.length() - 1);
                }*/ // 下面还要新增数据，这里不去除逗号
                w1.append(sql);
                if (count >= total) {
                    break outer;
                }
            }
        }

        // XXX 每个zone再创建3000个组，不加人 2020年7月10日11:59:0
        total = 30 * 3000;
        zoneId = 1;
        count = 0;
        outer:
        for (; zoneId <= zone_count; zoneId++) {
            long large_id = (zoneId << 16) + 0x7FFF + 1;
            long small_id = (zoneId << 16) + 0x2000;
            long start_id = large_zone ? large_id : small_id;
            long cur_id = start_id;
            for (; cur_id <= (start_id + 3000); cur_id++) {
                String sql = "(" + cur_id + ", 1, 0, 'tg_" + cur_id + "', '2020-07-10 13:00:00', 1, 0, 0, 1, " + zoneId + ", 1, 0, NULL, '', 1, NULL, 3),";
                ++count;
                if (count >= total) {
                    sql = sql.substring(0, sql.length() - 1);
                }
                w1.append(sql);
                if (count >= total) {
                    break outer;
                }
            }
        }


        //INSERT INTO `rtvitrunk`.`rtv_user`(`id`, `display_name`, `user_password`, `client_version`, `device`, `img_url`, `phone`, `register_date`, `last_logon_date`, `last_access_date`, `last_logon_ip`, `logon_state`, `salt`, `ts_profile`, `ts_group`, `rank`, `admin_id`, `dcg_id`, `corp_id`, `logon_name`, `priority`, `status`, `preconfig`, `default_grp`, `gps_report`, `gps_interval`, `adm_ts`, `zone_id`, `ats`, `pts`, `imei`, `lmr_uid`, `ext_did`, `extension_property`, `code`, `unit_id`, `department_id`, `original_name`, `unit_fk_id`, `department_fk_id`, `iccid`, `logon_type`, `last_update_time`, `joinlinkage`, `role_id`, `nfc`, `ext`, `create_way`, `user_role_id`, `expire_date`, `abptype`, `hardware_bind_time`, `hardware_logo`, `current_name`) VALUES
        // (65537, '张文涛', 'dfeddeeadfe9', NULL, 'portable', NULL, '11900000001', '2020-06-10 09:45:55', NULL, NULL, NULL, 'offline', 'ce7e970836ad6563', 3, 1, 0, 0, 65537, 1, '658032', 5, 1, 1, 0, 1, 30, 0, 1, 0, 0, 'imei123123', 65537, 'itrunk_65537', NULL, 'code_current_name_code', '3300000001', '3326004000', '张文涛', 1, 3, NULL, 0, '2020-07-07 15:27:56', NULL, 0, NULL, NULL, 1, NULL, NULL, 'sphone', NULL, NULL, 'current_name123123');

        //INSERT INTO `rtv_itrunk_defuser`.`rtv_group`(`id`, `group_ts`, `owner_id`, `group_name`, `create_date`, `last_update_time`, `corp_id`, `rank`, `dcg`, `preconfig`, `status`, `zone_id`, `console_dtg`, `admin_id`, `ext_tgid`, `parent_type`, `parent_id`, `priority`, `timer`, `type`, `ext`, `plan_del_time`) VALUES
        // (65537, 2, 65537, 'temp', '2019-03-25 15:17:02', '2020-06-02 10:27:01', 1, 0, 1, 1, 1, 1, 0, NULL, '', 1, NULL, 3, 30, 0, NULL, NULL);

        closeStream();
    }

    private static void createFile2(String table, long total) throws Exception {
        System.out.println(" ---------- " + table + " ---------- ");
        fname2 = output_dir + table + ".sql";
        System.out.println("生成文件开始：" + fname2 + ", total：" + total);
        file2 = new File(fname2);
        if (!file2.exists()) {
            file2.createNewFile();
            file2.setExecutable(true);
            file2.setReadable(true);
            file2.setWritable(true);
        }
        w2 = new FileWriter(file2);
        w2.append(buildPrefixSql(table));
        //sqlCmd2 = "mysql -proot -uroot < " + fname2;
    }

    public static void final_operation() throws Exception {
        createFile("final_operation", 2, false);
        String sql_10 = "USE " + dbname + ";UPDATE zone_id_assign z SET z.cur_uid  = IF((SELECT u.id FROM rtv_user  u where u.zone_id = z.id ORDER BY u.id DESC LIMIT 0,1),(SELECT u.id FROM rtv_user  u where u.zone_id = z.id ORDER BY u.id DESC LIMIT 0,1),z.cur_uid);";
        String sql_20 = "USE " + dbname + ";UPDATE zone_id_assign z SET z.cur_tgid = IF((SELECT g.id FROM rtv_group g where g.zone_id = z.id ORDER BY g.id DESC LIMIT 0,1),(SELECT g.id FROM rtv_group g where g.zone_id = z.id ORDER BY g.id DESC LIMIT 0,1),z.cur_tgid);";
        String sql_30 = "";
        String sql = sql_10 + sql_20 + sql_30;
        w1.append(sql);
        closeStream();
    }

    private static void createFile(String table, long total) throws Exception {
        createFile(table, total, true);
    }

    private static void createFile(String table, long total, boolean need_prefix) throws Exception {
        System.out.println(" ---------- " + table + " ---------- ");
        fname = output_dir + table + ".sql";
        System.out.println("createFile start : " + fname);// + ", total : " + total
        file1 = new File(fname);
        if (!file1.exists()) {
            file1.createNewFile();
            file1.setExecutable(true);
            file1.setReadable(true);
            file1.setWritable(true);
        }
        w1 = new FileWriter(file1);
        if (need_prefix) {
            w1.write(buildPrefixSql(table));
        }
        sqlCmd = "mysql -p" + jdbc_pswd + " -u" + jdbc_user + " < " + fname;
    }

    private static String buildPrefixSql(String table) {
        return "USE " + dbname + ";TRUNCATE TABLE `" + table + "`;INSERT INTO `" + table + "` VALUES ";
    }

    private static void closeStream() throws Exception {
        System.out.println("createFile end : " + fname);
        w1.flush();
        w1.close();
        if (doSql)
            executeSql(fname);//执行sql脚本文件
    }

    private static void closeStream2() throws Exception {
        System.out.println("生成文件结束：" + fname2);
        w2.flush();
        w2.close();
        if (doSql)
            executeSql(fname2);//执行sql脚本文件
    }

    private static void executeSql(String fname) throws Exception {
        fname = fname.replace(".sql", windows ? ".bat" : ".sh");
        file1 = new File(fname);
        if (!file1.exists()) {
            file1.createNewFile();
            file1.setExecutable(true);
            file1.setReadable(true);
            file1.setWritable(true);
        }
        FileWriter w = new FileWriter(file1);
        w.append(sqlCmd);
        w.flush();
        w.close();
        System.out.println("cmd content : " + sqlCmd);
        String cmd_pre = showCmd ? "cmd.exe /c start " : "cmd.exe /c ";
        String cmd = windows ? (cmd_pre + fname) : fname;
        System.out.println("execute cmd : " + cmd);
        Runtime.getRuntime().exec(cmd).waitFor();

        /** cmd /c dir 是执行完dir命令后封闭命令窗口。
         cmd /k dir 是执行完dir命令后不封闭命令窗口。
         cmd /c start dir 会打开一个新窗口后执行dir指令，原窗口会封闭。
         cmd /k start dir 会打开一个新窗口后执行dir指令，原窗口不会封闭。
         */
    }

}
