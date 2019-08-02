package com.zom.factory.zfactory.generator.data;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import com.zom.factory.zfactory.generator.data.model.ZoneIdAssign;

/**
 * 生成百万或千万级别的测试数据
 *
 * @author airson
 */
public class BigDataGenerator2 {

    /**
     * sql脚本文件的存放目录
     */
    private static final String output_dir = "D:\\zlab\\sql\\";
    /**
     * 数据库名称
     */
    private static final String dbname     = "zlab";

    /**
     * 默认值：数据的起始ID
     */
    private static final long user_id_start = 65537;

    // --- 数据规模配置 ---
    private static boolean sm = true;

    private static final long zone_count  = sm ? 50 : 255;
    private static final long corp_count  = sm ? 50 : 500;
    private static final long user_count  = sm ? 200000 : 2000000;    //user,token,bms_params,version_user
    private static final long group_count = sm ? 200000 : 5000000;
    private static final long media_count = sm ? 200000 : 5000000;    //video,picture,gps_info
	/*private static final long	domain_count	= sm ? 200000 : 5000000;	//plan_rule,operation_log,dlmsg,ulmsg
	private static final long	fence_count		= sm ? 200000 : 5000000;	//fence
	private static final long	nvr_count		= sm ? 200000 : 5000000;
	private static final long	cameral_count	= sm ? 200000 : 5000000;
	private static final long	version_count	= sm ? 2000 : 50000;*/

    /**
     * 默认值：数据的结束ID，与起始ID相减得数据的总条数
     */
    private static final long user_id_end = user_id_start + user_count;

    /**
     * 保存255个zone的数据，新增用户时从中取出用户ID值
     */
    private static Map<Integer, ZoneIdAssign> zmap = new HashMap<Integer, ZoneIdAssign>();

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

    public static void main(String[] args) {
        //rtv_zone();
        //rtv_corp();
        //rtv_corp_zone_map();
        //rtv_user();
    }
    //seq:zone,corp,corp_zone,user(each_zone_user,each_zone_console),token,user_admin(上下级关系),bms_params, *console_cop_mapping
    //group,user_group,nvr,camera,fence,plan_rule,console_user,picture,video,gps,ulmsg,dlmsg

    //zone_user,zone_console

