package com.zom.factory.zfactory.generator.template.db.generator;

public class Cfg {

    //路径：以项目根目录为相对起始路径
    //public static String sep = File.separator;
    public static String   db_name         = "zom_template";//数据库名称
    public static String   project_name    = "zom-factory";//项目名称
    public static String   base_dir        = System.getProperty("user.dir");
    public static String   output_dir_base = base_dir + "\\" + project_name + "\\template-output\\";
    //public static String   output_dir_base = "D:\\work\\generator\\template-output\\";
    public static String[] tableFilterAry  = {};//需要过滤的表
    public static String   charset         = "UTF-8";

    public static String poPackage          = "com.zom.common.dao.po";
    public static String daoPackage         = "com.zom.common.dao.mapper";
    //public static String servicePackage = "com.zom.cms.service.user";
    public static String output_dir_xml     = output_dir_base + "xml";
    public static String output_dir_po      = output_dir_base + "po";
    public static String output_dir_dao     = output_dir_base + "dao";
    public static String output_dir_service = output_dir_base + "service";

    //public static String template_dir_name = "D:\\work\\generator\\template\\";
    public static String template_dir_name = base_dir + "\\" + project_name + "\\template";
    public static String template_dao      = "dao.ftl";
    public static String template_po       = "po.ftl";
    public static String template_xml      = "xml.ftl";

    public static String filter_prefix = "rtv_"; // 需要过滤的前缀：rtv_ zom_

    public static String jdbc_driver = "com.mysql.cj.jdbc.Driver"; // 加载JDBC驱动
    public static String jdbc_url    = "jdbc:mysql://localhost/" + db_name + "?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true"; // 连接服务器和数据库test
    public static String jdbc_user   = "root"; // 默认用户名
    public static String jdbc_pswd   = "root"; // 密码


}
