/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层数据库字段对象（db_mysql:information_schema） <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 成都蓝海飞鱼科技有限公司开发人员 <p>
 * <strong> 编写时间：</strong> 2015-2016 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 1.0 <p>
 */
package com.zom.factory.zfactory.generator.template.db.core;

public class Column {

    private Integer id;
    private String  table_schema;
    private String  table_name;
    private String  column_name;
    private String  data_type;
    private String  character_maximum_length;
    private String  character_octet_length;
    private String  numeric_precision;
    private String  numeric_scale;
    private String  column_type;
    private String  column_comment;
    private String  column_default;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTable_schema() {
        return table_schema;
    }

    public void setTable_schema(String table_schema) {
        this.table_schema = table_schema;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getCharacter_maximum_length() {
        return character_maximum_length;
    }

    public void setCharacter_maximum_length(String character_maximum_length) {
        this.character_maximum_length = character_maximum_length;
    }

    public String getCharacter_octet_length() {
        return character_octet_length;
    }

    public void setCharacter_octet_length(String character_octet_length) {
        this.character_octet_length = character_octet_length;
    }

    public String getNumeric_precision() {
        return numeric_precision;
    }

    public void setNumeric_precision(String numeric_precision) {
        this.numeric_precision = numeric_precision;
    }

    public String getNumeric_scale() {
        return numeric_scale;
    }

    public void setNumeric_scale(String numeric_scale) {
        this.numeric_scale = numeric_scale;
    }

    public String getColumn_type() {
        return column_type;
    }

    public void setColumn_type(String column_type) {
        this.column_type = column_type;
    }

    public String getColumn_comment() {
        return column_comment;
    }

    public void setColumn_comment(String column_comment) {
        this.column_comment = column_comment;
    }

    public String getColumn_default() {
        return column_default;
    }

    public void setColumn_default(String column_default) {
        this.column_default = column_default;
    }

}