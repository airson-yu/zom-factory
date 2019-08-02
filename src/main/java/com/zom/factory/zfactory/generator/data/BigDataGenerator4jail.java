package com.zom.factory.zfactory.generator.data;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.zom.factory.zfactory.generator.data.model.CorpUser;
import com.zom.factory.zfactory.generator.data.model.ZoneIdAssign;

/**
 * 生成百万或千万级别的测试数据
 *
 * @author airson
 */
public class BigDataGenerator4jail {

    //数据库应该初始化的数据 : rtv_plan_event, rtv_plan_action

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
    private static final String output_dir = windows ? "E:\\zlab\\sql\\" : "/home/itrunk/scripts_zom/sql/";    //"/var/scripts_zom/sql/"
    private static final String file_dir   = windows ? "E:\\zlab\\file\\" : "/home/itrunk/scripts_zom/file/";    //"/home/itcit/file/"
    /**
     * 数据库配置
     */
    //private static final String	dbUrl		= windows ? "localhost" : "101.201.45.198";
    private static final String dbname     = windows ? "rtvitrunk" : "rtvitrunk";
    private static final String jdbc_user  = windows ? "root" : "root";
    private static final String jdbc_pswd  = windows ? "root" : "Xyz@Itr132!";

    /** 默认值 : 数据的起始ID */
    //private static final long user_id_start = 65537;
    /**
     * 默认值 : 数据的结束ID，与起始ID相减得数据的总条数
     */
    //private static final long user_id_end = user_id_start + user_count;

    // --- 数据规模配置 ---
    private static boolean sm = true;

    private static final long zone_count  = sm ? 38 : 255;
    private static final long corp_count  = sm ? 1 : 500;
    private static final long user_count  = sm ? 57000 : 2000000;    //user,token,bms_params,version_user
    private static final long group_count = sm ? 51 : 2000000;
    private static final long media_count = sm ? 50000 : 5000000;    //video,picture,gps_info
	/*private static final long	domain_count	= sm ? 200000 : 5000000;	//plan_rule,operation_log,dlmsg,ulmsg
	private static final long	fence_count		= sm ? 200000 : 5000000;	//fence
	private static final long	nvr_count		= sm ? 200000 : 5000000;
	private static final long	cameral_count	= sm ? 200000 : 5000000;
	private static final long	version_count	= sm ? 2000 : 50000;*/

    /**
     * 保存255个zone的数据，新增用户时从中取出用户ID值
     */
    private static Map<Integer, ZoneIdAssign> zmap = new HashMap<Integer, ZoneIdAssign>();

    private static Map<Long, CorpUser> corp_user_map = new HashMap<Long, CorpUser>();

    private static File       file1  = null;
    private static FileWriter w1     = null;
    private static String     fname  = "";
    /**
     * 批处理文件中的命令，一般为 : mysql -proot -uroot < D:\zlab\sql\rtv_xxx.sql
     */
    private static String     sqlCmd = "";

    private static boolean baseDataInsert = true;

    private static void init() {
        File file = new File(output_dir);
        if (!file.exists()) {
            file.mkdir();
            file.setExecutable(true);
            file.setReadable(true);
            file.setWritable(true);
        }
        file = new File(file_dir);
        if (!file.exists()) {
            file.mkdir();
            file.setExecutable(true);
            file.setReadable(true);
            file.setWritable(true);
        }
    }

    public static void main(String[] args) throws Exception {
        init();

		/*//step
		baseDataInsert = false;
		rtv_zone();//只缓存数据
		rtv_user();//只缓存数据
		rtv_plan_rule_user_map();
		if (2 > 1) {
			System.out.println("---------- over over over ---------- ");
			return;
		}*/

        if (null == args || args.length <= 0 || (null != args[0] && !"domain".equals(args[0]))) {
            //step
            rtv_zone();
            rtv_corp();
            //rtv_system_param();
            rtv_3rdkey();
            rtv_corp_zone_map();
            rtv_user();
            rtv_user_extension();

            //if (2 > 1)return;

            rtv_user_admin_mapping();
            rtv_console_cop_mapping();
            rtv_bms_params();
            rtv_group();
            rtv_group_dmr();
            rtv_user_group_map();
            rtv_console_user();
            rtv_sub_admin();
            rtv_cu_zone_map();
            rtv_console_user_mapping();

            //step
			/*baseDataInsert = false;
			rtv_zone();//只缓存数据
			rtv_user();//只缓存数据
			*/
            rtv_nvr();
            rtv_external_camera();
            rtv_fence();
            rtv_version_map();
            rtv_version_user_map();
            rtv_plan_rule();
            rtv_plan_rule_user_map();

            rtv_operation_log();

            //step
			/*baseDataInsert = false;
			rtv_zone();//只缓存数据
			rtv_user();//只缓存数据
			*/

            System.out.println("---------- final_operation begin ---------- ");
            final_operation();

        } else {
            baseDataInsert = false;
            rtv_zone();//只缓存数据
            rtv_user();//只缓存数据
            baseDataInsert = true;
        }

		/*String folder = file_dir + "rtpictures" + File.separator + "2017-10-26" + File.separator;
		File file = new File(folder);
		if (!file.exists()) {
			file.mkdir();
		}
		initFile(folder, "hd_", ".jpg", 1, 200000);
		
		folder = file_dir + "video" + File.separator + "2017-10-26" + File.separator;
		file = new File(folder);
		if (!file.exists()) {
			file.mkdir();
		}
		initFile(folder, "", ".mp4", 1, 200000);*/

        System.out.println("---------- domain table begin ---------- ");
        rtv_token();
        rtv_user_login();
        rtv_rtpictures();
        rtv_video();
        rtv_gps_info();
        rtv_dlmsg_storage();
        rtv_ulmsg_storage();

        System.out.println("---------- over over over ---------- ");

    }

    public static void final_operation() throws Exception {
        createFile("final_operation", 2, false);
        String sql_10 = "UPDATE zone_id_assign z SET z.cur_uid  = IF((SELECT u.id FROM rtv_user  u where u.zone_id = z.id ORDER BY u.id DESC LIMIT 0,1),(SELECT u.id FROM rtv_user  u where u.zone_id = z.id ORDER BY u.id DESC LIMIT 0,1),z.cur_uid);";
        String sql_20 = "UPDATE zone_id_assign z SET z.cur_tgid = IF((SELECT g.id FROM rtv_group g where g.zone_id = z.id ORDER BY g.id DESC LIMIT 0,1),(SELECT g.id FROM rtv_group g where g.zone_id = z.id ORDER BY g.id DESC LIMIT 0,1),z.cur_tgid);";
        String sql_30 = "";
        String sql = sql_10 + sql_20 + sql_30;
        w1.append(sql);
        closeStream();
    }

    //seq:zone,corp,corp_zone,user(each_zone_user,each_zone_console),token,user_admin(上下级关系),bms_params, *console_cop_mapping
    //group,user_group,nvr,camera,fence,plan_rule,console_user,picture,video,gps,ulmsg,dlmsg

    //zone_user,zone_console

    /**
     * 创建255个zone - 满zone - 实际使用数为 : {zone_count}
     */
    public static void rtv_zone() throws Exception {
        if (baseDataInsert) {
            createFile("zone_id_assign", 255);
        }
        String sql;
        int id = 1;
        while (id <= 255) {
            ZoneIdAssign zone = new ZoneIdAssign();
            zone.setMaxUid((long) ((id << 16) + 0x1FFF));
            zone.setMaxTgid((long) ((id << 16) + 0xFFFF));
            zone.setCurUid((long) ((id << 16) + 1));
            zone.setCurTgid((long) ((id << 16) + 0x2000));
            zone.setName("zone" + id);
            zone.setNum(id);
            zone.setId(id);
            zmap.put(id, zone);
            if (baseDataInsert) {
                sql = "(" + id + "," + id + ",'" + zone.getName() + "'," + zone.getMaxUid() + "," + zone.getMaxTgid() + "," + zone.getCurUid() + "," + zone.getCurTgid() + "),";
                if (id == 255)
                    sql = sql.substring(0, sql.length() - 1);
                w1.append(sql);
            }
            id++;
        }
        if (baseDataInsert) {
            closeStream();
        }
    }

