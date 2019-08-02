package com.zom.factory.zfactory.generator.template.db.tools;

import java.io.File;
import java.io.FileWriter;

/**
 * 生成百万或千万级别的测试数据
 *
 * @author airson
 */
public class BigDataGenerator2 {

    /**
     * sql脚本文件的存放目录
     */
    private static String output_dir = "D:\\zlab\\sql\\";

    private static File file1  = null;
    private static File file2  = null;
    private static File file3  = null;
    private static File file4  = null;
    private static File file5  = null;
    private static File file6  = null;
    private static File file7  = null;
    private static File file8  = null;
    private static File file9  = null;
    private static File file10 = null;

    private static FileWriter w1  = null;
    private static FileWriter w2  = null;
    private static FileWriter w3  = null;
    private static FileWriter w4  = null;
    private static FileWriter w5  = null;
    private static FileWriter w6  = null;
    private static FileWriter w7  = null;
    private static FileWriter w8  = null;
    private static FileWriter w9  = null;
    private static FileWriter w10 = null;

    private static void createFile(String table, String prefixSql) throws Exception {
        file1 = new File(output_dir + table + "1.sql");
        file2 = new File(output_dir + table + "2.sql");
        file3 = new File(output_dir + table + "3.sql");
        file4 = new File(output_dir + table + "4.sql");
        file5 = new File(output_dir + table + "5.sql");
        file6 = new File(output_dir + table + "6.sql");
        file7 = new File(output_dir + table + "7.sql");
        file8 = new File(output_dir + table + "8.sql");
        file9 = new File(output_dir + table + "9.sql");
        file10 = new File(output_dir + table + "10.sql");
        w1 = new FileWriter(file1);
        w2 = new FileWriter(file2);
        w3 = new FileWriter(file3);
        w4 = new FileWriter(file4);
        w5 = new FileWriter(file5);
        w6 = new FileWriter(file6);
        w7 = new FileWriter(file7);
        w8 = new FileWriter(file8);
        w9 = new FileWriter(file9);
        w10 = new FileWriter(file10);
        w1.append(prefixSql);
        w2.append(prefixSql);
        w3.append(prefixSql);
        w4.append(prefixSql);
        w5.append(prefixSql);
        w6.append(prefixSql);
        w7.append(prefixSql);
        w8.append(prefixSql);
        w9.append(prefixSql);
        w10.append(prefixSql);
    }

    private static void closeStream() throws Exception {
        w1.flush();
        w1.close();
        w2.flush();
        w2.close();
        w3.flush();
        w3.close();
        w4.flush();
        w4.close();
        w5.flush();
        w5.close();
        w6.flush();
        w6.close();
        w7.flush();
        w7.close();
        w8.flush();
        w8.close();
        w9.flush();
        w9.close();
        w10.flush();
        w10.close();
    }

    private static void appendSql(long idx, String sql) throws Exception {
        if (idx <= 65547) {
            System.out.println(sql);
        }
        if (idx <= 1065537) {
            if (idx == 1065537) sql = sql.substring(0, sql.length() - 1);
            w1.append(sql);
        } else if (idx <= 2065537) {
            if (idx == 2065537) sql = sql.substring(0, sql.length() - 1);
            w2.append(sql);
        } else if (idx <= 3065537) {
            if (idx == 3065537) sql = sql.substring(0, sql.length() - 1);
            w3.append(sql);
        } else if (idx <= 4065537) {
            if (idx == 4065537) sql = sql.substring(0, sql.length() - 1);
            w4.append(sql);
        } else if (idx <= 5065537) {
            if (idx == 5065537) sql = sql.substring(0, sql.length() - 1);
            w5.append(sql);
        } else if (idx <= 6065537) {
            if (idx == 6065537) sql = sql.substring(0, sql.length() - 1);
            w6.append(sql);
        } else if (idx <= 7065537) {
            if (idx == 7065537) sql = sql.substring(0, sql.length() - 1);
            w7.append(sql);
        } else if (idx <= 8065537) {
            if (idx == 8065537) sql = sql.substring(0, sql.length() - 1);
            w8.append(sql);
        } else if (idx <= 9065537) {
            if (idx == 9065537) sql = sql.substring(0, sql.length() - 1);
            w9.append(sql);
        } else if (idx <= 10065537) {
            if (idx == 10065537) sql = sql.substring(0, sql.length() - 1);
            w10.append(sql);
        }
    }

