package com.zom.factory.zfactory.generator.data;

import java.io.File;
import java.io.FileWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * 生成Jmeter性能测试的数据文件
 *
 * @author airson
 */
public class JmeterDataGenerator {

    /**
     * 程序运行 环境
     */
    private static boolean windows = true;

    /**
     * sql脚本文件的存放目录
     */
    private static final String output_dir  = windows ? "E:\\zlab\\jmeter2020\\" : "/home/itrunk/scripts_zom/jmeter2020/";
    /**
     * 数据库配置
     */
    private static final String dbHost      = windows ? "localhost" : "localhost";                                                    //101.201.45.198
    private static final String dbname      = windows ? "rtv_test_tmp" : "rtvitrunk"; //rtvitrunk
    private static final String jdbc_user   = windows ? "root" : "itrunk";//itrunk root
    private static final String jdbc_pswd   = windows ? "root" : "@Zse4rfv_";//@Zse4rfv_ Xyz@Itr132!
    private static final String jdbc_driver = "com.mysql.jdbc.Driver";
    private static final String jdbc_url    = "jdbc:mysql://" + dbHost + "/" + dbname + "?useUnicode=true&characterEncoding=utf-8";

    public static Connection conn = connectMysql();

    private static File       file1 = null;
    private static FileWriter w1    = null;
    private static String     fname = "";

    public static void main(String[] args) throws Exception {
        create_file_user();
        create_file_console();
        create_file_thirdkey_all();

        System.out.println("---------- over over over ---------- ");
    }

    //seq:user,console,

    /**
     * #user:前三个公司的全部用户
     */
    public static void create_file_user() throws Exception {
        createFile("user", "#u-uid,u-corpid,u-zoneid,u-phone,u-pswd,u-device,u-lname,u-token");
        String sql = "SELECT CONCAT_WS(',',A.id, A.corp_id, A.zone_id, A.phone, A.user_password, A.device, A.logon_name, t.token) AS data_concat "
                + "FROM rtv_user A LEFT JOIN rtv_token t ON t.uid = A.id WHERE A.corp_id IN(1,2,3) AND `rank` = 0";
        querySql(sql, true);

        // XXX 检查用户是否都有token
        //INSERT INTO `rtv_token`(`token`, `uid`, `request_date`, `refresh_date`, `overdue_date`)
        //SELECT CONCAT('cd2954a420523af841ddb89f87-',id), id, '2020-05-30 10:11:32', '2020-05-30 10:11:32', '2300-01-06 11:45:55' FROM rtv_user;

    }

    /**
     * #user:前三个公司的全部console
     */
    public static void create_file_console() throws Exception {
        //#uid, corpid, zoneid, phone, password, token, cu-uname, cu-pswd, cu-cname, console-att-uid(get_token),
        createFile("console", "#c-uid,c-corpid,c-zoneid,c-phone,c-pswd,c-lname,c-rank,c-token,cu-uname,cu-pswd,c-att-uid");
        String sql = "SELECT CONCAT_WS(',',A.id, A.corp_id, A.zone_id, A.phone, A.user_password, A.logon_name, A.`rank`, t.token,"
                + "(SELECT CONCAT_WS(',',user_name,password) FROM rtv_console_user WHERE corp_id = A.corp_id LIMIT 0,1),"
                + "IF(A.`rank`>1,(SELECT id FROM rtv_user WHERE corp_id=A.corp_id AND `rank`=A.`rank`-1 LIMIT 0,1),0)) "
                + "AS data_concat FROM rtv_user A LEFT JOIN rtv_token t ON t.uid = A.id WHERE A.corp_id IN(1,2,3) AND `rank` > 0";
        querySql(sql, true);
    }

    /**
     *
     */
    public static void create_file_thirdkey_all() throws Exception {
        create_file_thirdkey_core("create_device");
        create_file_thirdkey_core("del_device");
        create_file_thirdkey_core("update_device");
        create_file_thirdkey_core("create_tg");
        create_file_thirdkey_core("delete_tg");
        create_file_thirdkey_core("update_tg_info");
        create_file_thirdkey_core("update_tg_mem");
        create_file_thirdkey_core("update_device_tg");
        create_file_thirdkey_core("distribute_work_order");
        create_file_thirdkey_core("delete_work_order");
        create_file_thirdkey_core("inform_new_video");
        create_file_thirdkey_core("delete_video");
    }

    private static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

    //HMACSHA256 加密
    private static String HMACSHA256(byte[] data, byte[] key) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            return byte2hex(mac.doFinal(data));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String buildSign(String api_key, String securityKey, String url) {
        System.out.println("buildSign-api_key:" + api_key + ",securityKey:" + securityKey + ",url : " + url);
        String timestamp = "123456";
        String encrytString = "api_key" + api_key + "timestamp" + timestamp + url;
        String encrypt = HMACSHA256(encrytString.getBytes(), securityKey.getBytes());
        return encrypt;
    }

    private static void create_file_thirdkey_core(String api_key_pure) throws Exception {
        //#uid, corpid, zoneid, phone, password, token, cu-uname, cu-pswd, cu-cname, console-att-uid(get_token),
        createFile("thirdkey_" + api_key_pure, "#t-sign-api-key,t-sign-timestamp,t-sign-sign");
        String sql = "SELECT * FROM rtv_3rdkey WHERE api_key LIKE '" + api_key_pure + "%'";
        ResultSet rs = querySql(sql, false);
        while (rs.next()) {
            String api_key = rs.getString("api_key");
            String security_key = rs.getString("security_key");
            //int corp_id = rs.getInt("corp_id");
            String line = api_key + ",123456," + buildSign(api_key, security_key, "/rtv/api/3rd/v1/" + api_key.split("-")[0]);
            w1.append(line + "\n");
        }
        closeStream();
    }

    private static void createFile(String table, String prefix) throws Exception {
        System.out.println(" ---------- " + table + " ---------- ");
        fname = output_dir + table + ".sql";
        System.out.println("createFile start ：" + fname);// + ", total：" + total
        file1 = new File(fname);
        w1 = new FileWriter(file1);
        w1.write(prefix + "\n");
    }

    private static void closeStream() throws Exception {
        System.out.println("createFile end：" + fname);
        w1.flush();
        w1.close();
    }

    public static Connection connectMysql() {
        try {
            Class.forName(jdbc_driver);
            conn = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_pswd);
        } catch (Exception e) {
            System.out.println("数据库连接失败，请在检查相关配置是否正确");
            e.printStackTrace();
        }
        return conn;
    }

    public static ResultSet querySql(String sql, boolean append) throws Exception {
        System.out.println("execute sql : " + sql + " \n");
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (append) {
            while (rs.next()) {
                w1.append(rs.getString("data_concat") + "\n");
            }
            closeStream();
        }
        return rs;
    }

}