    /**
     * 创建1000个公司， - 实际使用数为 : {corp_count}
     */
    public static void rtv_corp() throws Exception {
        createFile("rtv_corp_table", 1);
        long id = 1;
        long phone = 12700000000l;
        while (id <= 1) {
            String name = "corp" + id;
            if (id == 1) {
                name = "admin";
            }
            String sql_10 = "(" + id + ", '" + name + "', 'corpname" + id + "', '7488e331b8b64e5794da3fa4eb10ad5d', 'corp" + id + "@01more.com', '" + (phone++)
                    + "','2017-01-01 13:30:30', '2050-12-31 00:00:00', '2017-10-01 14:40:40', '182.160.45.01'";
            String sql_20 = ",2,1,100000,100000,100000,100000,1,1,1,0";
            String sql_30 = ",1,30,0,0,0,5,'{\"camera\":1}'),";
            String sql = sql_10 + sql_20 + sql_30;
            if (id == 1)
                sql = sql.substring(0, sql.length() - 1);
            w1.append(sql);
            id++;
        }
        closeStream();
        //INSERT INTO `rtv_test_temp`.`rtv_corp_table` (`id`, `username`, `corp_name`, `corp_password`, `email`, `phone`, `register_date`, `expire_date`, `last_logon_date`, `last_logon_ip`,
        //`permission_level`, `priority_level`, `max_user`, `max_group`, `max_console`, `max_user_group`, `status`, `imeichk`, `sh_enable`, `asrecord`,
        //`gps_report`, `gps_interval`, `imei_bind`, `door_control`, `catt_modle`,`video_push_num`, `params`)
        //VALUES ('1', 'corp01', 'corp_name_01', '4297f44b13955235245b2497399d7a93', 'corp01@163.com', '12345678901', '2016-01-01 13:30:30', '2017-01-01 13:30:30', '2016-01-01 14:40:40', '182.160.45.01',
        //'1', '1', '500', '500', '500', '500', '1', '1', '1', '0',
        //'1', '30', '0', '0', '0', '5', '{\"camera\":1}');

    }

    /**
     * 每个公司一条license
     */
    public static void rtv_system_param() throws Exception {
        createFile("rtv_system_param", 1);
        long id = 1;
        long phone = 11700000000l;
        while (id <= 1) {
            String name = "corp" + id;
            if (id == 1) {
                name = "admin";
            }
            String sql_10 = "(" + id + ", '" + name + "', 'corp" + id + "@01more.com', '" + (phone++)
                    + "', '2050-12-31 00:00:00', '100000000', '100000000', '100000000', '100000000', '1', '1', '50000', '1', '1'";
            String sql_20 = ",'{\"camera_license\":1,\"corpn\":" + id + ",\"dmr_license\":1,\"mcc\":460,\"mnc\":1,\"seller_name\":\"成都零壹众科技有限公司\",\"tech_provider\":\"成都零壹众科技有限公司\"}',NULL),";
            String sql = sql_10 + sql_20;
            if (id == 1)
                sql = sql.substring(0, sql.length() - 1);
            w1.append(sql);
            id++;
        }
        closeStream();
        //INSERT INTO `rtvitc`.`rtv_system_param` (`id`, `system_name`, `email`, `phone`, `expired_date`, `max_user`, `max_group`, `max_console`, `max_user_group`, `status`,
        //										   `gps_license`, `video_push_num`, `dc_license`, `sh_license`, `params`)
        //VALUES ('1', 'ZOMITCIT2', 'williamyang7@163.com', '18615707585', '2050-12-31 00:00:00', '18000', '50000', '1500', '18000', '1', '1', '500', '1', '1', '{\"camera_license\":1,\"corpn\":2002,\"dmr_license\":1,\"mcc\":460,\"mnc\":1,\"seller_name\":\"成都零壹众科技有限公司\",\"tech_provider\":\"成都零壹众科技有限公司\"}');

    }

    /**
     * 创建255个zone - 满zone - 实际使用数为 : {zone_count}
     */
    public static void rtv_3rdkey() throws Exception {
        createFile("rtv_3rdkey", 255);
        for (int corpId = 1; corpId <= corp_count; corpId++) {
            String sql = "";
            sql += "(NULL, 'create_device-" + corpId + "', '654321', " + corpId + ", NULL, '{\"pre\":\"100\"}'),";
            sql += "(NULL, 'del_device-" + corpId + "', '654321', " + corpId + ", NULL, NULL),";
            sql += "(NULL, 'update_device-" + corpId + "', '654321', " + corpId + ", NULL, NULL),";
            sql += "(NULL, 'create_tg-" + corpId + "', '654321', " + corpId + ", NULL, NULL),";
            sql += "(NULL, 'delete_tg-" + corpId + "', '654321', " + corpId + ", NULL, NULL),";
            sql += "(NULL, 'update_tg_info-" + corpId + "', '654321', " + corpId + ", NULL, NULL),";
            sql += "(NULL, 'update_tg_mem-" + corpId + "', '654321', " + corpId + ", NULL, NULL),";
            sql += "(NULL, 'update_device_tg-" + corpId + "', '654321', " + corpId + ", NULL, NULL),";
            sql += "(NULL, 'distribute_work_order-" + corpId + "', '654321', " + corpId + ", NULL, NULL),";
            sql += "(NULL, 'delete_work_order-" + corpId + "', '654321', " + corpId + ", NULL, NULL),";
            sql += "(NULL, 'inform_new_video-" + corpId + "', '654321', " + corpId + ", NULL, NULL),";
            sql += "(NULL, 'delete_video-" + corpId + "', '654321', " + corpId + ", NULL, NULL),";
            w1.append(sql);
        }

        String sql = "" + "(0, 0, 0, 0, NULL, NULL)";
        w1.append(sql);
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
     * 每zone最多8000个用户，255个zone，系统最大用户数 : 2040000，
     * sm=true:每个zone都有1500 个用户，总共57000个用户
     * 1. 38个zone.
     * 2. 每个zone都有1500 个用户，总共57000个用户
     * 3. 每个zone 个5个一阶调度台， 总共180个一阶调度台。二阶调度台采用手动配置，不做数据预置。
     * 4. 每个zone 都有一个大组，包括了这个zone的所有成员。
     * 5. 每个zone 在另设50 个组，随机的包括成员，每个组的成员不超过40个人
     * sm=false:前50个公司各占满二个zone:50*8000*2=800000,51-100的公司各占满一个zone:50*8000=400000,1-500的公司每5个公司占满一个zone(每公司1600)，共占100个zone : 100*8000=800000,总共二百万个用户
     */
    public static void rtv_user() throws Exception {

        //新增rtv_user_admin时从缓存中读取userId相关数据
        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = new CorpUser();
            corp_user_map.put(cacheCorp, cu);
        }

        long total = user_count;
        if (baseDataInsert) {
            createFile("rtv_user", total);
        }
        long phone = 13700000000l;//XXX 通过手机号来判断是否为最后一个新增用户 phone == (sm ? 13700200000l : 13702000000l)

        //独占一个zone的公司
        for (long oneZoneCorpId = 1; oneZoneCorpId <= 1; oneZoneCorpId++) {
            System.out.println("one zone corp id : " + oneZoneCorpId + ", phone : " + phone);

            for (int zoneId = 1; zoneId <= zone_count; zoneId++) {
                ZoneIdAssign zone = zmap.get(zoneId);
                phone = insertUserByTurn(phone, 1, zoneId, zone, true);
            }

        }

        if (baseDataInsert) {
            closeStream();
        }
    }

