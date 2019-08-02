package com.zom.factory.zfactory.generator.template.db.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zom.factory.zfactory.generator.template.db.generator.Cfg;

/**
 * @author airson
 */
public class Executor {

    public static Connection conn = connectMysql();

    public static Connection connectMysql() {
        String jdbc_driver = Cfg.jdbc_driver; // 加载JDBC驱动
        String jdbc_url = Cfg.jdbc_url; // 连接服务器和数据库test
        String jdbc_user = Cfg.jdbc_user; // 默认用户名
        String jdbc_pswd = Cfg.jdbc_pswd; // 密码
        try {
            Class.forName(jdbc_driver);
            conn = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_pswd);
        } catch (Exception e) {
            System.out.println("数据库连接失败，请在Cfg中检查相关配置是否正确");
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 获取数据库的所有表
     *
     * @param filter       0-获取数据库全部表，1-只包含指定表，2-去除指定表
     * @param tableNameAry 需要过滤的表
     * @return List<Table>
     * @throws Exception
     */
    public static List<Table> getTableList(int filter, String[] tableNameAry) throws Exception {
        System.out.println("从 " + Cfg.db_name + " 数据库中获取表数据: \n");
        String sql = "select table_name,table_comment from INFORMATION_SCHEMA.TABLES where table_schema='" + Cfg.db_name + "' and table_type='base table';";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Table> tableList = new ArrayList<Table>();
        while (rs.next()) {
            boolean filterFlag = false;
            String table_name = rs.getString("table_name");
            String table_comment = rs.getString("table_comment");

            if (null != tableNameAry && tableNameAry.length > 0) {//跳过需要过滤的表
                for (String t : tableNameAry) {
                    if (table_name.equals(t)) {
                        filterFlag = true;
                        break;
                    }
                }
            }
            if (filter == 0 || (filter == 1 && filterFlag) || (filter == 2 && !filterFlag)) {
                Table t = new Table();
                t.setTable_name(table_name);
                t.setTable_comment(table_comment);
                tableList.add(t);
                System.out.println("表名: " + table_name);
            }
        }
        return tableList;
    }

    /**
     * 获取数据库的所有表名
     *
     * @param filter       0-获取数据库全部表，1-只包含指定表，2-去除指定表
     * @param tableNameAry 需要过滤的表
     * @return List<Table>
     * @throws Exception
     */
    public static List<String> getTableNameList(int filter, String[] tableNameAry) throws Exception {
        System.out.println("从 " + Cfg.db_name + " 数据库中获取表数据: \n");
        String sql = "select table_name,table_comment from INFORMATION_SCHEMA.TABLES where table_schema='" + Cfg.db_name + "' and table_type='base table';";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<String> tableList = new ArrayList<String>();
        while (rs.next()) {
            boolean filterFlag = false;
            String table_name = rs.getString("table_name");

            if (null != tableNameAry && tableNameAry.length > 0) {//跳过需要过滤的表
                for (String t : tableNameAry) {
                    if (table_name.equals(t)) {
                        filterFlag = true;
                        break;
                    }
                }
            }
            if (filter == 0 || (filter == 1 && filterFlag) || (filter == 2 && !filterFlag)) {
                tableList.add(table_name);
                System.out.println("表名: " + table_name);
            }
        }
        return tableList;
    }

    /**
     * 获取指定表的所有字段
     *
     * @param table_name
     * @return List<Column>
     * @throws Exception
     */
    public static List<Column> getColumnsList(String table_name) throws Exception {
        if (null == table_name) return null;
        List<Column> columnsList = new ArrayList<Column>();
        String sql = "select column_name,data_type,column_comment from INFORMATION_SCHEMA.COLUMNS where table_schema='" + Cfg.db_name + "' and table_name='" + table_name + "';";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String column_name = rs.getString("column_name");
            String data_type = rs.getString("data_type");
            String column_comment = rs.getString("column_comment");
            Column c = new Column();
            c.setColumn_name(column_name);
            c.setData_type(data_type);
            c.setColumn_comment(column_comment);
            //System.out.println("table_name: "+table_name+", column_name: "+column_name+", data_type: "+data_type+", column_comment: "+column_comment);
            columnsList.add(c);
        }
        return columnsList;
    }

    /**
     * 截断表（会清空数据！）
     *
     * @param tableNameList
     * @throws Exception
     */
    public static void truncateTable(List<String> tableNameList) throws Exception {
        for (String table_name : tableNameList) {
            String sql1 = "truncate `" + Cfg.db_name + "`.`" + table_name + "`;";
            String sql2 = "alter table `" + Cfg.db_name + "`.`" + table_name + "` auto_increment=1";
            System.out.println(sql1);
            System.out.println(sql2);
            PreparedStatement ps = conn.prepareStatement(sql1);
            ps.execute();
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.execute();
        }
    }

    /**
     * 表批量执行SQL
     *
     * @param tableList
     * @param sql
     * @throws Exception
     */
    public static void excuteSqlForAllTable(List<Table> tableList, String sql) throws Exception {
        if (null == tableList || null == sql) return;
        for (Table table : tableList) {
            String table_name = table.getTable_name();
            String exSql = sql.replaceAll("_TABLE_", table_name);
            System.out.println(exSql);
            PreparedStatement ps = conn.prepareStatement(exSql);
            try {
                ps.execute();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("table_name: " + table_name);
            }
        }
    }

    /**
     * 执行多条SQL语句(直接循环执行，无任何过滤)
     *
     * @throws Exception
     */
    public static void executeBatchSql(List<String> sqlList) throws Exception {
        for (String sql : sqlList) {
            System.out.println(sql);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        }
    }

}