    /**
     * 创建255个zone - 满zone - 实际使用数为：{zone_count}
     */
    public static void rtv_zone() throws Exception {
        createFile("zone_id_assign", 255);
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
            sql = "(" + id + "," + id + ",'" + zone.getName() + "'," + zone.getMaxUid() + "," + zone.getMaxTgid() + "," + zone.getCurUid() + "," + zone.getCurTgid() + "),";
            if (id == 255)
                sql = sql.substring(0, sql.length() - 1);
            w1.append(sql);
            id++;
        }
        closeStream();
    }

    /**
     * 创建1000个公司， - 实际使用数为：{corp_count}
     */
    public static void rtv_corp() throws Exception {
        createFile("rtv_corp_table", 1000);
        long id = 1;
        long phone = 12700000000l;
        while (id <= 1000) {
            String sql_10 = "(" + id + ", 'corp" + id + "', 'corpname" + id + "', '4297f44b13955235245b2497399d7a93', 'corp" + id + "@01more.com', '" + (phone++)
                    + "','2017-01-01 13:30:30', '2017-01-01 13:30:30', '2017-01-01 14:40:40', '182.160.45.01'";
            String sql_20 = ",1,1,10000000,10000000,10000000,10000000,1,1,1,0";
            String sql_30 = ",1,30,0,0,0,5,'{\"camera\":1}'),";
            String sql = sql_10 + sql_20 + sql_30;
            if (id == 1000)
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
        System.out.println(" -------- rtv_user 和 rtv_user_admin_mapping 同时生成： -------- ");
        createFile("rtv_user", total);
        createFile2("rtv_user_admin_mapping", total);
        long phone = 13700000000l;//XXX 通过手机号来判断是否为最后一个新增用户 phone == (sm ? 13700200000l : 13702000000l)
        int zoneId = 1;

        //独占两个zone的公司
        long twoZoneCorpMax = corp_count / 10;//sm = true : 5 : 50;
        for (long twoZoneCorpId = 1; twoZoneCorpId <= twoZoneCorpMax; twoZoneCorpId++) {
            System.out.println("two zone corp id : " + twoZoneCorpId + ", phone : " + phone);
            for (int idx = 1; idx <= 2; idx++) {//独占两个zone
                ZoneIdAssign zone = zmap.get(zoneId);

                phone = insertUserByTurn(phone, twoZoneCorpId, zoneId, zone, false);

                zoneId++;
            }
        }

        //独占一个zone的公司
        long oneZoneCorpMax = corp_count / 5;//sm = true : 10 : 100;
        for (long oneZoneCorpId = (corp_count / 10 + 1); oneZoneCorpId <= oneZoneCorpMax; oneZoneCorpId++) {
            System.out.println("one zone corp id : " + oneZoneCorpId + ", phone : " + phone);
            ZoneIdAssign zone = zmap.get(zoneId);

            phone = insertUserByTurn(phone, oneZoneCorpId, zoneId, zone, false);

            zoneId++;
        }

        //每5个公司占一个zone
        for (long shareZoneCorpId = 1; shareZoneCorpId <= corp_count; shareZoneCorpId++) {
            System.out.println("share zone corp id : " + shareZoneCorpId + ", phone : " + phone);
            ZoneIdAssign zone = zmap.get(zoneId);

            phone = insertUserByTurn(phone, shareZoneCorpId++, zoneId, zone, true);
            phone = insertUserByTurn(phone, shareZoneCorpId++, zoneId, zone, true);
            phone = insertUserByTurn(phone, shareZoneCorpId++, zoneId, zone, true);
            phone = insertUserByTurn(phone, shareZoneCorpId++, zoneId, zone, true);
            phone = insertUserByTurn(phone, shareZoneCorpId, zoneId, zone, true);

            zoneId++;
        }

        //INSERT INTO `rtvitc`.`rtv_user` (`id`, `display_name`, `user_password`, `client_version`, `device`, `img_url`, `phone`, `register_date`, `last_logon_date`, `last_access_date`,
        //`last_logon_ip`, `logon_state`, `salt`, `ts_profile`, `ts_group`, `rank`, `admin_id`, `dcg_id`, `corp_id`, `logon_name`,
        //`priority`, `status`, `preconfig`, `default_grp`, `gps_report`, `gps_interval`, `adm_ts`, `zone_id`, `ats`, `pts`,
        //`imei`, `lmr_uid`, `ext_did`)
        //VALUES ('65537', 'u1', 'df', '2.5.1', 'web', NULL, '19900000001', '2016-05-30 14:04:31', '2017-08-29 17:49:19', '2017-08-29 17:49:19',
        //NULL, 'online', 'c996f03e8c8f4c09', '6', '4', '0', '0', '65537', '1', 'u11',
        //'1', '1', '1', '0', '1', '30', '0', '1', '0', '0',
        //'', NULL, NULL);
        closeStream();
    }

    private static long insertUserByTurn(long phone, long corpId, long zoneId, ZoneIdAssign zone, boolean shareZone) throws Exception {
        String dname = null;
        String lname = null;
        int turnIdx = 1;
        //每个zone8000个用户
        int total = shareZone ? 1600 : 8000;
        long userId = zone.getCurUid();
        long turnUserIdStart = userId;
        System.out.println("turnUserIdStart : " + turnUserIdStart + ", userId : " + userId + ", zoneId : " + zoneId);
        for (int userCount = 1; userCount <= total; userCount++) {
            dname = "bms" + userId;
            lname = "u" + userId;
            int rank = 0;
            if (turnIdx > 1600) {//每1600个用户为一轮，每一轮中1569个用户，31个console(1个7阶，2个6阶，3个5阶，4个4阶，5个3阶，6个2阶，10个1阶)
                turnIdx = 1;
                turnUserIdStart = userId;
                System.out.println("consoleIdStart : " + turnUserIdStart + ", userId : " + userId + ", zoneId : " + zoneId);
            }
            if (turnIdx > 1569) {//console
                if (turnIdx <= 1579) {//一阶10个：70-79
                    rank = 1;
                    //rtv_user_admin_mapping : 每前1000个用户加入所有1阶,剩余569个用户被二阶以上调度台直管
                    for (int top1500User = 1; top1500User <= 1500; top1500User++) {
                        String sql2 = "(NULL, " + (turnUserIdStart + top1500User) + "," + userId + "),";
                        w2.append(sql2);
                    }

                } else if (turnIdx <= 1585) {//二阶6个：80-85
                    rank = 2;
                    //rtv_user_admin_mapping :所有一阶加入所有二阶
                    for (int level_1 = 1; level_1 <= 10; level_1++) {
                        String sql2 = "(NULL, " + (turnUserIdStart + 1569 + level_1) + "," + userId + "),";
                        w2.append(sql2);
                    }
                    for (int u = 1; u <= 20; u++) {//1001-1100的100个用户直接加入所有二阶
                        String sql2 = "(NULL, " + (turnUserIdStart + 1500 + u) + "," + userId + "),";
                        w2.append(sql2);
                    }
                } else if (turnIdx <= 1590) {//三阶5个：86-90
                    rank = 3;
                    //rtv_user_admin_mapping :所有二阶加入所有三阶
                    for (int level_2 = 1; level_2 <= 6; level_2++) {
                        String sql2 = "(NULL, " + (turnUserIdStart + 1579 + level_2) + "," + userId + "),";
                        w2.append(sql2);
                    }
                    for (int u = 1; u <= 10; u++) {//1001-1100的100个用户直接加入所有三阶
                        String sql2 = "(NULL, " + (turnUserIdStart + 1520 + u) + "," + userId + "),";
                        w2.append(sql2);
                    }
                } else if (turnIdx <= 1594) {//四阶4个：91-94
                    rank = 4;
                    //rtv_user_admin_mapping :所有三阶加入所有四阶
                    for (int level_3 = 1; level_3 <= 5; level_3++) {
                        String sql2 = "(NULL, " + (turnUserIdStart + 1585 + level_3) + "," + userId + "),";
                        w2.append(sql2);
                    }
                    for (int u = 1; u <= 10; u++) {//1001-1100的100个用户直接加入所有四阶
                        String sql2 = "(NULL, " + (turnUserIdStart + 1530 + u) + "," + userId + "),";
                        w2.append(sql2);
                    }
                } else if (turnIdx <= 1597) {//五阶3个：95-97
                    rank = 5;
                    //rtv_user_admin_mapping :所有四阶加入所有五阶
                    for (int level_4 = 1; level_4 <= 4; level_4++) {
                        String sql2 = "(NULL, " + (turnUserIdStart + 1590 + level_4) + "," + userId + "),";
                        w2.append(sql2);
                    }
                    for (int u = 1; u <= 10; u++) {//1001-1100的100个用户直接加入所有五阶
                        String sql2 = "(NULL, " + (turnUserIdStart + 1540 + u) + "," + userId + "),";
                        w2.append(sql2);
                    }
                } else if (turnIdx <= 1599) {//六阶2个：98-99
                    rank = 6;
                    //rtv_user_admin_mapping :所有五阶加入所有六阶
                    for (int level_5 = 1; level_5 <= 3; level_5++) {
                        String sql2 = "(NULL, " + (turnUserIdStart + 1590 + level_5) + "," + userId + "),";
                        w2.append(sql2);
                    }
                    for (int u = 1; u <= 10; u++) {//1001-1100的100个用户直接加入所有六阶
                        String sql2 = "(NULL, " + (turnUserIdStart + 1540 + u) + "," + userId + "),";
                        w2.append(sql2);
                    }
                } else if (turnIdx <= 1600) {//七阶1个：100
                    rank = 7;
                    //rtv_user_admin_mapping :所有六阶加入所有七阶
                    for (int level_6 = 1; level_6 <= 2; level_6++) {
                        String sql2 = "(NULL, " + (turnUserIdStart + 1590 + level_6) + "," + userId + "),";
                        w2.append(sql2);
                    }
                    for (int u = 1; u <= 10; u++) {//1001-1100的100个用户直接加入所有七阶
                        String sql2 = "(NULL, " + (turnUserIdStart + 1540 + u) + "," + userId + "),";
                        w2.append(sql2);
                    }
                }
            }
            String sql_10 = "(" + userId + ",'" + dname + "','df',NULL,'portable',NULL,'" + (phone++) + "','2017-09-30 14:16:00','2017-09-30 14:16:00',NULL";
            String sql_20 = ",NULL,'offline','245956046a76a3a8',100,100,0," + rank + "," + userId + "," + corpId + ",'" + lname;
            String sql_30 = "',1,1,1,0,1,30,0," + zoneId + ",0,0,";
            String sql_40 = "'',NULL,NULL),";
            String sql = sql_10 + sql_20 + sql_30 + sql_40;
            if (phone == (sm ? 13700200000l : 13702000000l)) {//XXX 通过手机号来判断是否为最后一个新增用户
                sql = sql.substring(0, sql.length() - 1);
                System.out.println("share zone corp id : " + corpId + ", phone : " + phone);
            }
            w1.append(sql);
            zone.setCurUid(++userId);
            turnIdx++;
        }
        return phone;
    }

    /**
     * sm=true:前5个公司各占满二个zone:5*8000*2=80000,6-10的公司各占满一个zone:5*8000=40000,1-50的公司每5个公司占满一个zone(每公司1600)，共占10个zone：10*8000=80000,总共2十万个用户
     * sm=false:前50个公司各占满二个zone:50*8000*2=800000,51-100的公司各占满一个zone:50*8000=400000,1-500的公司每5个公司占满一个zone(每公司1600)，共占100个zone：100*8000=800000,总共二百万个用户
     */
    public static void rtv_user_admin_mapping() throws Exception {
        createFile("rtv_user_admin_mapping", user_count);
        long userId = user_id_start;
        long zoneUserIdStart = 0;
        int consoleIdx = 1;
        for (int corpId = 1; corpId <= 50; corpId++) {
            for (int zoneId = 1; zoneId <= 10; zoneId++) {
                zoneUserIdStart = userId;
                for (long idx = 1; idx <= 10000; idx++) {
                    if (idx <= 1000) {//前1000个用户加入50个一阶
                        for (long idx2 = 1; idx2 <= 50; idx2++) {
                            String sql = "(NULL, " + userId + "," + (zoneUserIdStart + 9900 + idx2) + "),";
                            w1.append(sql);
                        }
                    } else if (idx <= 2050) {//每个调度台关联5个用户，共210*5=1050
                        if (consoleIdx > 210) {//有210个大于1阶的调度台，每个调度台轮流接收user,
                            consoleIdx = 1;
                        }
                        String sql = "(NULL, " + userId + "," + (zoneUserIdStart + 9690 + consoleIdx++) + "),";//9690-1000=8690
                        w1.append(sql);
                    } else if (idx >= 9690) {
                        //int rank = 0;
                        if (idx <= 9700) {//七阶：9690-9700
                            //rank = 7;
                            for (long idx2 = 1; idx2 <= 20; idx2++) {//有20个6阶,全部加入7阶,
                                String sql = "(NULL, " + (zoneUserIdStart + 9700 + idx2) + "," + userId + "),";
                                w1.append(sql);
                            }
                        } else if (idx <= 9720) {//六阶：9700-9720
                            //rank = 6;
                            for (long idx2 = 1; idx2 <= 30; idx2++) {//有30个5阶,全部加入6阶,
                                String sql = "(NULL, " + (zoneUserIdStart + 9200 + idx2) + "," + userId + "),";
                                w1.append(sql);
                            }
                        } else if (idx <= 9750) {//五阶：9720-9750
                            //rank = 5;
                            for (long idx2 = 1; idx2 <= 40; idx2++) {//有40个4阶,全部加入5阶,
                                String sql = "(NULL, " + (zoneUserIdStart + 9750 + idx2) + "," + userId + "),";
                                w1.append(sql);
                            }
                        } else if (idx <= 9790) {//四阶：9750-9790
                            //rank = 4;
                            for (long idx2 = 1; idx2 <= 50; idx2++) {//有50个3阶,全部加入4阶,
                                String sql = "(NULL, " + (zoneUserIdStart + 9790 + idx2) + "," + userId + "),";
                                w1.append(sql);
                            }
                        } else if (idx <= 9840) {//三阶：9790-9840
                            //rank = 3;
                            for (long idx2 = 1; idx2 <= 60; idx2++) {//有60个2阶,全部加入3阶,
                                String sql = "(NULL, " + (zoneUserIdStart + 9840 + idx2) + "," + userId + "),";
                                w1.append(sql);
                            }
                        } else if (idx <= 9900) {//二阶：9840-9900
                            //rank = 2;
                            for (long idx2 = 1; idx2 <= 100; idx2++) {//有100个1阶,全部加入2阶,
                                String sql = "(NULL, " + (zoneUserIdStart + 9900 + idx2) + "," + userId + "),";
                                if (idx2 == 100 && idx == 9900 && zoneId == 10 && corpId == 50)
                                    sql = sql.substring(0, sql.length() - 1);
                                w1.append(sql);
                            }
                        } else if (idx <= 10000) {//一阶：9900-10000
                            //rank = 1;
                        }
                    }
                    userId++;
                }
            }
        }
        closeStream();
    }

    /**
     * 每个用户一个token 5百万
     */
    public static void rtv_token() throws Exception {
        createFile("rtv_token", user_count);
        long userId = user_id_start;
        while (userId <= user_id_end) {
            String sql = "(" + userId + ", 'ec25339660da4b66907a61047a0e3d43000" + userId + "', " + userId + ", '2017-10-11 18:01:37', '2017-10-11 18:01:37', '2301-10-10 18:01:37'),";
            if (userId == user_id_end)
                sql = sql.substring(0, sql.length() - 1);
            w1.append(sql);
            userId++;
        }
        closeStream();
    }

    /**
     * 每个用户一个配置
     */
    public static void rtv_bms_params() throws Exception {
        createFile("rtv_bms_params", user_count);
        long userId = user_id_start;
        while (userId <= user_id_end) {
            int corpId = 1;
            String sql = "(" + userId + "," + userId + ", " + corpId
                    + ", 100, '{\"vpull_type_15\":1073741826,\"gps_interval_2\":1073741854,\"basic_bms_pri_12\":1073741827,\"gps_query_3\":1073741825,\"gps_report_1\":1073741825}'),";
            if (userId == user_id_end)
                sql = sql.substring(0, sql.length() - 1);
            w1.append(sql);
            userId++;
        }
        closeStream();
    }

    public static void rtv_console_cop_mapping() throws Exception {
        createFile("rtv_console_cop_mapping", user_count);
        long idx = user_id_start;
        while (idx <= user_id_end) {
            String sql = "(NULL," + 1 + "," + idx + "),";
            appendSql(idx, sql);
            idx++;
        }
        closeStream();
    }

    /**
     * 1百万个组
     */
    public static void rtv_group() throws Exception {
        createFile("rtv_group", group_count);
        String gname = null;
        long idx = user_id_start;
        while (idx <= user_id_end) {
            gname = "dcg" + idx;
            String sql = "(NULL,2," + idx + ",'" + gname + "','2016-05-30 14:04:32',1,0,1,1,1,1,0),";
            if (idx == user_id_end)
                sql = sql.substring(0, sql.length() - 1);
            w1.append(sql);
            idx++;
        }
        closeStream();
    }

    public static void rtv_group_dmr() {

    }

    /**
     * 每个zone的前10000个用户加入1000个组
     */
    public static void rtv_user_group_map() throws Exception {
        createFile("rtv_user_group_map", user_count);
        long idx = user_id_start;
        long count = user_id_end + 20000 * 2;
        while (idx <= count) {
            String sql = "(NULL," + idx + "," + idx + ",5),";
            if (idx == count)
                sql = sql.substring(0, sql.length() - 1);
            w1.append(sql);
            idx++;
        }
        closeStream();
    }

    public static void rtv_console_user() {

    }

    public static void rtv_console_user_mapping() throws Exception {
        createFile("rtv_console_user_mapping", user_count);
        long idx = user_id_start;
        while (idx <= user_id_end) {
            String sql = "(NULL," + idx + ", 1),";
            appendSql(idx, sql);
            idx++;
        }
        closeStream();
    }

    public static void rtv_nvr() {

    }

    public static void rtv_rtv_external_camera() {

    }

    public static void rtv_fence() {

    }

    public static void rtv_version_map() {

    }

    public static void rtv_version_user_map() {

    }

    public static void rtv_plan_rule() {

    }

    /**
     * 每个zone的前10000个用户1000条数据
     */
    public static void rtv_rtpictures() throws Exception {
        createFile("rtv_rtpictures", media_count);
        long idx = user_id_start;
        while (idx <= user_id_end) {
            String sql = "(NULL, " + idx
                    + ", '{\"addr\":\"天目路61-63号;保利·香槟国际\",\"desc\":\"\"}', 'http://101.201.45.198:8081/rtv/rtpictures/2017-06-16/hd_103_1497586068675_uid_69577_2017-06-16_12-07-38_ini.jpg', 'http://101.201.45.198:8081/rtv/rtpictures/2017-06-16/small_103_1497586068675_uid_69577_2017-06-16_12-07-38_ini.jpg', 'http://101.201.45.198:8081/rtv/rtpictures/2017-06-16/large_103_1497586068675_uid_69577_2017-06-16_12-07-38_ini.jpg', '2017-06-16 12:07:51', '1'),";
            appendSql(idx, sql);
            idx++;
        }
        closeStream();
    }

    /**
     * 每个zone的前10000个用户1000条数据
     */
    public static void rtv_video() {
        //video_record_table
    }

    /**
     * 每个zone的前10000个用户1000条数据
     */
    public static void rtv_gps_info() throws Exception {
        createFile("rtv_gps_info", user_count);
        long idx = user_id_start;
        while (idx <= user_id_end) {
            String sql = "(NULL, " + idx + ", '20170428', '', 1),";
            appendSql(idx, sql);
            idx++;
        }
        closeStream();
    }

    /**
     * 100万数据
     */
    public static void rtv_operation_log() throws Exception {
        createFile("rtv_operation_log", user_count);
        long idx = user_id_start;
        while (idx <= user_id_end) {
            String sql = "(NULL, 'rtv_user', '修改用户', '{\"id\":\"69554\",\"nickname\":\"BMS18\",\"phone\":\"19900004018\",\"userPriority\":\"1\",\"zoneId\":\"1\",\"consoleIdAddAry\":\"[]\",\"consoleIdDelAry\":\"[]\",\"groupIdAddAry\":\"[]\",\"groupIdDelAry\":\"[]\"}', '[{\"val_cn_old\":\"20\",\"key_cn\":\"用户优先级\",\"val_new\":\"1\",\"key_en\":\"priority\",\"val_cn_new\":\"1\",\"val_old\":\"20\"}]', '<thead><tr role=\"row\"><th class=\"col-xs-3 sorting_disabled\">名称</th><th class=\"col-xs-3 sorting_disabled\">旧数据</th><th class=\"col-xs-6 sorting_disabled\">新数据</th></tr></thead><tbody><tr role=\"row\" class=\"even\"><td>用户优先级</td><td>20</td><td>1</td></tr></tbody>', '171.217.97.157', '2017-05-08 17:56:12', 'update', 'default', NULL, '1', 'admin', 'zom', '"
                    + idx
                    + "-BMS18', NULL, '1', 'admin', 'zom', '@Around', '1', '{\"status\":\"success\",\"_resultType\":\"_result_obj\",\"code\":\"operation_success\",\"msg\":\"操作成功\",\"success\":\"success\"}', NULL),";
            appendSql(idx, sql);
            idx++;
        }
        closeStream();
    }

    public static void rtv_dlmsg_storage() throws Exception {
        createFile("rtv_dlmsg_storage", user_count);
        long idx = user_id_start;
        while (idx <= user_id_end) {
            String sql = "(NULL, " + idx + ", " + idx + ", " + (idx + 1) + ", '0', '0', '0', '1494679262', '1494679262', '0', '29', '5LmI5LmI5LmI5LmI5aaI5aaI5ZKq\n'),";
            appendSql(idx, sql);
            idx++;
        }
        closeStream();
    }

    public static void rtv_ulmsg_storage() throws Exception {
        createFile("rtv_ulmsg_storage", user_count);
        long idx = user_id_start;
        while (idx <= user_id_end) {
            String sql = "(" + idx + ", " + idx + ", " + idx + ", " + (idx + 1) + ", '0', '0', '0', '1494515625', '1494515625', '13', '5L2g5aW9cQ==\n'),";
            appendSql(idx, sql);
            idx++;
        }
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
        System.out.println(" ---------- " + table + " ---------- ");
        fname = output_dir + table + ".sql";
        System.out.println("生成文件开始：" + fname + ", total：" + total);
        file1 = new File(fname);
        w1 = new FileWriter(file1);
        w1.append(buildPrefixSql(table));
        sqlCmd = "mysql -proot -uroot < " + fname;
    }

    private static void createFile2(String table, long total) throws Exception {
        System.out.println(" ---------- " + table + " ---------- ");
        fname2 = output_dir + table + ".sql";
        System.out.println("生成文件开始：" + fname2 + ", total：" + total);
        file2 = new File(fname2);
        w2 = new FileWriter(file2);
        w2.append(buildPrefixSql(table));
        //sqlCmd2 = "mysql -proot -uroot < " + fname2;
    }

    private static String buildPrefixSql(String table) {
        return "USE " + dbname + ";TRUNCATE TABLE `" + table + "`;INSERT INTO `" + table + "` VALUES ";
    }

    private static void appendSql(long idx, String sql) throws Exception {
        w1.append(sql);
    }

    private static void closeStream() throws Exception {
        System.out.println("生成文件结束：" + fname);
        w1.flush();
        w1.close();
        excuteSql(fname);//执行sql脚本文件
    }

	/*private static void closeStream2() throws Exception {
		System.out.println("生成文件结束：" + fname2);
		w2.flush();
		w2.close();
		excuteSql(fname2);//执行sql脚本文件
	}*/

    private static void excuteSql(String fname) throws Exception {
        fname = fname.replace(".sql", ".bat");
        FileWriter w = new FileWriter(new File(fname));
        w.append(sqlCmd);
        w.flush();
        w.close();
        System.out.println("执行批处理：" + sqlCmd);
        Runtime.getRuntime().exec("cmd.exe /k start " + fname);
    }

	/*private static void excuteSql2(String fname) throws Exception {
		fname = fname.replace(".sql", ".bat");
		FileWriter w = new FileWriter(new File(fname));
		w.append(sqlCmd2);
		w.flush();
		w.close();
		System.out.println("执行批处理：" + sqlCmd2);
		Runtime.getRuntime().exec("cmd.exe /k start " + fname);
	}*/

    //	public static void printMessage(InputStream input) {
    //		new Thread(new Runnable() {
    //			@Override
    //			public void run() {
    //				Reader reader = new InputStreamReader(input);
    //				BufferedReader bf = new BufferedReader(reader);
    //				String line = null;
    //				try {
    //					while ((line = bf.readLine()) != null) {
    //						System.out.println(line);
    //					}
    //				} catch (IOException e) {
    //					e.printStackTrace();
    //				} finally {
    //					try {
    //						input.close();
    //						reader.close();
    //						bf.close();
    //					} catch (IOException e) {
    //						e.printStackTrace();
    //					}
    //				}
    //			}
    //		}).start();
    //	}

	/*private void buldSql(String table, String prefixSql, Long start, Long count, String sql) throws Exception {
		createFile(table, prefixSql);
		long idx = null == user_id_start ? user_id_start : start;
		count = null == user_id_end ? user_id_end : count;
		while (idx <= user_id_end) {
			w1.append(sql);
			idx++;
		}
		closeStream();
	}*/
}
