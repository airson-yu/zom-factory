package com.zom.factory.zfactory.generator.template.db.tools;

import java.util.List;

import com.zom.factory.zfactory.generator.template.db.core.Executor;
import com.zom.factory.zfactory.generator.template.db.core.Table;

/**
 * sql语句执行器
 *
 * @author airson
 */
public class SqlExcuter {

    private static String sql =  // _TABLE_ 自动替换为表名:String exSql = sql.replace("_TABLE_", table_name);
            "ALTER TABLE _TABLE_ MODIFY COLUMN `deleted_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '删除人' AFTER `deleted_at`;";

    public static void main(String[] args) throws Exception {
        execute(null);
    }

    public static void execute(List<Table> tableList) throws Exception {
        if (null == sql || sql.length() <= 5) return;
        tableList = null == tableList ? Executor.getTableList(0, null) : tableList;//若不指定表则在所有表上执行
        Executor.excuteSqlForAllTable(tableList, sql);
    }

}
