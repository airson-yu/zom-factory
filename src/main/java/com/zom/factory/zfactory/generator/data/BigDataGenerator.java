package com.zom.factory.zfactory.generator.data;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zom.factory.zfactory.generator.data.model.CorpUser;
import com.zom.factory.zfactory.generator.data.model.ZoneIdAssign;

/**
 * 生成百万或千万级别的测试数据
 *
 * @author airson
 */
public class BigDataGenerator {

    //数据库应该初始化的数据 : rtv_plan_event, rtv_plan_action

    /**
     * 程序运行 环境
     */
    private static boolean windows = false;

    private static boolean showCmd = false;

    private static boolean doSql = false;

    /**
     * sql脚本文件的存放目录
     */
    //private static final String output_dir = windows ? "E:\\zlab\\sql\\" : "/root/scripts_zom/sql/";    //"/var/scripts_zom/sql/"
    //private static final String file_dir   = windows ? "E:\\zlab\\file\\" : "/root/scripts_zom/file/";    //"/home/itcit/file/"
    private static final String output_dir = "/Users/airsonyu/Downloads/tmp/sql/";    //"/var/scripts_zom/sql/"
    private static final String file_dir   = "/Users/airsonyu/Downloads/tmp/file/";    //"/home/itcit/file/"
    /**
     * 数据库配置
     */
    //private static final String	dbUrl		= windows ? "localhost" : "101.201.45.198";
    //private static final String dbname     = windows ? "zlab" : "rtvitrunk_test";
    private static final String dbname     = windows ? "zlab" : "rtvitrunk";//rtv_tmp
    private static final String jdbc_user  = windows ? "root" : "root";
    //private static final String jdbc_pswd  = windows ? "root" : "Xyz@Itr132!";
    private static final String jdbc_pswd  = windows ? "root" : "vfr47ujm";

    /** 默认值 : 数据的起始ID */
    //private static final long user_id_start = 65537;
    /**
     * 默认值 : 数据的结束ID，与起始ID相减得数据的总条数
     */
    //private static final long user_id_end = user_id_start + user_count;

    // --- 数据规模配置 ---
    private static boolean sm = true;

    private static final long zone_count  = sm ? 50 : 255;
    private static final long corp_count  = sm ? 50 : 500;
    private static final long user_count  = sm ? 200000 : 2000000;    //user,token,bms_params,version_user
    private static final long group_count = sm ? 200000 : 2000000;
    private static final long media_count = sm ? 200000 : 5000000;    //video,picture,gps_info
	/*private static final long	domain_count	= sm ? 200000 : 5000000;	//plan_rule,operation_log,dlmsg,ulmsg
	private static final long	fence_count		= sm ? 200000 : 5000000;	//fence
	private static final long	nvr_count		= sm ? 200000 : 5000000;
	private static final long	cameral_count	= sm ? 200000 : 5000000;
	private static final long	version_count	= sm ? 2000 : 50000;*/

    /**
     * 保存255个zone的数据，新增用户时从中取出用户ID值
     */
    private static Map<Integer, ZoneIdAssign> zmap = new HashMap<>();

    private static Map<Long, CorpUser> corp_user_map = new HashMap<>();

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

        //step
        rtv_zone();
        rtv_corp();
        rtv_system_param();
        //rtv_3rdkey();
        rtv_corp_zone_map();
        rtv_user();

        //if (2 > 1)return;

        rtv_user_admin_mapping();
        rtv_console_cop_mapping();
        //rtv_bms_params();
        rtv_token();
        rtv_group();
        rtv_group_dmr();
        rtv_user_group_map();
        rtv_console_user();
        rtv_console_user_mapping();

        //step
		/*baseDataInsert = false;
		rtv_zone();//只缓存数据
		rtv_user();//只缓存数据
		*/

        rtv_version_map();
        rtv_version_user_map();

        /*rtv_nvr();
        rtv_external_camera();
        rtv_fence();
        rtv_plan_rule();
        rtv_plan_rule_user_map();*/

        //step
		/*baseDataInsert = false;
		rtv_zone();//只缓存数据
		rtv_user();//只缓存数据
		*/

        /*rtv_rtpictures();
        rtv_video();
        rtv_gps_info();*/

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

        //rtv_operation_log();
        //rtv_dlmsg_storage();
        //rtv_ulmsg_storage();

        System.out.println("---------- final_operation begin ---------- ");
        final_operation();