    private static long insertUserByTurn(long phone, long corpId, long zoneId, ZoneIdAssign zone, boolean shareZone) throws Exception {
        String dname = null;
        String lname = null;
        int turnIdx = 1;
        //每个zone8000个用户
        int total = shareZone ? 1505 : 8000;
        long userId = zone.getCurUid();
        zone.setCurUid(userId + 1);
        long turnUserIdStart = userId;
        System.out.println("turnUserIdStart : " + turnUserIdStart + ", userId : " + userId + ", zoneId : " + zoneId);
        CorpUser corpUser = corp_user_map.get(corpId);
        for (int userCount = 1; userCount <= total; userCount++) {
            dname = "bms" + userId;
            lname = "u" + userId;
            int rank = 0;
            int priority = 3;
            if (turnIdx > 1505) {//每1505个用户为一轮，每一轮中1500个用户，5个console(全部为1阶，高阶手动创建)
                turnIdx = 1;
                turnUserIdStart = userId;
                System.out.println("consoleIdStart : " + turnUserIdStart + ", userId : " + userId + ", zoneId : " + zoneId);
            }
            if (turnIdx > 1500) {//console
                rank = 1;
                priority = 11;
                dname = "con_" + rank + "_" + userId;
                System.out.println("consoleIdStart : dname : " + dname);
                corpUser.getCon1List().add(userId);
                corpUser.getConsoleZoneMap().put(userId, zoneId);//缓存调度台和ZONE的关联
                zmap.get((int) zoneId).getCon1List().add(userId);
            } else {//用户
                corpUser.getUserList().add(userId);
                corpUser.getUserZoneMap().put(userId, zoneId);
                zmap.get((int) zoneId).getUserList().add(userId);
            }

            //INSERT INTO `rtvitc`.`rtv_user` (`id`, `display_name`, `user_password`, `client_version`, `device`, `img_url`, `phone`, `register_date`, `last_logon_date`, `last_access_date`,
            //`last_logon_ip`, `logon_state`, `salt`, `ts_profile`, `ts_group`, `rank`, `admin_id`, `dcg_id`, `corp_id`, `logon_name`,
            //`priority`, `status`, `preconfig`, `default_grp`, `gps_report`, `gps_interval`, `adm_ts`, `zone_id`, `ats`, `pts`,
            //`imei`, `lmr_uid`, `ext_did`,extension_property,code,unit_id,department_id)
            //VALUES ('65537', 'u1', 'df', '2.5.1', 'web', NULL, '19900000001', '2016-05-30 14:04:31', '2017-08-29 17:49:19', '2017-08-29 17:49:19',
            //NULL, 'online', 'c996f03e8c8f4c09', '6', '4', '0', '0', '65537', '1', 'u11',
            //'1', '1', '1', '0', '1', '30', '0', '1', '0', '0',
            //'', NULL, NULL);
            if (baseDataInsert) {
                String sql_10 = "(" + userId + ",'" + dname + "','df',NULL,'portable',NULL,'" + (phone++) + "','2017-09-30 14:16:00','2017-09-30 14:16:00',NULL";
                String sql_20 = ",NULL,'online','245956046a76a3a8',100,100," + rank + ",0," + userId + "," + corpId + ",'" + lname;
                String sql_30 = "'," + priority + ",1,1,0,1,30,0," + zoneId + ",0,0,";
                String sql_40 = "'',NULL," + userId + ",'{\"oc\":" + (userId % 2) + "}'," + userId + ",'0','0'),";//oc:0,1
                String sql = sql_10 + sql_20 + sql_30 + sql_40;
                if (phone == (sm ? 13700057190l : 13702000000l)) {//XXX 通过手机号来判断是否为最后一个新增用户
                    sql = sql.substring(0, sql.length() - 1);
                    System.out.println("share zone corp id : " + corpId + ", phone : " + phone);
                }
                w1.append(sql);
            }
            zone.setCurUid(++userId);
            turnIdx++;
        }
        return phone;
    }

    /**
     * rtv_user_extension
     */
    public static void rtv_user_extension() throws Exception {

        createFile("rtv_user_extension", user_count);

        long phone = 14700000000l;

        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();

            //每个用户一条数据
            for (Long userId : userList) {
                String sql = "(NULL, " + userId + "," + userId + "," + userId + "," + phone + ",NULL,NULL,NULL,NULL,NULL,NULL,NULL),";
                w1.append(sql);
                phone++;
            }
        }

        String sql = "(0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)";//for : 去除最后一个逗号，没有实际业务意义
        w1.append(sql);