    public static void rtv_user() throws Exception {
        createFile("rtv_user", "USE zlab; INSERT INTO rtv_user VALUES ");
        long phone = 13700000000l;
        String dname = null;
        String lname = null;
        long idx = 65537;
        while (idx <= 10065537) {
            dname = "bms" + idx;
            lname = "u" + idx;
            String sql = "(NULL,'" + dname + "','df',NULL,'portable',NULL,'" + (phone++) + "','2016-05-30 14:16:00','2016-05-30 14:16:00',NULL,NULL,'offline','245956046a76a3a8',1,1,0,0,65553,1,'" + lname + "',1,1,1,0,1,30,0,1,0,0,'',NULL),";
            appendSql(idx, sql);
            idx++;
        }
        closeStream();

    }

    public static void rtv_group() throws Exception {
        createFile("rtv_group", "USE zlab; INSERT INTO `rtv_group` VALUES ");
        String gname = null;
        long idx = 65537;
        while (idx <= 10065537) {
            gname = "dcg" + idx;
            String sql = "(NULL,2," + idx + ",'" + gname + "','2016-05-30 14:04:32',1,0,1,1,1,1,0),";
            appendSql(idx, sql);
            idx++;
        }
        closeStream();
    }

    public static void rtv_user_group_map() throws Exception {
        createFile("rtv_user_group_map", "USE zlab; INSERT INTO `rtv_user_group_map` VALUES ");
        long idx = 65537;
        while (idx <= 10065537) {
            String sql = "(NULL," + idx + "," + idx + ",5),";
            appendSql(idx, sql);
            idx++;
        }
        closeStream();
    }

    public static void rtv_console_cop_mapping() throws Exception {
        createFile("rtv_console_cop_mapping", "USE zlab; INSERT INTO `rtv_console_cop_mapping` VALUES ");
        long idx = 65537;
        while (idx <= 10065537) {
            String sql = "(NULL," + 1 + "," + idx + "),";
            appendSql(idx, sql);
            idx++;
        }
        closeStream();
    }

    public static void rtv_rtpictures() throws Exception {
        createFile("rtv_rtpictures", "USE zlab; INSERT INTO `rtv_rtpictures` VALUES ");
        long idx = 65537;
        while (idx <= 10065537) {
            String sql = "(NULL, " + idx + ", '{\"addr\":\"天目路61-63号;保利·香槟国际\",\"desc\":\"\"}', 'http://101.201.45.198:8081/rtv/rtpictures/2017-06-16/hd_103_1497586068675_uid_69577_2017-06-16_12-07-38_ini.jpg', 'http://101.201.45.198:8081/rtv/rtpictures/2017-06-16/small_103_1497586068675_uid_69577_2017-06-16_12-07-38_ini.jpg', 'http://101.201.45.198:8081/rtv/rtpictures/2017-06-16/large_103_1497586068675_uid_69577_2017-06-16_12-07-38_ini.jpg', '2017-06-16 12:07:51', '1'),";
            appendSql(idx, sql);
            idx++;
        }
        closeStream();
    }

    public static void rtv_bms_params() throws Exception {
        createFile("rtv_bms_params", "USE zlab; INSERT INTO `rtv_bms_params` VALUES ");
        long idx = 65537;
        while (idx <= 10065537) {
            String sql = "(NULL," + idx + ", 1, 8, '{\"vpull_type_15\":1073741826,\"gps_interval_2\":1073741854,\"basic_bms_pri_12\":1073741827,\"gps_query_3\":1073741825,\"gps_report_1\":1073741825}'),";
            appendSql(idx, sql);
            idx++;
        }
        closeStream();
    }