        System.out.println("---------- over over over ---------- ");
    }

    public static void final_operation() throws Exception {
        createFile("final_operation", 1000, false);
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
        int uid_start_gap = 1000;//0;
        while (id <= 255) {
            ZoneIdAssign zone = new ZoneIdAssign();
            zone.setMaxUid((long) ((id << 16) + 0x1FFF));
            zone.setMaxTgid((long) ((id << 16) + 0xFFFF));
            //zone.setCurUid((long) ((id << 16) + 1));
            zone.setCurUid((long) ((id << 16) + 1) + uid_start_gap);
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
     * INSERT INTO `rtvitrunk`.`rtv_corp_table`(`id`, `username`, `corp_name`, `corp_password`, `email`, `phone`, `register_date`, `expire_date`, `last_logon_date`,
     * `last_logon_ip`, `permission_level`, `priority_level`, `max_user`, `max_group`, `max_console`, `max_user_group`, `status`, `imeichk`, `sh_enable`, `asrecord`,
     * `gps_report`, `gps_interval`, `imei_bind`, `door_control`, `catt_modle`, `video_push_num`, `params`, `note`,
     * `expire_operation`, `expire_way`, `allow_modifi_iccid`, `allow_expire_update`)
     * VALUES (2, 'corpuser', 'corpuser', 'e10adc3949ba59abbe56e057f20f883e', 'airson_yu@163.com', '19911112222', '2020-12-09 11:54:06', NULL, '1900-01-01 00:00:00',
     * NULL, 0, 0, 100, 100, 5, 50, 1, 0, 0, 0, 1, 30, 0, 0, 0, 9, '{\"pstngw_no\":0,\"roipgw_no\":0,\"camera\":1}',
     * NULL, 1, 1, 1, 2);
     */
    public static void rtv_corp() throws Exception {
        createFile("rtv_corp_table", 1000);
        long id = 1;
        long phone = 12700000000l;
        while (id <= 1000) {
            String sql_10 = "(" + id + ", 'corp" + id + "', 'corpname" + id + "', '4297f44b13955235245b2497399d7a93', 'corp" + id + "@01more.com', '" + (phone++)
                    + "','2017-01-01 13:30:30', '2050-12-31 00:00:00', '2017-10-01 14:40:40', '182.160.45.01'";
            String sql_20 = ",1,1,100000,100000,100000,100000,1,1,1,0";
            String sql_30 = ",1,30,0,0,0,5,'{\"camera\":1}',NULL, 1, 1, 1, 2),";
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
     * 每个公司一条license
     * INSERT INTO `rtvitrunk`.`rtv_system_param`(`id`, `system_name`, `email`, `phone`, `expired_date`, `max_user`, `max_group`, `max_console`, `max_user_group`, `status`,
     * `gps_license`, `video_push_num`, `dc_license`, `sh_license`, `params`, `lc`, `hak`, `updatetime`)
     * VALUES (1, '测试123_56_126_189', '12305@163.com', '18631477413', '2021-09-30 00:00:00', 200000, 20000, 500, 20000, 1, 1, 256, 0, 0,
     * '{\"corpn\":5,\"mnc\":103,\"seller_name\":\"测试部门测试使用\",\"mcc\":460,\"camera_license\":1,\"dmr_license\":1,\"server_type\":\"MZ1K\",\"tech_provider\":\"成都零壹众科技有限公司\"}',
     * 'SA24V4se6BLGiTJhQRFBVzAAP71LRtJ8wG++oIGuM1o0XhN286I1aJiydD5B491tseDVHjqgqQRtqfC1Y1VYSFBu2PBS4TQOIOkW8kRbZP3QuRhMFSupXtlWCVPeczhMrt0a9b4JB6wetNKz2r9dwLCu0YFTqy8BFkg8D6CtLdN7RfbK1/KIbJ1vdRbW79VrDTfuIeCGpJPq8xo1OE/R/uIIkSPJ7VlXCLpuc0c8Us8VWSkFlzwb4F9TnBIOWYyXa29pg5R9NxwLFpBpZMKS+c4joGEY4nK2LDNY974dkFkfjXSQXGlZ2kzDqjPLmNsOTXPlEk5trv/mhAP0n7Ms8wVT48pcTdm+5rCZ6YuyJTWxFdluF+sROOCi+z36WFvNUZA6WET/tOnkISQU7tMA4BQ35BhElRtpX07RbqJ9MylXJU7fue1AC7ePjZKh0XWMSX1J/vZ0GY/7OQcqzAVMIJ0seADdNWsV5NvpXsAyRjCprt9e4P0rR+YTlqTnChCNI4SKUl8niPu8+qSC3KkH481Y3kuwS4YSTx0q+ksSqgRSBsPTonbdQKvrs9CSkI7u9aS9CjdUWZaNlVlldmdVa1sB703yjsXGMF1wnxcoOvWqHHGBE/EF6rxxYHQrWMyIQ9sh9AqUKptDvNVJdTd0AN96xEOqtvVzWQsmLQ/vT11q8yuPUleyLs3WwpnIKsshTe5sqq1oDOqOa7YPxX2esldT4xxSNihJ2TjIIA6gaGuMP+6690fOSmG6ttE24/veL32sAqsKTtosLQxzLV0aEtBi/Ebd7lD7DR0AGB8+bicLY1s7Ysv89Z9KMXvs/qVxnVu7mBY6iIi1idVJkqvBLfS+pOZzwrVvZaRr8Qot6IV7RhYVWnyD9rvZVCIWv0I4WPEF5lSm0RIevoxNlg7BmeeAp+WED+94oQc155TaUjZBzd4dKh4M4XU+shD9g3KKEF3fuHrVPJfcQGCDLtEzrTofJsQyZJxBcKnB4HpHL3F3n+nV3lLhP3axsXqmb5Cn', '1', '2021-03-02 15:26:16');
     */
    public static void rtv_system_param() throws Exception {
        createFile("rtv_system_param", 1000);
        long id = 1;
        long phone = 11700000000l;
        while (id <= 1000) {
            String sql_10 = "(" + id + ", 'corp" + id + "', 'corp" + id + "@163.com', '" + (phone++)
                    + "', '2050-12-31 00:00:00', '100000000', '100000000', '100000000', '100000000', '1', '1', '50000', '1', '1'";
            String sql_20 = ",'{\"camera_license\":1,\"corpn\":" + id + ",\"dmr_license\":1,\"mcc\":460,\"mnc\":1,\"seller_name\":\"成都零壹众科技有限公司\",\"tech_provider\":\"成都零壹众科技有限公司\"}'";
            String sql_30 = ",'SA24V4se6BLGiTJhQRFBVzAAP71LRtJ8wG++oIGuM1o0XhN286I1aJiydD5B491tseDVHjqgqQRtqfC1Y1VYSFBu2PBS4TQOIOkW8kRbZP3QuRhMFSupXtlWCVPeczhMrt0a9b4JB6wetNKz2r9dwLCu0YFTqy8BFkg8D6CtLdN7RfbK1/KIbJ1vdRbW79VrDTfuIeCGpJPq8xo1OE/R/uIIkSPJ7VlXCLpuc0c8Us8VWSkFlzwb4F9TnBIOWYyXa29pg5R9NxwLFpBpZMKS+c4joGEY4nK2LDNY974dkFkfjXSQXGlZ2kzDqjPLmNsOTXPlEk5trv/mhAP0n7Ms8wVT48pcTdm+5rCZ6YuyJTWxFdluF+sROOCi+z36WFvNUZA6WET/tOnkISQU7tMA4BQ35BhElRtpX07RbqJ9MylXJU7fue1AC7ePjZKh0XWMSX1J/vZ0GY/7OQcqzAVMIJ0seADdNWsV5NvpXsAyRjCprt9e4P0rR+YTlqTnChCNI4SKUl8niPu8+qSC3KkH481Y3kuwS4YSTx0q+ksSqgRSBsPTonbdQKvrs9CSkI7u9aS9CjdUWZaNlVlldmdVa1sB703yjsXGMF1wnxcoOvWqHHGBE/EF6rxxYHQrWMyIQ9sh9AqUKptDvNVJdTd0AN96xEOqtvVzWQsmLQ/vT11q8yuPUleyLs3WwpnIKsshTe5sqq1oDOqOa7YPxX2esldT4xxSNihJ2TjIIA6gaGuMP+6690fOSmG6ttE24/veL32sAqsKTtosLQxzLV0aEtBi/Ebd7lD7DR0AGB8+bicLY1s7Ysv89Z9KMXvs/qVxnVu7mBY6iIi1idVJkqvBLfS+pOZzwrVvZaRr8Qot6IV7RhYVWnyD9rvZVCIWv0I4WPEF5lSm0RIevoxNlg7BmeeAp+WED+94oQc155TaUjZBzd4dKh4M4XU+shD9g3KKEF3fuHrVPJfcQGCDLtEzrTofJsQyZJxBcKnB4HpHL3F3n+nV3lLhP3axsXqmb5Cn', '1', '2021-03-02 15:26:16'),";
            String sql = sql_10 + sql_20 + sql_30;
            if (id == 1000)
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
     * INSERT INTO `rtvitrunk`.`rtv_3rdkey`(`id`, `api_key`, `security_key`, `corp_id`, `home`, `extjson`, `platform_name`, `platform_code`, `expire_time`, `ts_valid_minutes`,
     * `state`, `auth_level`, `allow_apis`, `remark`, `update_time`)
     * VALUES (35, 'CFEACEC79961AEB076658D8CB58E4016', 'B288F0D93A06AF708AC9E7A2359F93FCABC48507ABF1D97DBE65432AFF5456C3', 1, NULL, NULL, '信虹-大运会', 'xh_dyh', NULL, 0, 1, 1, NULL, NULL, '2021-02-09 11:30:17');
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
     * sm=true:前5个公司各占满二个zone:5*8000*2=80000,6-10的公司各占满一个zone:5*8000=40000,1-50的公司每5个公司占满一个zone(每公司1600)，共占10个zone : 10*8000=80000,总共2十万个用户
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
        if (baseDataInsert) {
            closeStream();
        }
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
        CorpUser corpUser = corp_user_map.get(corpId);
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
                // XXX DONE 更好的方案 : 按公司来配置console,每个公司 : 31个console(1个7阶，2个6阶，3个5阶，4个4阶，5个3阶，6个2阶，10个1阶)
                List<Long> con_7_list = corpUser.getCon7List();
                if (null != con_7_list && con_7_list.size() > 0) {//当前公司已经有7阶调度台，说明已经完成了console的创建，后续不再创建
                    //用户
                    corpUser.getUserList().add(userId);
                    corpUser.getUserZoneMap().put(userId, zoneId);
                } else {
                    //创建调度台
                    if (turnIdx <= 1579) {//一阶10个 : 70-79
                        rank = 1;
                        corpUser.getCon1List().add(userId);
                    } else if (turnIdx <= 1585) {//二阶6个 : 80-85
                        rank = 2;
                        corpUser.getCon2List().add(userId);
                    } else if (turnIdx <= 1590) {//三阶5个 : 86-90
                        rank = 3;
                        corpUser.getCon3List().add(userId);
                    } else if (turnIdx <= 1594) {//四阶4个 : 91-94
                        rank = 4;
                        corpUser.getCon4List().add(userId);
                    } else if (turnIdx <= 1597) {//五阶3个 : 95-97
                        rank = 5;
                        corpUser.getCon5List().add(userId);
                    } else if (turnIdx <= 1599) {//六阶2个 : 98-99
                        rank = 6;
                        corpUser.getCon6List().add(userId);
                    } else if (turnIdx <= 1600) {//七阶1个 : 100
                        rank = 7;
                        corpUser.getCon7List().add(userId);
                    }
                    dname = "con_" + rank + "_" + userId;
                    corpUser.getConsoleZoneMap().put(userId, zoneId);//缓存调度台和ZONE的关联
                }
            } else {//用户
                corpUser.getUserList().add(userId);
                corpUser.getUserZoneMap().put(userId, zoneId);
            }

            //INSERT INTO `rtvitc`.`rtv_user` (`id`, `display_name`, `user_password`, `client_version`, `device`, `img_url`, `phone`, `register_date`, `last_logon_date`, `last_access_date`,
            //`last_logon_ip`, `logon_state`, `salt`, `ts_profile`, `ts_group`, `rank`, `admin_id`, `dcg_id`, `corp_id`, `logon_name`,
            //`priority`, `status`, `preconfig`, `default_grp`, `gps_report`, `gps_interval`, `adm_ts`, `zone_id`, `ats`, `pts`,
            //`imei`, `lmr_uid`, `ext_did`,extension_property)
            //VALUES ('65537', 'u1', 'df', '2.5.1', 'web', NULL, '19900000001', '2016-05-30 14:04:31', '2017-08-29 17:49:19', '2017-08-29 17:49:19',
            //NULL, 'online', 'c996f03e8c8f4c09', '6', '4', '0', '0', '65537', '1', 'u11',
            //'1', '1', '1', '0', '1', '30', '0', '1', '0', '0',
            //'', NULL, NULL);

            // 字段更新 2021年03月11日10:05:00：
            //INSERT INTO `rtv_tmp`.`rtv_user`(`id`, `display_name`, `user_password`, `client_version`, `device`, `img_url`, `phone`, `register_date`, `last_logon_date`, `last_access_date`,
            // `last_logon_ip`, `logon_state`, `salt`, `ts_profile`, `ts_group`, `rank`, `admin_id`, `dcg_id`, `corp_id`, `logon_name`,
            // `priority`, `status`, `preconfig`, `default_grp`, `gps_report`, `gps_interval`, `adm_ts`, `zone_id`, `ats`, `pts`,
            // `imei`, `lmr_uid`, `ext_did`, `extension_property`,
            // `code`, `unit_id`, `department_id`, `original_name`, `unit_fk_id`, `department_fk_id`, `iccid`, `logon_type`, `last_update_time`, `joinlinkage`, `role_id`, `nfc`, `ext`, `create_way`,
            // `user_role_id`, `expire_date`, `abptype`, `hardware_bind_time`, `hardware_logo`, `current_name`, `con_visible_level`, `sign_in`, `sync_strategy`, `pinyin`, `pinyin_abbr`, `sms_enable`)
            // VALUES (65542, '王青青', 'jl/dY7ubkhhqjIpM9eY7PQ==', NULL, 'portable', NULL, '', '2020-12-08 19:03:19', NULL, NULL, NULL, 'offline', 'ssss', 13, 1, 0, NULL, 65542, 1, 'wangqq', 3, 1, 1, 0, 1, 30, 0, 1, 0, 0,
            // '860772037907741', 65542, '2c91facf75f3b6fc0175f5069969000e',NULL,
            // NULL, '1', '110', '王青青', 1, 15, NULL, 0, '2020-12-10 11:00:21', 0, 31, NULL, NULL, 1, NULL, NULL, 'sphone', NULL, NULL, NULL, 2, 1, 1, 'wangqingqing', 'wqq', 0);

            if (baseDataInsert) {
                String sql_10 = "(" + userId + ",'" + dname + "','df',NULL,'portable',NULL,'" + (phone++) + "','2017-09-30 14:16:00','2017-09-30 14:16:00',NULL";
                String sql_20 = ",NULL,'online','245956046a76a3a8',100,100," + rank + ",0," + userId + "," + corpId + ",'" + lname;
                String sql_30 = "',1,1,1,0,1,30,0," + zoneId + ",0,0,";
                String sql_40 = "'',NULL," + userId + ",'{\"oc\":" + (userId % 2) + "}',";//oc:0,1
                String sql_50 = "NULL, '1', '1', '" + dname + "', 1, 1, NULL, 0, '2021-03-11 10:10:21', 0, 1, NULL, NULL, 1, NULL, NULL, 'sphone', NULL, NULL, NULL, 2, 1, 1, 'pinyin', 'py', 0),";//code - sms_enable
                String sql = sql_10 + sql_20 + sql_30 + sql_40 + sql_50;
                if (phone == (sm ? 13700200000l : 13702000000l)) {//XXX 通过手机号来判断是否为最后一个新增用户
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
     * sm=true:前5个公司各占满二个zone:5*8000*2=80000,6-10的公司各占满一个zone:5*8000=40000,1-50的公司每5个公司占满一个zone(每公司1600)，共占10个zone : 10*8000=80000,总共2十万个用户
     * sm=false:前50个公司各占满二个zone:50*8000*2=800000,51-100的公司各占满一个zone:50*8000=400000,1-500的公司每5个公司占满一个zone(每公司1600)，共占100个zone : 100*8000=800000,总共二百万个用户
     */
    public static void rtv_user_admin_mapping() throws Exception {

        createFile("rtv_user_admin_mapping", user_count);

        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> con1List = cu.getCon1List();
            List<Long> con2List = cu.getCon2List();
            List<Long> con3List = cu.getCon3List();
            List<Long> con4List = cu.getCon4List();
            List<Long> con5List = cu.getCon5List();
            List<Long> con6List = cu.getCon6List();
            List<Long> con7List = cu.getCon7List();

            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            int userSize = userList.size();
            //int con1Size = con1List.size();

            System.out.println("corpId : " + cacheCorp + ", userSize : " + userSize + ", conAllList : " + conAllList.size());

            //20%的用户轮流加入2-7阶,进行直管
            for (int idx = 1; idx <= userSize * 0.2; idx++) {
                long userId = userList.get(idx);
                for (long con : conAllList) {
                    String sql = "(NULL, " + userId + "," + con + "),";
                    w1.append(sql);
                }
            }
			/*//剩余80%的用户全部加入前4个1阶
			for (int idx = (int) (userSize * 0.2 + 1); idx < userSize; idx++) {
				long userId = userList.get(idx);
				for (int idx2 = 0; idx2 < 4; idx2++) {
					long con1 = con1List.get(idx2);
					String sql = "(NULL, " + userId + "," + con1 + "),";
					w1.append(sql);
				}
			}
			//剩余80%的用户轮流加入4以后的1阶
			for (int idx = (int) (userSize * 0.2 + 1); idx < userSize; idx++) {
				long userId = userList.get(idx);
				int seq = 4;
				if (seq >= con1Size) {
					seq = 4;
				}
				long con1 = con1List.get(seq);
				String sql = "(NULL, " + userId + "," + con1 + "),";
				w1.append(sql);
				seq++;
			}*/

            //XXX 实际业务场景 : 一个调度台推荐不超过2000个成员
            //剩余80%的用户轮流加入前5个1阶，1阶成员最多为 : (8000*2+1600)/5=3520
            for (int idx = (int) (userSize * 0.2 + 1); idx < userSize; idx++) {
                long userId = userList.get(idx);
                int seq = 0;
                if (seq >= 5) {
                    seq = 0;
                }
                long con1 = con1List.get(seq);
                String sql = "(NULL, " + userId + "," + con1 + "),";
                w1.append(sql);
                seq++;
            }

            //所有1阶加入所有2阶
            for (long con1 : con1List) {
                for (long con2 : con2List) {
                    String sql = "(NULL, " + con1 + "," + con2 + "),";
                    w1.append(sql);
                }
            }
            //所有2阶加入所有3阶
            for (long con2 : con2List) {
                for (long con3 : con3List) {
                    String sql = "(NULL, " + con2 + "," + con3 + "),";
                    w1.append(sql);
                }
            }
            //所有3阶加入所有4阶
            for (long con3 : con3List) {
                for (long con4 : con4List) {
                    String sql = "(NULL, " + con3 + "," + con4 + "),";
                    w1.append(sql);
                }
            }
            //所有4阶加入所有5阶
            for (long con4 : con4List) {
                for (long con5 : con5List) {
                    String sql = "(NULL, " + con4 + "," + con5 + "),";
                    w1.append(sql);
                }
            }
            //所有5阶加入所有6阶
            for (long con5 : con5List) {
                for (long con6 : con6List) {
                    String sql = "(NULL, " + con5 + "," + con6 + "),";
                    w1.append(sql);
                }
            }
            //所有6阶加入所有7阶
            for (long con6 : con6List) {
                for (long con7 : con7List) {
                    String sql = "(NULL, " + con6 + "," + con7 + "),";
                    w1.append(sql);
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

        String params = "{\"basic_bms_pri_12\":1073741825,\"door_ctrl_5\":1073741825,\"gps_interval_2\":\"1073741854\",\"gps_query_3\":1073741825,\"gps_report_1\":1073741825,\"imei_validate_4\":1073741825,\"new_tg_pri_11\":1073741825,\"ptt_lock_pri_13\":1073741825,\"secure_desktop_6\":1073741825,\"sms_upload_10\":1073741825,\"tg_owner_pri_14\":1073741825,\"video_upload_21\":1073741825,\"visible_attach_7\":1073741825,\"vpull_type_15\":1073741826,\"vstorage_20\":1073741826,\"cc_22\":0,\"power_23\":0}";

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
                String sql = "(" + userId + ", 'ec25339660da4b66907a61047a0e3d4user" + userId + "', " + userId + ", '2017-10-11 18:01:37', '2017-10-11 18:01:37', '2301-10-10 18:01:37'),";
                w1.append(sql);
            }
            for (long userId : conAllList) {
                String sql = "(" + userId + ", 'ec25339660da4b66907a61047a0e3d43con" + userId + "', " + userId + ", '2017-10-11 18:01:37', '2017-10-11 18:01:37', '2301-10-10 18:01:37'),";
                w1.append(sql);
            }
        }

        String sql = "(0, '0', 0, '2017-10-11 18:01:37', '2017-10-11 18:01:37', '2301-10-10 18:01:37')";
        w1.append(sql);
        closeStream();
    }

    /**
     * 用户组
     * INSERT INTO `rtvitrunk`.`rtv_group`(`id`, `group_ts`, `owner_id`, `group_name`, `create_date`, `last_update_time`, `corp_id`, `rank`, `dcg`, `preconfig`, `status`, `zone_id`,
     * `console_dtg`, `admin_id`, `ext_tgid`,
     * `parent_type`, `parent_id`, `priority`, `timer`, `type`, `ext`, `plan_del_time`, `gw_flag`, `pinyin`, `pinyin_abbr`)
     * VALUES (100254, 10, 0, '汉东省衢州监狱组织人事科', '2020-06-10 09:45:54', '2020-09-24 17:42:15', 1, 0, 0, 1, 1, 1, 0, NULL, '',
     * 1, 2206, 3, NULL, 0, NULL, NULL,0, 'handongshengquzhoujianyuzuzhirenshike', 'hdsqzjyzzrsk');
     */
    public static void rtv_group() throws Exception {
        createFile("rtv_group", group_count);
        String gname = null;

        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶
            //每个user一个group - userId 与 groupId一致
            for (long userId : userList) {
                gname = "groupbms" + userId;
                long zoneId = corp_user_map.get(cacheCorp).getUserZoneMap().get(userId);
                String sql = "(" + userId + ",100," + userId + ",'" + gname + "', '2017-10-11 14:04:32', '2021-03-28 14:04:32', " + cacheCorp + ",1,0,1,1," + zoneId + ",0,NULL,NULL,1, 1, 3, NULL, 0, NULL, NULL,0, 'pinyin', 'py'),";
                w1.append(sql);
            }
            for (long userId : conAllList) {
                gname = "groupcon" + userId;
                long zoneId = corp_user_map.get(cacheCorp).getConsoleZoneMap().get(userId);
                String sql = "(" + userId + ",100," + userId + ",'" + gname + "', '2017-10-11 14:04:32', '2021-03-28 14:04:32', " + cacheCorp + ",1,0,1,1," + zoneId + ",0,NULL,NULL,1, 1, 3, NULL, 0, NULL, NULL,0, 'pinyin', 'py'),";
                w1.append(sql);
            }
        }

        int id = 30000001;
        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            //console dtg group
            for (long userId : conAllList) {
                gname = "groupdtg" + userId;
                long zoneId = corp_user_map.get(cacheCorp).getConsoleZoneMap().get(userId);
                String sql = "(" + id + ",100," + userId + ",'" + gname + "', '2017-10-11 14:04:32', '2021-03-28 14:04:32', " + cacheCorp + ",1,1,1,1," + zoneId + ",1,NULL,NULL,1, 1, 3, NULL, 0, NULL, NULL,0, 'pinyin', 'py'),";
                w1.append(sql);
                id++;
            }

            //dcg group
            for (long userId : userList) {
                gname = "groupdcg" + userId;
                long zoneId = corp_user_map.get(cacheCorp).getUserZoneMap().get(userId);
                String sql = "(" + id + ",100," + userId + ",'" + gname + "', '2017-10-11 14:04:32', '2021-03-28 14:04:32', " + cacheCorp + ",1,1,1,1," + zoneId + ",0,NULL,NULL,1, 1, 3, NULL, 0, NULL, NULL,0, 'pinyin', 'py'),";
                w1.append(sql);
                id++;
            }
        }
        String sql = "(0,1,1,'default', '2017-11-11 11:11:11', '2021-03-28 14:04:32',0,1,1,1,1,0,0,NULL,NULL,1, 1, 3, NULL, 0, NULL, NULL,0, 'pinyin', 'py')";//for : 去掉逗号
        w1.append(sql);
        closeStream();
    }

    /**
     * group_dmr
     */
    public static void rtv_group_dmr() throws Exception {
        createFile("rtv_group_dmr", user_count);

        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            for (long userId : userList) {
                String sql = "(NULL, " + userId + "," + userId + ", '0', '409.025', '409.025', '409.025', '409.025', '0', '1', '49', NULL, '0', '20', '1', '2', '4'),";
                w1.append(sql);
            }
            for (long userId : conAllList) {
                String sql = "(NULL, " + userId + "," + userId + ", '0', '409.025', '409.025', '409.025', '409.025', '0', '1', '49', NULL, '0', '20', '1', '2', '4'),";
                w1.append(sql);
            }
        }

        String sql = "(0,0,0, '0', '409.025', '409.025', '409.025', '409.025', '0', '1', '49', NULL, '0', '20', '1', '2', '4')";
        w1.append(sql);

        //INSERT INTO `rtvitc`.`rtv_group_dmr` (`id`, `gid`, `tgid`, `mode`, `dtx`, `drx`, `atx`, `arx`, `encryption`, `sq`, `ctcss`, `rf`, `broadband`, `cc`, `power`, `txmode`, `rxmode`)
        //VALUES ('1', '74760', '74760', '0', '409.025', '409.025', '409.025', '409.025', '0', '1', '49', NULL, '0', '20', '1', '2', '4');
        closeStream();
    }

    /**
     * 每个group最多65535个用户，每个公司的所有用户全部加入公司的前5个组
     * INSERT INTO `rtvitrunk`.`rtv_user_group_map`(`id`, `group_id`, `user_id`, `group_right`, `nickname`, `user_role`) VALUES (1, 98306, 65537, 5, NULL, '0');
     */
    public static void rtv_user_group_map() throws Exception {
        createFile("rtv_user_group_map", user_count);

        //XXX 实际业务场景 : 一个组推荐不超过2000个成员

        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            //每个user一个group - userId 与 groupId一致
            long group1 = userList.get(0);
            long group2 = userList.get(1);
            long group3 = userList.get(2);
            long group4 = userList.get(3);
            long group5 = userList.get(4);

            List<Long> groups = new ArrayList<Long>();
            groups.add(group1);
            groups.add(group2);
            groups.add(group3);
            groups.add(group4);
            groups.add(group5);

            //组成员最多为 : (8000*2+1600)/5=3520
            for (long userId : userList) {
                int seq = 0;
                if (seq >= 5) {
                    seq = 0;
                }
                String sql = "(NULL," + groups.get(seq) + "," + userId + ",5, NULL, '0'),";
                w1.append(sql);
                seq++;
            }
            for (long userId : conAllList) {
                int seq = 0;
                if (seq >= 5) {
                    seq = 0;
                }
                String sql = "(NULL," + groups.get(seq) + "," + userId + ",5, NULL, '0'),";
                w1.append(sql);
                seq++;
            }
        }
        String sql = "(0,0,0,0, NULL, '0')";
        w1.append(sql);
        closeStream();
    }

    /**
     * 每个公司50条
     * INSERT INTO `rtvitrunk`.`rtv_console_user`(`id`, `user_name`, `password`, `salt`, `phone`, `corp_id`, `admin_id`,
     * `display_name`, `department_id`, `department_fk_id`, `auth`, `ext_id`, `video_permission`, `audio_permission`, `photo_permission`, `device`)
     * VALUES (1, 'xxt', 'dfeddeeadfe9', '98d9bc502630280c', '15500000001', 1, NULL,
     * '', '3300000001', 1, 30, NULL, 1, 1, 1, '');
     */
    public static void rtv_console_user() throws Exception {
        createFile("rtv_console_user", corp_count * 50);
        long phone = 11111111111l;
        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            for (int i = 1; i <= 50; i++) {
                long idx = (cacheCorp - 1) * 50 + i;
                String sql = "(NULL,'cu" + idx + "','df','3c687a804bda8827','" + (phone++) + "'," + cacheCorp + ",NULL, '', '1', 1, 30, NULL, 1, 1, 1, ''),";
                w1.append(sql);
            }
        }
        String sql = "(0,'default','df','3c687a804bda8827','1000000099',0,NULL, '', '1', 1, 30, NULL, 1, 1, 1, '')";//for : 去掉逗号
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
     * 每个用户10条
     */
    public static void rtv_external_camera() throws Exception {
        createFile("rtv_external_camera", media_count);
        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            for (long userId : userList) {
                for (int i = 1; i <= 10; i++) {
                    String sql = "(NULL, " + userId + ",'cam" + userId
                            + "', 'admin', 'YWRtaW4xMjM0NQ==', '101.201.45.198', '0', '5', '5', '4', 'cnRzcDovL2FkbWluOmFkbWluMTIzNDVAMTAxLjIwMS40NS4xOTg6NTU0L2NhbS9yZWFsbW9uaXRv\\ncj9jaGFubmVsPTEmc3VidHlwZT0w', 'cnRzcDovL2FkbWluOmFkbWluMTIzNDVAMTAxLjIwMS40NS4xOTg6NTU0L2NhbS9yZWFsbW9uaXRv\\ncj9jaGFubmVsPTEmc3VidHlwZT0x', "
                            + cacheCorp + ",1),";
                    w1.append(sql);
                }
            }

            for (long userId : conAllList) {
                for (int i = 1; i <= 10; i++) {
                    String sql = "(NULL, " + userId + ",'cam" + userId
                            + "', 'admin', 'YWRtaW4xMjM0NQ==', '101.201.45.198', '0', '5', '5', '4', 'cnRzcDovL2FkbWluOmFkbWluMTIzNDVAMTAxLjIwMS40NS4xOTg6NTU0L2NhbS9yZWFsbW9uaXRv\\ncj9jaGFubmVsPTEmc3VidHlwZT0w', 'cnRzcDovL2FkbWluOmFkbWluMTIzNDVAMTAxLjIwMS40NS4xOTg6NTU0L2NhbS9yZWFsbW9uaXRv\\ncj9jaGFubmVsPTEmc3VidHlwZT0x', "
                            + cacheCorp + ",1),";
                    w1.append(sql);
                }
            }
        }
        String sql = "(0,0,'cam0', 'admin', 'YWRtaW4xMjM0NQ==', '101.201.45.198', '0', '5', '5', '4', NULL, NULL,0)";//for : 去除最后一个逗号
        w1.append(sql);
        //INSERT INTO `rtvitc`.`rtv_external_camera` (`id`, `ctrid`, `display_name`, `uname`, `passwd`, `ipaddr`, `iptype`, `nvr_type`, `camera_type`, `channel`, `rtsp_main_url`, `rtsp_sub_url`, `orgid`)
        //VALUES ('1', '69603', '驾驶仓摄像头', 'admin', 'YWRtaW4xMjM0NQ==', '101.201.45.198', '0', '5', '5', '4', 'cnRzcDovL2FkbWluOmFkbWluMTIzNDVAMTAxLjIwMS40NS4xOTg6NTU0L2NhbS9yZWFsbW9uaXRv\ncj9jaGFubmVsPTEmc3VidHlwZT0w', 'cnRzcDovL2FkbWluOmFkbWluMTIzNDVAMTAxLjIwMS40NS4xOTg6NTU0L2NhbS9yZWFsbW9uaXRv\ncj9jaGFubmVsPTEmc3VidHlwZT0x', '1');
        closeStream();
    }

    /**
     * 每个用户10条
     */
    public static void rtv_fence() throws Exception {
        createFile("rtv_fence", media_count);
        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            for (long userId : userList) {
                String sql = "(" + userId + ", 1, 100, '测试围栏3', '13244444', '1', '{\"lat\":30,\"lon\":104,\"rad\":1234}', '[{\"lat\":30,\"lon\":104,\"rad\":1234},{\"lat\":30,\"lon\":104,\"rad\":1234}]', "
                        + userId + ", " + cacheCorp + "),";
                w1.append(sql);
            }

            for (long userId : conAllList) {
                String sql = "(" + userId + ", 1, 100, '测试围栏3', '13244444', '1', '{\"lat\":30,\"lon\":104,\"rad\":1234}', '[{\"lat\":30,\"lon\":104,\"rad\":1234},{\"lat\":30,\"lon\":104,\"rad\":1234}]', "
                        + userId + ", " + cacheCorp + "),";
                w1.append(sql);
            }
        }
        String sql = "(0, 1, 100, '测试围栏3', '13244444', '1', '{\"lat\":30,\"lon\":104,\"rad\":1234}', '[{\"lat\":30,\"lon\":104,\"rad\":1234},{\"lat\":30,\"lon\":104,\"rad\":1234}]',0,0)";
        w1.append(sql);
        closeStream();
        //INSERT INTO `rtvitc`.`rtv_fence` (`id`, `status`, `ts`, `name`, `color`, `type`, `circle_info`, `polygon_info`, `ownerid`, `corpid`)
        //VALUES ('3', '1', '4', '测试围栏3', '1', '1', '{\"lat\":30,\"lon\":104,\"rad\":1234}', '[{\"lat\":30,\"lon\":104,\"rad\":1234},{\"lat\":30,\"lon\":104,\"rad\":1234}]', '69592', '1');
    }

    /**
     * INSERT INTO `rtvitrunk`.`rtv_version_map`(`id`, `server_version`, `client_os_type`, `client_version`, `client_url`, `latest_server`,
     * `latest_client`, `client_description`, `force`, `branch`, `upload_time`, `old_forbid_dept_id`)
     * VALUES (4, NULL, 'android', '8.2.1.8.LYX', 'http://123.56.126.189:80//zhddglt/app/LYJX_8.2.1.8.LYX_signed.apk', 0, 0, '测试升级', NULL, 'main', '2020-06-11 15:19:00', '');
     * @throws Exception
     */
    public static void rtv_version_map() throws Exception {
        createFile("rtv_version_map", user_count);
        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            String sql = "(NULL, NULL, 'console', '5.0." + cacheCorp + "', 'http://rtv.oss-cn-beijing.aliyuncs.com/APPLatestVersion%2FJJH.apk', '1', '1', NULL, '0', 'main', '2017-07-26 15:58:35', NULL),";
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
     * 每个用户10条数据
     */
    public static void rtv_rtpictures() throws Exception {
        createFile("rtv_rtpictures", media_count);
        int id = 1;
        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            for (long userId : userList) {
                for (int i = 1; i <= 10; i++) {
                    String sql = "(" + id + ", " + userId + ", '{\"addr\":\"天目路61-63号;保利·香槟国际\",\"desc\":\"\"}', 'http://101.201.45.198:8081/rtv/rtpictures/2017-10-26/hd_" + id
                            + ".jpg', 'http://101.201.45.198:8081/rtv/rtpictures/2017-10-26/small_" + id + ".jpg', 'http://101.201.45.198:8081/rtv/rtpictures/2017-10-26/large_" + id
                            + ".jpg', '2017-10-26 12:07:51', " + cacheCorp + "),";
                    w1.append(sql);
                    id++;
                }
            }

            for (long userId : conAllList) {
                for (int i = 1; i <= 10; i++) {
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
     * 每个用户10条数据
     */
    public static void rtv_video() throws Exception {
        createFile("video_record_table", user_count);
        long id = 1;
        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            for (long userId : userList) {
                for (int i = 1; i <= 10; i++) {
                    String sql = "(" + id + ", " + userId + ",'1508984291', '1508984291', '/home/itcit/video/record/2017-10-26/" + id
                            + ".mp4', '22.855', '20.146', '1.74433', '610.573', 'http://101.201.45.198:81/hls/2017-10-26/" + id + ".m3u8', " + cacheCorp + ", '0', '0'),";
                    w1.append(sql);
                    id++;
                    //INSERT INTO `rtvitc`.`video_record_table` (`id`, `uid`, `video_date`, `create_date`, `video_url`, `duration`, `frame_rate`, `size`, `bit_rate`, `url`, `corp_id`, `session`, `channel`)
                    //VALUES ('1', '69537', '1493373747', '1493373747', '/home/itcit/video/record/2017-04-28/3686858974_00069537_2017-04-28_18:02:27.mp4', '22.855', '20.146', '1.74433', '610.573', 'http://101.201.45.198:81/hls/2017-04-28/3686858974_00069537_2017-04-28_18:02:27.m3u8', '1', '0', '0');
                }
            }

            for (long userId : conAllList) {
                for (int i = 1; i <= 10; i++) {
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
     * 每个用户10条数据
     */
    public static void rtv_gps_info() throws Exception {
        createFile("rtv_gps_info", user_count);

        for (long cacheCorp = 1; cacheCorp <= corp_count; cacheCorp++) {
            CorpUser cu = corp_user_map.get(cacheCorp);
            List<Long> userList = cu.getUserList();
            List<Long> conAllList = cu.getConAllList();//所有1-7阶

            for (long userId : userList) {
                for (int i = 1; i <= 5; i++) {
                    String sql = "(NULL, " + userId
                            + ", '20171026', '\0Y�@>���`Z�@ZN�ğ�\\0Y3@>���6?W@ZM�΁\\0Y�@>�|�&�+@ZQeMa�\\0YB@>�����@ZM@$�>\\0Y�@>��`��@ZN�/f\\0Y@>�|����@ZQ�\\r�\\0Y�@>�|�&�+@ZQi�\\0Y?@>���2#@ZO�I�H\\0Y�@>����M@ZOT��l\\0YP@>�}�%V�@ZQ�7�M\\0Y�@>��]�r�@ZP3:~\\0YF@>���1�!@ZO�B��\\0Y�@>��]�r�@ZP3:~\\0Y�@>��@l\\0�@ZP?�t6\\0Y9@>��]�r�@ZP3:~\\0YX@>��a��@ZP.�|�\\0Y�@>��]�r�@ZP3:~\\0Y�@>��\\Z��@ZP�	$\\0Y\\Z-@>�{�\\rZ\\\\@ZQ���\f\\0Y\\Z�@>���2#@ZO�О\\0Y!@>��@l\\0�@ZPLH��', "
                            + cacheCorp + "),";
                    w1.append(sql);
                    //INSERT INTO `rtvitc`.`rtv_gps_info` (`id`, `uid`, `date_key`, `gps_points`, `orgid`) VALUES ('11111', '69537', '20170428', '123', '1');
                }
            }

            for (long userId : conAllList) {
                for (int i = 1; i <= 5; i++) {
                    String sql = "(NULL, " + userId
                            + ", '20171026', '\\0Y�@>���`Z�@ZN�ğ�\\\\0Y3@>���6?W@ZM�΁\\\\0Y�@>�|�&�+@ZQeMa�\\\\0YB@>�����@ZM@$�>\\\\0Y�@>��`��@ZN�/f\\\\0Y@>�|����@ZQ�\\\\r�\\\\0Y�@>�|�&�+@ZQi�\\\\0Y?@>���2#@ZO�I�H\\\\0Y�@>����M@ZOT��l\\\\0YP@>�}�%V�@ZQ�7�M\\\\0Y�@>��]�r�@ZP3:~\\\\0YF@>���1�!@ZO�B��\\\\0Y�@>��]�r�@ZP3:~\\\\0Y�@>��@l\\\\0�@ZP?�t6\\\\0Y9@>��]�r�@ZP3:~\\\\0YX@>��a��@ZP.�|�\\\\0Y�@>��]�r�@ZP3:~\\\\0Y�@>��\\\\Z��@ZP�	$\\\\0Y\\\\Z-@>�{�\\\\rZ\\\\\\\\@ZQ���\\f\\\\0Y\\\\Z�@>���2#@ZO�О\\\\0Y!@>��@l\\\\0�@ZPLH��', "
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
                        + "-BMS18', NULL, '1', 'admin', 'zom', '@Around', '1', '{\"status\":\"success\",\"_resultType\":\"_result_obj\",\"code\":\"operation_success\",\"msg\":\"操作成功\",\"success\":\"success\"}', NULL),";
                w1.append(sql);
            }
            for (long userId : conAllList) {
                String sql = "(NULL, 'rtv_user', '修改用户', '{\"id\":\"69554\",\"nickname\":\"BMS18\",\"phone\":\"19900004018\",\"userPriority\":\"1\",\"zoneId\":\"1\",\"consoleIdAddAry\":\"[]\",\"consoleIdDelAry\":\"[]\",\"groupIdAddAry\":\"[]\",\"groupIdDelAry\":\"[]\"}', '[{\"val_cn_old\":\"20\",\"key_cn\":\"用户优先级\",\"val_new\":\"1\",\"key_en\":\"priority\",\"val_cn_new\":\"1\",\"val_old\":\"20\"}]', '<thead><tr role=\"row\"><th class=\"col-xs-3 sorting_disabled\">名称</th><th class=\"col-xs-3 sorting_disabled\">旧数据</th><th class=\"col-xs-6 sorting_disabled\">新数据</th></tr></thead><tbody><tr role=\"row\" class=\"even\"><td>用户优先级</td><td>20</td><td>1</td></tr></tbody>', '171.217.97.157', '2017-05-08 17:56:12', 'update', 'default', NULL, '1', 'admin', 'zom', '"
                        + userId
                        + "-BMS18', NULL, '1', 'admin', 'zom', '@Around', '1', '{\"status\":\"success\",\"_resultType\":\"_result_obj\",\"code\":\"operation_success\",\"msg\":\"操作成功\",\"success\":\"success\"}', NULL),";
                w1.append(sql);
            }
        }

        String sql = "(0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)";
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

        String sql = "(0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)";
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

        String sql = "(0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)";
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