        closeStream();

    }

    /**
     * rtv_user_login
     */
    public static void rtv_user_login() throws Exception {

        createFile("rtv_user_login", user_count);

        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();

            //每个用户一条数据
            for (Long userId : userList) {
                String sql = "(NULL, " + userId + ",'2018-01-04 14:24:47','2018-01-04 14:24:47',NULL,'online'),";
                w1.append(sql);
            }
        }

        String sql = "(0,0,NULL,NULL,NULL,'offline')";//for : 去除最后一个逗号，没有实际业务意义
        w1.append(sql);

        closeStream();

    }

    /**
     * sm=true:前5个公司各占满二个zone:5*8000*2=80000,6-10的公司各占满一个zone:5*8000=40000,1-50的公司每5个公司占满一个zone(每公司1600)，共占10个zone : 10*8000=80000,总共2十万个用户
     * sm=false:前50个公司各占满二个zone:50*8000*2=800000,51-100的公司各占满一个zone:50*8000=400000,1-500的公司每5个公司占满一个zone(每公司1600)，共占100个zone : 100*8000=800000,总共二百万个用户
     */
    public static void rtv_user_admin_mapping() throws Exception {

        createFile("rtv_user_admin_mapping", user_count);

        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            //List<Long> con1List = cu.getCon1List();

            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            int userSize = userList.size();
            //int con1Size = con1List.size();

            System.out.println("corpId : " + cacheCorp + ", userSize : " + userSize + ", conAllList : " + conAllList.size());

            //zone的所有用户加入所有调度台
            for (int idx = 1; idx <= zone_count; idx++) {
                List<Long> zUserList = zmap.get(idx).getUserList();
                List<Long> zCon1List = zmap.get(idx).getCon1List();
                for (long userId : zUserList) {
                    for (long con : zCon1List) {
                        String sql = "(NULL, " + userId + "," + con + "),";
                        w1.append(sql);
                    }
                }

            }
        }
        String sql = "(NULL,1,1)";//for : 去除最后一个逗号，没有实际业务意义
        w1.append(sql);
        closeStream();
    }

    public static void rtv_console_cop_mapping() throws Exception {
        createFile("rtv_console_cop_mapping", user_count);
        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            int count = conAllList.size();
            for (int index = 0; index < count; index++) {
                long userId = conAllList.get(index);
                String sql = "(NULL," + cacheCorp + "," + userId + "),";
                if (cacheCorp == corp_count && index == count - 1)
                    sql = sql.substring(0, sql.length() - 1);
                w1.append(sql);
            }
        }
        closeStream();
    }

    /**
     * 每个用户一个配置,每个公司一个配置，整个系统一个默认配置：uid =0 server params, uid <65537 is for org, uid >=65537 is for uid
     */
    public static void rtv_bms_params() throws Exception {
        createFile("rtv_bms_params", user_count);

        String params = "{\"basic_bms_pri_12\":1073741825,\"door_ctrl_5\":1073741825,\"gps_interval_2\":\"1073741854\",\"gps_query_3\":1073741825,\"gps_report_1\":1073741825,\"imei_validate_4\":1073741825,\"new_tg_pri_11\":1073741825,\"ptt_lock_pri_13\":1073741825,\"secure_desktop_6\":1073741825,\"sms_upload_10\":1073741825,\"tg_owner_pri_14\":1073741825,\"video_upload_21\":1073741825,\"visible_attach_7\":1073741825,\"vpull_type_15\":1073741826,\"vstorage_20\":1073741826,\"cc_22\":0,\"power_23\":0,\"emeg_flwup_26\":1073741824,\"rdkp_duration_27\":1073741829}";

        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            String sql_corp = "(" + cacheCorp + "," + cacheCorp + ", " + cacheCorp + ", 100, '" + params + "'),";
            w1.append(sql_corp);

            for (long userId : userList) {
                String sql = "(" + userId + "," + userId + ", " + cacheCorp + ", 100, '" + params + "'),";
                w1.append(sql);
            }
            for (long userId : conAllList) {
                String sql = "(" + userId + "," + userId + ", " + cacheCorp + ", 100, '" + params + "'),";
                w1.append(sql);
            }
        }
        String sql = "(0,0,0,1,'" + params + "')";//default params
        w1.append(sql);
        closeStream();
    }

    /**
     * 每个用户一个token
     */
    public static void rtv_token() throws Exception {
        createFile("rtv_token", user_count);

        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            for (long userId : userList) {
                String sql = "(" + userId + ", 'ec25339660da4b66907a61047a0e3d4user" + userId + "', " + userId + ", '2018-01-10 18:01:37', '2018-01-10 18:01:37', '2301-10-10 18:01:37'),";
                w1.append(sql);
            }
            for (long userId : conAllList) {
                String sql = "(" + userId + ", 'ec25339660da4b66907a61047a0e3d43con" + userId + "', " + userId + ", '2018-01-10 18:01:37', '2018-01-10 18:01:37', '2301-10-10 18:01:37'),";
                w1.append(sql);
            }
        }

        String sql = "(0, '0', 0, '2018-01-10 18:01:37', '2018-01-10 18:01:37', '2301-10-10 18:01:37')";
        w1.append(sql);
        closeStream();
    }

    /**
     * 用户组
     */
    public static void rtv_group() throws Exception {
        createFile("rtv_group", group_count);
        String gname = null;

        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
			/*CorpUser cu = corp_user_map.get(cacheCorp);
			List<Long> userList = cu.getUserList();
			List<Long> conAllList = cu.getConAllList();//所有1-7阶
			*/
            // 每个zone 都有一个大组，包括了这个zone的所有成员。
            for (int idx = 1; idx <= zone_count; idx++) {
                int zoneId = idx;
                gname = "g" + zoneId;
                List<Long> zUserList = zmap.get(idx).getUserList();

                ZoneIdAssign zone = zmap.get(zoneId);
                Long tgId = zone.getCurTgid();
                zone.setCurTgid(tgId + 1);

                Long userId = zUserList.get(0);
                String sql = "(" + tgId + ",100," + userId + ",'" + gname + "', '2018-01-10 14:04:32', " + cacheCorp + ",0,0,1,1," + zoneId + ",0,NULL,NULL),";
                w1.append(sql);

                zmap.get(idx).getGroupList().add(tgId);
            }

            // 每个zone 在另设50 个组，随机的包括成员，每个组的成员不超过40个人
            for (int idx = 1; idx <= zone_count; idx++) {
                int zoneId = idx;
                List<Long> zUserList = zmap.get(idx).getUserList();

                ZoneIdAssign zone = zmap.get(zoneId);

                for (int j = 1; j <= 50; j++) {
                    gname = "g" + zoneId + "g" + j;
                    Long userId = zUserList.get(j);

                    Long tgId = zone.getCurTgid();
                    zone.setCurTgid(tgId + 1);

                    String sql = "(" + tgId + ",100," + userId + ",'" + gname + "', '2018-01-10 14:04:32', " + cacheCorp + ",0,0,1,1," + zoneId + ",0,NULL,NULL),";
                    w1.append(sql);

                    zmap.get(idx).getGroupList().add(tgId);
                }
            }
        }

        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            //console dtg group : (common group : console default group)
            for (long userId : conAllList) {
                gname = "condtg" + userId;
                long zoneId = corp_user_map.get(cacheCorp).getConsoleZoneMap().get(userId);

                ZoneIdAssign zone = zmap.get((int) zoneId);
                Long tgId = zone.getCurTgid();
                zone.setCurTgid(tgId + 1);

                String sql = "(" + tgId + ",100," + userId + ",'" + gname + "', '2018-01-12 09:13:17', " + cacheCorp + ",1,0,1,1," + zoneId + ",1,NULL,NULL),";
                w1.append(sql);
            }

            //dcg group for console
            for (long userId : conAllList) {
                gname = "condcg" + userId;
                long zoneId = corp_user_map.get(cacheCorp).getConsoleZoneMap().get(userId);

                String sql = "(" + userId + ",100," + userId + ",'" + gname + "', '2018-01-12 09:13:17', " + cacheCorp + ",1,1,1,1," + zoneId + ",0,NULL,NULL),";
                w1.append(sql);
            }

            //dcg group for user
            for (long userId : userList) {
                gname = "bmsdcg" + userId;
                long zoneId = corp_user_map.get(cacheCorp).getUserZoneMap().get(userId);

                String sql = "(" + userId + ",100," + userId + ",'" + gname + "', '2018-01-12 09:13:17', " + cacheCorp + ",0,1,1,1," + zoneId + ",0,NULL,NULL),";
                w1.append(sql);
            }
        }
        //INSERT INTO `rtvitc`.`rtv_group` (`id`, `group_ts`, `owner_id`, `group_name`, `create_date`, `corp_id`, `rank`, `dcg`, `preconfig`, `status`, `zone_id`, `console_dtg`, `admin_id`, `ext_tgid`)
        //VALUES ('65537', '2', '65537', 'dcg1', '2016-05-30 14:04:32', '1', '0', '1', '1', '1', '1', '0', '0', NULL);

        String sql = "(0,1,1,'default', '2017-11-11 11:11:11',0,1,1,1,1,0,0,NULL,NULL)";//for : 去掉逗号
        w1.append(sql);
        closeStream();
    }

    /**
     * group_dmr
     */
    public static void rtv_group_dmr() throws Exception {
        createFile("rtv_group_dmr", user_count);

        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
			/*CorpUser cu = corp_user_map.get(cacheCorp);
			List<Long> userList = cu.getUserList();
			List<Long> conAllList = cu.getConAllList();//所有1-7阶
			*/
            for (int idx = 1; idx <= zone_count; idx++) {
                List<Long> zGroupList = zmap.get(idx).getGroupList();
                for (Long gid : zGroupList) {
                    String sql = "(NULL, " + gid + "," + gid + ", '0', '409.025', '409.025', '409.025', '409.025', '0', '1', '49', NULL, '0', '15', '1', '2', '4'),";
                    w1.append(sql);
                }
            }

        }

        String sql = "(0,0,0, '0', '409.025', '409.025', '409.025', '409.025', '0', '1', '49', NULL, '0', '15', '1', '2', '4')";
        w1.append(sql);

        //INSERT INTO `rtvitc`.`rtv_group_dmr` (`id`, `gid`, `tgid`, `mode`, `dtx`, `drx`, `atx`, `arx`, `encryption`, `sq`, `ctcss`, `rf`, `broadband`, `cc`, `power`, `txmode`, `rxmode`)
        //VALUES ('1', '74760', '74760', '0', '409.025', '409.025', '409.025', '409.025', '0', '1', '49', NULL, '0', '20', '1', '2', '4');
        closeStream();
    }

    /**
     * 每个group最多65535个用户，每个公司的所有用户全部加入公司的前5个组
     */
    public static void rtv_user_group_map() throws Exception {
        createFile("rtv_user_group_map", user_count);

        //XXX 实际业务场景 : 一个组推荐不超过2000个成员

        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
			/*CorpUser cu = corp_user_map.get(cacheCorp);
			List<Long> userList = cu.getUserList();
			List<Long> conAllList = cu.getConAllList();//所有1-7阶
			*/
            // 每个zone 都有一个大组，包括了这个zone的所有成员。
            for (int idx = 1; idx <= zone_count; idx++) {
                List<Long> zUserList = zmap.get(idx).getUserList();
                Long gId = zmap.get(idx).getGroupList().get(0);// big group
                for (long userId : zUserList) {
                    String sql = "(NULL," + gId + "," + userId + ",5),";
                    w1.append(sql);
                }
            }

            // 每个zone 在另设50 个组，随机的包括成员，每个组的成员不超过40个人
            for (int idx = 1; idx <= zone_count; idx++) {
                List<Long> zUserList = zmap.get(idx).getUserList();
                int size = zUserList.size();
                for (int j = 1; j <= 50; j++) {
                    Long gId = zmap.get(idx).getGroupList().get(j);// common group
                    Set<Long> randomUSet = new HashSet<Long>();
                    for (int k = 1; k <= 40; k++) {
                        Random ran = new Random();
                        int ranIndex = ran.nextInt(size);
                        Long userId = zUserList.get(ranIndex);
                        randomUSet.add(userId);
                    }

                    for (long userId : randomUSet) {
                        String sql = "(NULL," + gId + "," + userId + ",5),";
                        w1.append(sql);
                    }
                }
            }
        }
        String sql = "(0,0,0,0)";
        w1.append(sql);
        closeStream();
    }

    /**
     * 每个公司50条
     */
    public static void rtv_console_user() throws Exception {
        createFile("rtv_console_user", corp_count * 50);
        long phone = 11111111111l;
        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            for (int i = 1; i <= 76; i++) {
                long idx = (cacheCorp - 1) * 50 + i;
                String sql = "(NULL,'cu" + idx + "','df','3c687a804bda8827','" + (phone++) + "'," + cacheCorp + ",NULL),";
                w1.append(sql);
            }
        }
        String sql = "(0,'default','df','3c687a804bda8827','1000000099',0,NULL)";//for : 去掉逗号
        w1.append(sql);
        //INSERT INTO `rtvitc`.`rtv_console_user` (`id`, `user_name`, `password`, `salt`, `phone`, `corp_id`, `admin_id`)
        //VALUES ('2', 'u2', 'df', '3c687a804bda8827', '19900094055', '1', NULL);
        closeStream();
    }

    public static void rtv_sub_admin() throws Exception {
        createFile("rtv_sub_admin", corp_count * 50);
        long phone = 12111111111l;
        for (int idx = 1; idx <= zone_count; idx++) {
            String sql = "(NULL,'sa" + idx + "','df','3c687a804bda8827','" + (phone++) + "'," + 1 + ",NULL,'[\"" + idx + "\"]'),";
            w1.append(sql);
        }

        String sql = "(0,'default','df','3c687a804bda8827','1200000099',0,NULL,NULL)";//for : 去掉逗号
        w1.append(sql);
        //INSERT INTO `rtvitc`.`rtv_console_user` (`id`, `user_name`, `password`, `salt`, `phone`, `corp_id`, `admin_id`)
        //VALUES ('2', 'u2', 'df', '3c687a804bda8827', '19900094055', '1', NULL);
        closeStream();
    }

    public static void rtv_cu_zone_map() throws Exception {
        createFile("rtv_cu_zone_map", corp_count * 50);
        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            long cuId = 1;
            for (int i = 1; i <= 50; i++) {
                long zoneId = (cacheCorp - 1) * 50 + i;
                for (int j = 1; j <= 2; j++) {
                    String sql = "(NULL," + cuId + "," + zoneId + "),";
                    w1.append(sql);
                    cuId++;
                }
            }
        }
        String sql = "(0,0,0)";//for : 去掉逗号
        w1.append(sql);
        //INSERT INTO `rtvitc`.`rtv_console_user` (`id`, `user_name`, `password`, `salt`, `phone`, `corp_id`, `admin_id`)
        //VALUES ('2', 'u2', 'df', '3c687a804bda8827', '19900094055', '1', NULL);
        closeStream();
    }

    /**
     * 每个公司所有的调度台关联该公司所有的关联用户
     */
    public static void rtv_console_user_mapping() throws Exception {
        createFile("rtv_console_user_mapping", user_count);
        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            for (long userId : conAllList) {
                for (int i = 1; i <= 50; i++) {
                    long idx = (cacheCorp - 1) * 50 + i;
                    String sql = "(NULL," + userId + ", " + idx + "),";
                    w1.append(sql);
                }
            }
        }
        String sql = "(0,0,0)";
        w1.append(sql);
        closeStream();
    }

    /**
     * 每个用户1条
     */
    public static void rtv_nvr() throws Exception {
        createFile("rtv_nvr", media_count);
        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            for (long userId : userList) {
                String sql = "(NULL, " + userId + ", 'nvr" + userId + "', 'admin', 'YWRtaW4=', '0', '101.201.45.198', '554', '5', " + cacheCorp + "),";
                w1.append(sql);
            }

            for (long userId : conAllList) {
                String sql = "(NULL, " + userId + ", 'nvr" + userId + "', 'admin', 'YWRtaW4=', '0', '101.201.45.198', '554', '5', " + cacheCorp + "),";
                w1.append(sql);
            }
        }
        String sql = "(0, 0, 'nvr0', 'admin', 'YWRtaW4=', '0', '101.201.45.198', '554', '5', 0)";//for : 去除最后一个逗号
        w1.append(sql);
        closeStream();
        //INSERT INTO `rtvitc`.`rtv_nvr` (`id`, `ctrid`, `display_name`, `uname`, `passwd`, `iptype`, `ipaddr`, `port`, `nvr_type`, `orgid`)
        //VALUES ('2', '69603', '藏山南-01', 'admin', 'YWRtaW4=', '0', '101.201.45.198', '554', '5', '1');
    }

    /**
     * 每个用户2条
     */
    public static void rtv_external_camera() throws Exception {
        createFile("rtv_external_camera", media_count);
        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            for (long userId : userList) {
                for (int i = 1; i <= 2; i++) {
                    String sql = "(NULL, " + userId + ",'cam" + userId
                            + "', 'admin', 'YWRtaW4xMjM0NQ==', '101.201.45.198', '0', '5', '5', '4', 'cnRzcDovL2FkbWluOmFkbWluMTIzNDVAMTAxLjIwMS40NS4xOTg6NTU0L2NhbS9yZWFsbW9uaXRv\\ncj9jaGFubmVsPTEmc3VidHlwZT0w', 'cnRzcDovL2FkbWluOmFkbWluMTIzNDVAMTAxLjIwMS40NS4xOTg6NTU0L2NhbS9yZWFsbW9uaXRv\\ncj9jaGFubmVsPTEmc3VidHlwZT0x', "
                            + cacheCorp + ",1,1),";
                    w1.append(sql);
                }
            }

            for (long userId : conAllList) {
                for (int i = 1; i <= 2; i++) {
                    String sql = "(NULL, " + userId + ",'cam" + userId
                            + "', 'admin', 'YWRtaW4xMjM0NQ==', '101.201.45.198', '0', '5', '5', '4', 'cnRzcDovL2FkbWluOmFkbWluMTIzNDVAMTAxLjIwMS40NS4xOTg6NTU0L2NhbS9yZWFsbW9uaXRv\\ncj9jaGFubmVsPTEmc3VidHlwZT0w', 'cnRzcDovL2FkbWluOmFkbWluMTIzNDVAMTAxLjIwMS40NS4xOTg6NTU0L2NhbS9yZWFsbW9uaXRv\\ncj9jaGFubmVsPTEmc3VidHlwZT0x', "
                            + cacheCorp + ",1,1),";
                    w1.append(sql);
                }
            }
        }
        String sql = "(0,0,'cam0', 'admin', 'YWRtaW4xMjM0NQ==', '101.201.45.198', '0', '5', '5', '4', NULL, NULL,0,1)";//for : 去除最后一个逗号
        w1.append(sql);
        //INSERT INTO `rtvitc`.`rtv_external_camera` (`id`, `ctrid`, `display_name`, `uname`, `passwd`, `ipaddr`, `iptype`, `nvr_type`, `camera_type`, `channel`, `rtsp_main_url`, `rtsp_sub_url`, `orgid`)
        //VALUES ('1', '69603', '驾驶仓摄像头', 'admin', 'YWRtaW4xMjM0NQ==', '101.201.45.198', '0', '5', '5', '4', 'cnRzcDovL2FkbWluOmFkbWluMTIzNDVAMTAxLjIwMS40NS4xOTg6NTU0L2NhbS9yZWFsbW9uaXRv\ncj9jaGFubmVsPTEmc3VidHlwZT0w', 'cnRzcDovL2FkbWluOmFkbWluMTIzNDVAMTAxLjIwMS40NS4xOTg6NTU0L2NhbS9yZWFsbW9uaXRv\ncj9jaGFubmVsPTEmc3VidHlwZT0x', '1');
        closeStream();
    }

    /**
     * 每个用户1条
     */
    public static void rtv_fence() throws Exception {
        createFile("rtv_fence", media_count);
        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            for (long userId : userList) {
                String sql = "(" + userId + ", 1, 100, '测试围栏" + userId
                        + "', '13244444', '1', '{\"lat\":30,\"lon\":104,\"rad\":1234}', '[{\"lat\":30,\"lon\":104,\"rad\":1234},{\"lat\":30,\"lon\":104,\"rad\":1234}]', " + userId + ", " + cacheCorp + "),";
                w1.append(sql);
            }

            for (long userId : conAllList) {
                String sql = "(" + userId + ", 1, 100, '测试围栏" + userId
                        + "', '13244444', '1', '{\"lat\":30,\"lon\":104,\"rad\":1234}', '[{\"lat\":30,\"lon\":104,\"rad\":1234},{\"lat\":30,\"lon\":104,\"rad\":1234}]', " + userId + ", " + cacheCorp + "),";
                w1.append(sql);
            }
        }
        String sql = "(0, 1, 100, '测试围栏', '13244444', '1', '{\"lat\":30,\"lon\":104,\"rad\":1234}', '[{\"lat\":30,\"lon\":104,\"rad\":1234},{\"lat\":30,\"lon\":104,\"rad\":1234}]',0,0)";
        w1.append(sql);
        closeStream();
        //INSERT INTO `rtvitc`.`rtv_fence` (`id`, `status`, `ts`, `name`, `color`, `type`, `circle_info`, `polygon_info`, `ownerid`, `corpid`)
        //VALUES ('3', '1', '4', '测试围栏3', '1', '1', '{\"lat\":30,\"lon\":104,\"rad\":1234}', '[{\"lat\":30,\"lon\":104,\"rad\":1234},{\"lat\":30,\"lon\":104,\"rad\":1234}]', '69592', '1');
    }

    public static void rtv_version_map() throws Exception {
        createFile("rtv_version_map", user_count);
        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            String sql = "(NULL, NULL, 'console', '5.0." + cacheCorp + "', 'http://rtv.oss-cn-beijing.aliyuncs.com/APPLatestVersion%2FJJH.apk', '1', '1', NULL, '0', 'main', '2017-07-26 15:58:35'),";
            if (cacheCorp == corp_count) {
                sql = sql.substring(0, sql.length() - 1);
            }
            w1.append(sql);
        }
        //INSERT INTO `rtvitc`.`rtv_version_map` (`id`, `server_version`, `client_os_type`, `client_version`, `client_url`, `latest_server`, `latest_client`, `client_description`, `force`, `branch`, `upload_time`)
        //VALUES ('1', '2.2', 'console', '2.5.1', 'http://rtv.oss-cn-beijing.aliyuncs.com/APPLatestVersion%2FJJH.apk', '1', '1', NULL, '0', 'main', '2017-07-26 15:58:35');
        closeStream();
    }

    public static void rtv_version_user_map() throws Exception {
        createFile("rtv_version_user_map", user_count);

        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            for (long userId : userList) {
                String sql = "(" + userId + ", " + userId + ", NULL, 1, 2, '0', NULL, '2017-10-09 17:20:07', '2017-10-09 17:49:19'),";
                w1.append(sql);
            }
            for (long userId : conAllList) {
                String sql = "(" + userId + ", " + userId + ", NULL, 1, 2, '0', NULL, '2017-10-09 17:20:07', '2017-10-09 17:49:19'),";
                w1.append(sql);
            }
        }

        String sql = "(0, 0, NULL, 1, 2, '0', NULL, '2017-10-09 17:20:07', '2017-10-09 17:49:19')";
        w1.append(sql);

        //INSERT INTO `rtvitc`.`rtv_version_user_map` (`id`, `uid`, `device_type`, `cur_vid`, `plan_vid`, `force`, `plan_time`, `up_time`, `login_time`)
        //VALUES ('1', '65537', NULL, '12', NULL, '0', NULL, '2017-08-29 17:20:07', '2017-08-29 17:49:19');
        closeStream();
    }

    public static void rtv_plan_rule() throws Exception {
        createFile("rtv_plan_rule", media_count);
        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            for (long userId : userList) {
                String sql = "(" + userId + ",1,100, 'rule" + userId + "', '{\"repeat\":true,\"weeks\":\"1,2,3,4,5\",\"start\":\"9:00:00\",\"end\":\"18:00:00\"}', '1', '{\"fence\":" + userId + "}', '0', "
                        + cacheCorp + "),";
                w1.append(sql);
                //INSERT INTO `rtvitc`.`rtv_plan_rule` (`id`, `status`, `ts`, `name`, `scheduled_time`, `eventid`, `event_params`, `ownerid`, `corpid`)
                //VALUES ('2', '1', '2', '测试进入围栏1', '{\"repeat\":true,\"weeks\":\"1,2,3,4,5\",\"start\":\"9:00:00\",\"end\":\"18:00:00\"}', '1', '{\"fence\":1}', '0', '2');
            }

            for (long userId : conAllList) {
                String sql = "(" + userId + ",1,100, 'rule" + userId + "', '{\"repeat\":true,\"weeks\":\"1,2,3,4,5\",\"start\":\"9:00:00\",\"end\":\"18:00:00\"}', '1', '{\"fence\":" + userId + "}', " + userId
                        + ", " + cacheCorp + "),";
                w1.append(sql);
            }
        }
        String sql = "(0,1,0,NULL,NULL,NULL,NULL,0,0)";
        w1.append(sql);
        closeStream();
    }

    /**
     * 前10个rule加载所有用户
     */
    public static void rtv_plan_rule_user_map() throws Exception {
        createFile("rtv_plan_rule_user_map", media_count);
        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            //前10个rule加载所有用户
            int ruleIdx = 0;
            for (long userId : userList) {
                if (ruleIdx >= 100) {
                    ruleIdx = 0;
                }
                long ruleId = userList.get(ruleIdx);
                String sql = "(NULL," + ruleId + "," + userId + "),";
                w1.append(sql);
                //INSERT INTO `rtvitc`.`rtv_plan_rule_user_map` (`id`, `ruleid`, `uid`) VALUES ('2', '4', '69600');
                ruleIdx++;
            }

            ruleIdx = 0;
            for (long userId : conAllList) {
                if (ruleIdx >= 100) {
                    ruleIdx = 0;
                }
                long ruleId = userList.get(ruleIdx);
                String sql = "(NULL," + ruleId + "," + userId + "),";
                w1.append(sql);
                //INSERT INTO `rtvitc`.`rtv_plan_rule_user_map` (`id`, `ruleid`, `uid`) VALUES ('2', '4', '69600');
                ruleIdx++;
            }

        }
        String sql = "(0,0,0)";
        w1.append(sql);
        closeStream();
    }

    @SuppressWarnings("unused")
    private static void initFile(String folder, String prefix, String suffix, int start, int end) throws Exception {
        File file = null;
        for (int i = start; i <= end; i++) {
            file = new File(folder + prefix + i + suffix);
            if (!file.exists()) {
                file.createNewFile();
            }
        }
    }

    /**
     * 每个用户3条数据
     */
    public static void rtv_rtpictures() throws Exception {
        createFile("rtv_rtpictures", media_count);
        int id = 1;
        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            for (long userId : userList) {
                for (int i = 1; i <= 3; i++) {
                    String sql = "(" + id + ", " + userId + ", '{\"addr\":\"天目路61-63号;保利·香槟国际\",\"desc\":\"\"}', 'http://101.201.45.198:8081/rtv/rtpictures/2017-10-26/hd_" + id
                            + ".jpg', 'http://101.201.45.198:8081/rtv/rtpictures/2017-10-26/small_" + id + ".jpg', 'http://101.201.45.198:8081/rtv/rtpictures/2017-10-26/large_" + id
                            + ".jpg', '2017-10-26 12:07:51', " + cacheCorp + "),";
                    w1.append(sql);
                    id++;
                }
            }

            for (long userId : conAllList) {
                for (int i = 1; i <= 3; i++) {
                    String sql = "(" + id + ", " + userId + ", '{\"addr\":\"天目路61-63号;保利·香槟国际\",\"desc\":\"\"}', 'http://101.201.45.198:8081/rtv/rtpictures/2017-10-26/hd_" + id
                            + ".jpg', 'http://101.201.45.198:8081/rtv/rtpictures/2017-10-26/small_" + id + ".jpg', 'http://101.201.45.198:8081/rtv/rtpictures/2017-10-26/large_" + id
                            + ".jpg', '2017-10-26 12:07:51', " + cacheCorp + "),";
                    w1.append(sql);
                    id++;
                }
            }
        }
        String sql = "(0,0,NULL,NULL,NULL,NULL,NULL,0)";
        w1.append(sql);
        closeStream();
    }

    /**
     * 每个用户3条数据
     */
    public static void rtv_video() throws Exception {
        createFile("video_record_table", user_count);
        long id = 1;
        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            for (long userId : userList) {
                for (int i = 1; i <= 3; i++) {
                    String sql = "(" + id + ", " + userId + ",'1508984291', '1508984291', '/home/itcit/video/record/2017-10-26/" + id
                            + ".mp4', '22.855', '20.146', '1.74433', '610.573', 'http://101.201.45.198:81/hls/2017-10-26/" + id + ".m3u8', " + cacheCorp + ", '0', '0'),";
                    w1.append(sql);
                    id++;
                    //INSERT INTO `rtvitc`.`video_record_table` (`id`, `uid`, `video_date`, `create_date`, `video_url`, `duration`, `frame_rate`, `size`, `bit_rate`, `url`, `corp_id`, `session`, `channel`)
                    //VALUES ('1', '69537', '1493373747', '1493373747', '/home/itcit/video/record/2017-04-28/3686858974_00069537_2017-04-28_18:02:27.mp4', '22.855', '20.146', '1.74433', '610.573', 'http://101.201.45.198:81/hls/2017-04-28/3686858974_00069537_2017-04-28_18:02:27.m3u8', '1', '0', '0');
                }
            }

            for (long userId : conAllList) {
                for (int i = 1; i <= 3; i++) {
                    String sql = "(" + id + ", " + userId + ",'1508984291', '1508984291', '/home/itcit/video/record/2017-10-26/" + id
                            + ".mp4', '22.855', '20.146', '1.74433', '610.573', 'http://101.201.45.198:81/hls/2017-10-26/" + id + ".m3u8', " + cacheCorp + ", '0', '0'),";
                    w1.append(sql);
                    id++;
                }
            }
        }
        String sql = "(0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)";
        w1.append(sql);
        closeStream();
    }

    /**
     * 每个用户1条数据
     */
    public static void rtv_gps_info() throws Exception {
        createFile("rtv_gps_info", user_count);

        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            for (long userId : userList) {
                for (int i = 1; i <= 1; i++) {
                    String sql = "(NULL, " + userId
                            + ", '20180110', '\0Y�@>���`Z�@ZN�ğ�\\0Y3@>���6?W@ZM�΁\\0Y�@>�|�&�+@ZQeMa�\\0YB@>�����@ZM@$�>\\0Y�@>��`��@ZN�/f\\0Y@>�|����@ZQ�\\r�\\0Y�@>�|�&�+@ZQi�\\0Y?@>���2#@ZO�I�H\\0Y�@>����M@ZOT��l\\0YP@>�}�%V�@ZQ�7�M\\0Y�@>��]�r�@ZP3:~\\0YF@>���1�!@ZO�B��\\0Y�@>��]�r�@ZP3:~\\0Y�@>��@l\\0�@ZP?�t6\\0Y9@>��]�r�@ZP3:~\\0YX@>��a��@ZP.�|�\\0Y�@>��]�r�@ZP3:~\\0Y�@>��\\Z��@ZP�	$\\0Y\\Z-@>�{�\\rZ\\\\@ZQ���\f\\0Y\\Z�@>���2#@ZO�О\\0Y!@>��@l\\0�@ZPLH��', "
                            + cacheCorp + "),";
                    w1.append(sql);
                    //INSERT INTO `rtvitc`.`rtv_gps_info` (`id`, `uid`, `date_key`, `gps_points`, `orgid`) VALUES ('11111', '69537', '20170428', '123', '1');
                }
            }

            for (long userId : conAllList) {
                for (int i = 1; i <= 1; i++) {
                    String sql = "(NULL, " + userId
                            + ", '20180110', '\\0Y�@>���`Z�@ZN�ğ�\\\\0Y3@>���6?W@ZM�΁\\\\0Y�@>�|�&�+@ZQeMa�\\\\0YB@>�����@ZM@$�>\\\\0Y�@>��`��@ZN�/f\\\\0Y@>�|����@ZQ�\\\\r�\\\\0Y�@>�|�&�+@ZQi�\\\\0Y?@>���2#@ZO�I�H\\\\0Y�@>����M@ZOT��l\\\\0YP@>�}�%V�@ZQ�7�M\\\\0Y�@>��]�r�@ZP3:~\\\\0YF@>���1�!@ZO�B��\\\\0Y�@>��]�r�@ZP3:~\\\\0Y�@>��@l\\\\0�@ZP?�t6\\\\0Y9@>��]�r�@ZP3:~\\\\0YX@>��a��@ZP.�|�\\\\0Y�@>��]�r�@ZP3:~\\\\0Y�@>��\\\\Z��@ZP�	$\\\\0Y\\\\Z-@>�{�\\\\rZ\\\\\\\\@ZQ���\\f\\\\0Y\\\\Z�@>���2#@ZO�О\\\\0Y!@>��@l\\\\0�@ZPLH��', "
                            + cacheCorp + "),";
                    w1.append(sql);
                    //INSERT INTO `rtvitc`.`rtv_gps_info` (`id`, `uid`, `date_key`, `gps_points`, `orgid`) VALUES ('11111', '69537', '20170428', '123', '1');
                }
            }
        }
        String sql = "(0,0,NULL,NULL,NULL)";
        w1.append(sql);
        closeStream();
    }

    /**
     * 100万数据
     */
    public static void rtv_operation_log() throws Exception {
        createFile("rtv_operation_log", user_count);

        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            for (long userId : userList) {
                String sql = "(NULL, 'rtv_user', '修改用户', '{\"id\":\"69554\",\"nickname\":\"BMS18\",\"phone\":\"19900004018\",\"userPriority\":\"1\",\"zoneId\":\"1\",\"consoleIdAddAry\":\"[]\",\"consoleIdDelAry\":\"[]\",\"groupIdAddAry\":\"[]\",\"groupIdDelAry\":\"[]\"}', '[{\"val_cn_old\":\"20\",\"key_cn\":\"用户优先级\",\"val_new\":\"1\",\"key_en\":\"priority\",\"val_cn_new\":\"1\",\"val_old\":\"20\"}]', '<thead><tr role=\"row\"><th class=\"col-xs-3 sorting_disabled\">名称</th><th class=\"col-xs-3 sorting_disabled\">旧数据</th><th class=\"col-xs-6 sorting_disabled\">新数据</th></tr></thead><tbody><tr role=\"row\" class=\"even\"><td>用户优先级</td><td>20</td><td>1</td></tr></tbody>', '171.217.97.157', '2017-05-08 17:56:12', 'update', 'default', NULL, '1', 'admin', 'zom', '"
                        + userId
                        + "-BMS18',1,1,NULL, '1', 'admin', 'zom', '@Around', '1', '{\"status\":\"success\",\"_resultType\":\"_result_obj\",\"code\":\"operation_success\",\"msg\":\"操作成功\",\"success\":\"success\"}', NULL),";
                w1.append(sql);
            }
            for (long userId : conAllList) {
                String sql = "(NULL, 'rtv_user', '修改用户', '{\"id\":\"69554\",\"nickname\":\"BMS18\",\"phone\":\"19900004018\",\"userPriority\":\"1\",\"zoneId\":\"1\",\"consoleIdAddAry\":\"[]\",\"consoleIdDelAry\":\"[]\",\"groupIdAddAry\":\"[]\",\"groupIdDelAry\":\"[]\"}', '[{\"val_cn_old\":\"20\",\"key_cn\":\"用户优先级\",\"val_new\":\"1\",\"key_en\":\"priority\",\"val_cn_new\":\"1\",\"val_old\":\"20\"}]', '<thead><tr role=\"row\"><th class=\"col-xs-3 sorting_disabled\">名称</th><th class=\"col-xs-3 sorting_disabled\">旧数据</th><th class=\"col-xs-6 sorting_disabled\">新数据</th></tr></thead><tbody><tr role=\"row\" class=\"even\"><td>用户优先级</td><td>20</td><td>1</td></tr></tbody>', '171.217.97.157', '2017-05-08 17:56:12', 'update', 'default', NULL, '1', 'admin', 'zom', '"
                        + userId
                        + "-BMS18',1,1,NULL, '1', 'admin', 'zom', '@Around', '1', '{\"status\":\"success\",\"_resultType\":\"_result_obj\",\"code\":\"operation_success\",\"msg\":\"操作成功\",\"success\":\"success\"}', NULL),";
                w1.append(sql);
            }
        }

        String sql = "(0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)";
        w1.append(sql);

        closeStream();
    }

    public static void rtv_dlmsg_storage() throws Exception {
        createFile("rtv_dlmsg_storage", user_count);

        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            for (long userId : userList) {
                String sql = "(NULL, " + userId + ", " + userId + ", " + (userId + 1) + ", '0', '0', '0', '1494679262', '1494679262', '0', '29', '5LmI5LmI5LmI5LmI5aaI5aaI5ZKq\n'),";
                w1.append(sql);
            }
            for (long userId : conAllList) {
                String sql = "(NULL, " + userId + ", " + userId + ", " + (userId + 1) + ", '0', '0', '0', '1494679262', '1494679262', '0', '29', '5LmI5LmI5LmI5LmI5aaI5aaI5ZKq\n'),";
                w1.append(sql);
            }
        }

        String sql = "(0,0,0,0,0,0,0,0,0,0,0,0)";
        w1.append(sql);
        closeStream();
    }

    public static void rtv_ulmsg_storage() throws Exception {
        createFile("rtv_ulmsg_storage", user_count);

        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            for (long userId : userList) {
                String sql = "(" + userId + ", " + userId + ", " + userId + ", " + (userId + 1) + ", '0', '0', '0', '1494515625', '1494515625', '13', '5L2g5aW9cQ==\n'),";
                w1.append(sql);
            }
            for (long userId : conAllList) {
                String sql = "(" + userId + ", " + userId + ", " + userId + ", " + (userId + 1) + ", '0', '0', '0', '1494515625', '1494515625', '13', '5L2g5aW9cQ==\n'),";
                w1.append(sql);
            }
        }

        String sql = "(0,0,0,0,0,0,0,0,0,0,0)";
        w1.append(sql);
        closeStream();
    }

    //INSERT INTO `rtvitc`.`rtv_gps_info` (`id`, `uid`, `date_key`, `gps_points`, `orgid`) VALUES
    //('1', '69537', '20170428', '\0Y�@>���`Z�@ZN�ğ�\0Y3@>���6?W@ZM�΁\0Y�@>�|�&�+@ZQeMa�\0YB@>�����@ZM@$�>\0Y�@>��`��@ZN�/f\0Y@>�|����@ZQ�\r�\0Y�@>�|�&�+@ZQi�\0Y?@>���2#@ZO�I�H\0Y�@>����M@ZOT��l\0YP@>�}�%V�@ZQ�7�M\0Y�@>��]�r�@ZP3:~\0YF@>���1�!@ZO�B��\0Y�@>��]�r�@ZP3:~\0Y�@>��@l\0�@ZP?�t6\0Y9@>��]�r�@ZP3:~\0YX@>��a��@ZP.�|�\0Y�@>��]�r�@ZP3:~\0Y�@>��\Z��@ZP�	$\0Y\Z-@>�{�\rZ\\@ZQ���\0Y\Z�@>���2#@ZO�О\0Y!@>��@l\0�@ZPLH��', '1');

    //INSERT INTO `rtvitc`.`rtv_token` (`id`, `token`, `uid`, `request_date`, `refresh_date`, `overdue_date`) VALUES
    //('2', 'ec25339660da4b66907a61047a0e3d43', '69590', '2017-04-28 18:01:37', '2017-04-28 18:01:37', '2301-02-02 18:01:37');

    //INSERT INTO `rtvitc`.`rtv_operation_log` (`id`, `oper_obj`, `title`, `params`, `meta_content`, `show_content`, `ip`, `operation_time`, `opration_type`, `operation_level`, `user_id`, `corp_id`, `username`, `corp_name`, `obj_name`, `operator_user_id`, `operator_corp_id`, `operator_username`, `operator_corp_name`, `log_aspect`, `status`, `result`, `remark`) VALUES
    //('10', 'rtv_user', '修改用户', '{\"id\":\"69554\",\"nickname\":\"BMS18\",\"phone\":\"19900004018\",\"userPriority\":\"1\",\"zoneId\":\"1\",\"consoleIdAddAry\":\"[]\",\"consoleIdDelAry\":\"[]\",\"groupIdAddAry\":\"[]\",\"groupIdDelAry\":\"[]\"}', '[{\"val_cn_old\":\"20\",\"key_cn\":\"用户优先级\",\"val_new\":\"1\",\"key_en\":\"priority\",\"val_cn_new\":\"1\",\"val_old\":\"20\"}]', '<thead><tr role=\"row\"><th class=\"col-xs-3 sorting_disabled\">名称</th><th class=\"col-xs-3 sorting_disabled\">旧数据</th><th class=\"col-xs-6 sorting_disabled\">新数据</th></tr></thead><tbody><tr role=\"row\" class=\"even\"><td>用户优先级</td><td>20</td><td>1</td></tr></tbody>', '171.217.97.157', '2017-05-08 17:56:12', 'update', 'default', NULL, '1', 'admin', 'zom', '69554-BMS18', NULL, '1', 'admin', 'zom', '@Around', '1', '{\"status\":\"success\",\"_resultType\":\"_result_obj\",\"code\":\"operation_success\",\"msg\":\"操作成功\",\"success\":\"success\"}', NULL);

    //INSERT INTO `rtvitc`.`rtv_dlmsg_storage` (`id`, `msgid`, `sender`, `receiver`, `ttype`, `tgid`, `sessionid`, `schedtime`, `delivtime`, `mtype`, `length`, `content`) VALUES
    //('6', '1062', '69554', '69591', '0', '0', '0', '1494679262', '1494679262', '0', '29', '5LmI5LmI5LmI5LmI5aaI5aaI5ZKq\n');

    //INSERT INTO `rtvitc`.`rtv_ulmsg_storage` (`msgid`, `txtid`, `sender`, `receiver`, `ttype`, `session`, `mtype`, `recvtime`, `schedtime`, `length`, `content`) VALUES
    //('936', '1494515625', '69554', '69591', '0', '0', '0', '1494515625', '1494515625', '13', '5L2g5aW9cQ==\n');

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