    public static void rtv_console_user_mapping() throws Exception {
        createFile("rtv_console_user_mapping", "USE zlab; INSERT INTO `rtv_console_user_mapping` VALUES ");
        long idx = 65537;
        while (idx <= 10065537) {
            String sql = "(NULL," + idx + ", 1),";
            appendSql(idx, sql);
            idx++;
        }
        closeStream();
    }

    public static void rtv_gps_info() throws Exception {
        createFile("rtv_gps_info", "USE zlab; INSERT INTO `rtv_gps_info` VALUES ");
        long idx = 65537;
        while (idx <= 10065537) {
            String sql = "(NULL, " + idx + ", '20170428', '', 1),";
            appendSql(idx, sql);
            idx++;
        }
        closeStream();
    }

    public static void rtv_token() throws Exception {
        createFile("rtv_token", "USE zlab; INSERT INTO `rtv_token` VALUES ");
        long idx = 65537;
        while (idx <= 10065537) {
            String sql = "(NULL, 'ec25339660da4b66907a61047a0e3d43" + 000 + idx + "', " + idx + ", '2017-06-19 18:01:37', '2017-06-19 18:01:37', '2301-02-02 18:01:37'),";
            appendSql(idx, sql);
            idx++;
        }
        closeStream();
    }

    public static void rtv_operation_log() throws Exception {
        createFile("rtv_operation_log", "USE zlab; INSERT INTO `rtv_operation_log` VALUES ");
        long idx = 65537;
        while (idx <= 10065537) {
            String sql = "(NULL, 'rtv_user', '修改用户', '{\"id\":\"69554\",\"nickname\":\"BMS18\",\"phone\":\"19900004018\",\"userPriority\":\"1\",\"zoneId\":\"1\",\"consoleIdAddAry\":\"[]\",\"consoleIdDelAry\":\"[]\",\"groupIdAddAry\":\"[]\",\"groupIdDelAry\":\"[]\"}', '[{\"val_cn_old\":\"20\",\"key_cn\":\"用户优先级\",\"val_new\":\"1\",\"key_en\":\"priority\",\"val_cn_new\":\"1\",\"val_old\":\"20\"}]', '<thead><tr role=\"row\"><th class=\"col-xs-3 sorting_disabled\">名称</th><th class=\"col-xs-3 sorting_disabled\">旧数据</th><th class=\"col-xs-6 sorting_disabled\">新数据</th></tr></thead><tbody><tr role=\"row\" class=\"even\"><td>用户优先级</td><td>20</td><td>1</td></tr></tbody>', '171.217.97.157', '2017-05-08 17:56:12', 'update', 'default', NULL, '1', 'admin', 'zom', '" + idx + "-BMS18', NULL, '1', 'admin', 'zom', '@Around', '1', '{\"status\":\"success\",\"_resultType\":\"_result_obj\",\"code\":\"operation_success\",\"msg\":\"操作成功\",\"success\":\"success\"}', NULL),";
            appendSql(idx, sql);
            idx++;
        }
        closeStream();
    }

    public static void rtv_dlmsg_storage() throws Exception {
        createFile("rtv_dlmsg_storage", "USE zlab; INSERT INTO `rtv_dlmsg_storage` VALUES ");
        long idx = 65537;
        while (idx <= 10065537) {
            String sql = "(NULL, " + idx + ", " + idx + ", " + (idx + 1) + ", '0', '0', '0', '1494679262', '1494679262', '0', '29', '5LmI5LmI5LmI5LmI5aaI5aaI5ZKq\n'),";
            appendSql(idx, sql);
            idx++;
        }
        closeStream();
    }

    public static void rtv_ulmsg_storage() throws Exception {
        createFile("rtv_ulmsg_storage", "USE zlab; INSERT INTO `rtv_ulmsg_storage` VALUES ");
        long idx = 65537;
        while (idx <= 10065537) {
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

    public static void main(String[] args) throws Exception {
        rtv_user();
    }
}
