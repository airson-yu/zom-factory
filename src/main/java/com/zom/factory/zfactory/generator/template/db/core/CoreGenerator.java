package com.zom.factory.zfactory.generator.template.db.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zom.factory.zfactory.generator.template.db.generator.Cfg;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class CoreGenerator {

    private static String sep = File.separator;

    /**
     * 生成模板文件
     *
     * @param filter        0-获取数据库全部表，1-只包含指定表，2-去除指定表
     * @param tableNameList 指定的表名数组
     * @param xml           是否生成mybatis-xml文件
     * @param po            是否生成PO
     * @param dao           是否生成DAO
     * @param service       是否生成SERVICE
     * @throws Exception
     */
    public static void produceAll(int filter, String[] tableNameList, boolean xml, boolean po, boolean dao, boolean service) throws Exception {

        System.out.println("\n提示： ----------------------------------------开始清空template-output目录----------------------------------------\n");
        String path = Cfg.output_dir_xml;
        System.out.println(path);
        File file1 = new File(path);
        File file2 = new File(Cfg.output_dir_po);
        File file3 = new File(Cfg.output_dir_dao);
        File file4 = new File(Cfg.output_dir_service);
        for (File f : file1.listFiles()) {
            f.delete();
        }
        for (File f : file2.listFiles()) {
            f.delete();
        }
        for (File f : file3.listFiles()) {
            f.delete();
        }
        for (File f : file4.listFiles()) {
            f.delete();
        }

        System.out.println("\n提示： ----------------------------------------开始生成文件----------------------------------------\n");
        List<Table> tableList = Executor.getTableList(filter, tableNameList);

        if (xml) {
            System.out.println("\n提示： --------------------读取模板xml:" + Cfg.template_xml + "--------------------\n");
            System.out.println("\n提示： --------------------开始生成xml:" + Cfg.output_dir_xml + "--------------------\n");
            produceXml(tableList);
        }

        if (po) {
            System.out.println("\n提示： --------------------读取模板po:" + Cfg.template_po + "--------------------\n");
            System.out.println("\n提示： --------------------po:" + Cfg.output_dir_po + "--------------------\n");
            producePo(tableList);
        }

        if (dao) {
            System.out.println("\n提示： --------------------读取模板dao:" + Cfg.template_dao + "--------------------\n");
            System.out.println("\n提示： --------------------dao:" + Cfg.output_dir_dao + "--------------------\n");
            produceNonColumnFile(tableList, Cfg.output_dir_dao, "Mapper.java", Cfg.template_dao);
        }

        System.out.println("\n提示： --------------------开始生成Service--------------------\n");
        //produceNonColumnFile(conn, tableList, path_service, "Service.java", serviceFtl);

        System.out.println("\n提示： ----------------------------------------生成文件结束----------------------------------------\n");
        if (!Executor.conn.isClosed()) Executor.conn.close();
    }


    private static Map<String, Object> initRootMap() {
        Map<String, Object> root = new HashMap<>();
        root.put("ts", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
        return root;
    }

    @SuppressWarnings("deprecation")
    private static void produceXml(List<Table> tableList) throws Exception {
        Configuration cfg = Configuration.getDefaultConfiguration();//Configuration cfg = new Configuration();
        cfg.setObjectWrapper(new DefaultObjectWrapper());// 指定模板如何检索数据模型，这是一个高级的主题了… 但先可以这么来用：
        cfg.setDirectoryForTemplateLoading(new File(Cfg.template_dir_name));// 指定模板文件从何处加载的数据源，这里设置成一个文件目录。

        Map<String, Object> root = initRootMap();
        for (Table table : tableList) {//循环表
            String tableName = table.getTable_name();
            String objectName = underlineToCamelhump(tableName);//驼峰命名
            String className = objectName.substring(0, 1).toUpperCase() + objectName.substring(1);//表名对应的对象名，首字母大写
            System.out.println("正在处理： " + tableName);
            root.put("table", tableName);
            root.put("className", className);
            root.put("objectName", objectName);
            File xmlFile = new File(Cfg.output_dir_xml + sep + className + "Mapper.xml");// 生成XML 只有xml和po需要知道具体字段信息
            String tableComment = table.getTable_comment();
            root.put("tableComment", tableComment);
            if (!xmlFile.exists()) {
                xmlFile.createNewFile();
            }
            Template xmlTemplate = cfg.getTemplate(Cfg.template_xml);
            //LineIterator it = FileUtils.lineIterator(xmlFile, Cfg.charset);

            root.put("poPath", Cfg.poPackage + "." + className);
            root.put("daoPath", Cfg.daoPackage + "." + className);
            root.put("poPackage", Cfg.poPackage);
            root.put("daoPackage", Cfg.daoPackage);
            List<Column> columnsList = Executor.getColumnsList(tableName);
            produceXmlColumnData(root, columnsList);
            FileOutputStream o = new FileOutputStream(xmlFile);
            Writer out = new OutputStreamWriter(o);
            xmlTemplate.process(root, out);
            o.flush();
            o.close();
            out.flush();
            out.close();
        }
    }

    @SuppressWarnings("deprecation")
    public static void producePo(List<Table> tableList) throws Exception {
        Configuration cfg = Configuration.getDefaultConfiguration();//Configuration cfg = new Configuration();
        cfg.setObjectWrapper(new DefaultObjectWrapper());// 指定模板如何检索数据模型，这是一个高级的主题了… 但先可以这么来用：
        cfg.setDirectoryForTemplateLoading(new File(Cfg.template_dir_name));// 指定模板文件从何处加载的数据源，这里设置成一个文件目录。

        Map<String, Object> root = initRootMap();
        for (Table table : tableList) {//循环表
            String tableName = table.getTable_name();
            String objectName = underlineToCamelhump(tableName);//驼峰命名
            String className = objectName.substring(0, 1).toUpperCase() + objectName.substring(1);//表名对应的对象名，首字母大写
            System.out.println("正在处理： " + tableName);
            root.put("table", tableName);
            root.put("className", className);
            root.put("objectName", objectName);
            File poFile = new File(Cfg.output_dir_po + sep + className + ".java");// 生成PO
            String tableComment = table.getTable_comment();
            root.put("tableComment", tableComment);
            if (!poFile.exists()) {
                poFile.createNewFile();
            }
            Template poTemplate = cfg.getTemplate(Cfg.template_po);
            //LineIterator it = FileUtils.lineIterator(poFile, charset);

            //root.put("servicePackage", Cfg.servicePackage);
            root.put("daoPackage", Cfg.daoPackage);
            root.put("poPackage", Cfg.poPackage);
            List<Column> columnsList = Executor.getColumnsList(tableName);
            producePoColumnData(root, columnsList);
            FileOutputStream o = new FileOutputStream(poFile);
            Writer out = new OutputStreamWriter(o);
            poTemplate.process(root, out);
            o.flush();
            o.close();
            out.flush();
            out.close();
        }
    }

    @SuppressWarnings("deprecation")
    private static void produceNonColumnFile(List<Table> tableList, String filePreffix, String fileSuffix, String ftl) throws Exception {
        Configuration cfg = Configuration.getDefaultConfiguration();//Configuration cfg = new Configuration();
        cfg.setObjectWrapper(new DefaultObjectWrapper());// 指定模板如何检索数据模型，这是一个高级的主题了… 但先可以这么来用：
        cfg.setDirectoryForTemplateLoading(new File(Cfg.template_dir_name));// 指定模板文件从何处加载的数据源，这里设置成一个文件目录。
        Map<String, Object> root = initRootMap();
        for (Table table : tableList) {//循环表
            String tableName = table.getTable_name();
            String objectName = underlineToCamelhump(tableName);//驼峰命名
            String className = objectName.substring(0, 1).toUpperCase() + objectName.substring(1);//表名对应的对象名，首字母大写
            System.out.println("正在处理： " + tableName);
            root.put("table", tableName);
            root.put("className", className);
            root.put("objectName", objectName);
            File serviceFile = new File(filePreffix + sep + className + fileSuffix);// 生成Service 或 mapper
            String tableComment = table.getTable_comment();
            root.put("tableComment", tableComment);
            root.put("poPath", Cfg.poPackage + "." + className);
            root.put("daoPath", Cfg.daoPackage + "." + className);
            //root.put("servicePackage", Cfg.servicePackage);
            root.put("daoPackage", Cfg.daoPackage);
            root.put("poPackage", Cfg.poPackage);
            if (!serviceFile.exists()) {
                serviceFile.createNewFile();
                Template serviceTemplate = cfg.getTemplate(ftl);
                FileOutputStream o = new FileOutputStream(serviceFile);
                Writer out = new OutputStreamWriter(o);
                serviceTemplate.process(root, out);
                o.flush();
                o.close();
                out.flush();
                out.close();
            }
        }
    }

    public static void produceXmlColumnData(Map<String, Object> map, List<Column> columnsList) {
        List<Map<String, String>> xmlColumnsMapList = new ArrayList<>();
        for (Column c : columnsList) {
            if (null == c) continue;
            Map<String, String> xmlColumnMap = new HashMap<>();
            String columnType = c.getData_type();
            String fieldName = c.getColumn_name();
            String fieldComment = c.getColumn_comment();
            String fieldHumpName = underlineToCamelhump(fieldName);//驼峰命名

            String colType = columnType.toLowerCase();
            colType = columnType.toUpperCase();
            if (columnType.equalsIgnoreCase(("int"))) {
                colType = "INTEGER";
            } else if (columnType.contains("bigint")) {
                colType = "BIGINT";
            } else if (columnType.contains("int")) {
                colType = "INTEGER";
            } else if (columnType.contains("char")) {
                colType = "VARCHAR";
            } else if (columnType.contains("datetime")) {
                colType = "TIMESTAMP";
            } else if (columnType.contains("date")) {
                colType = "DATE";
            } else if (columnType.contains("decimal")) {
                colType = "DECIMAL";
            } else if (columnType.contains("text")) {
                colType = "LONGVARCHAR";
            } else {
                colType = columnType.toUpperCase();
            }

            xmlColumnMap.put("columnType", colType);//数据库字段类型
            xmlColumnMap.put("fieldName", fieldName);//包含下划线
            xmlColumnMap.put("fieldHumpName", fieldHumpName);
            xmlColumnMap.put("fieldNameFirstUp", fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1).toLowerCase());
            xmlColumnMap.put("fieldHumpNameFirstUp", fieldHumpName.substring(0, 1).toUpperCase() + fieldHumpName.substring(1));
            //xmlColumnMap.put("javaType", javaType);
            //xmlColumnMap.put("completeJavaType", poPackage+"."+javaType);
            xmlColumnMap.put("fieldComment", fieldComment);
            xmlColumnsMapList.add(xmlColumnMap);
        }
        map.put("xmlColumnsMapList", xmlColumnsMapList);
    }

    public static void producePoColumnData(Map<String, Object> map, List<Column> columnsList) {
        List<Map<String, String>> poColumnsMapList = new ArrayList<>();
        for (Column c : columnsList) {
            Map<String, String> poColumnMap = new HashMap<>();
            String columnType = c.getData_type();
            String fieldName = c.getColumn_name();
            String fieldComment = c.getColumn_comment();
            String javaType = "String";
            String datePattern = null;
            if (columnType.equalsIgnoreCase("int") || columnType.equalsIgnoreCase("tinyint")) {
                javaType = "Integer";
            } else if (columnType.equalsIgnoreCase("bigint")) {
                javaType = "Long";
            } else if (columnType.equalsIgnoreCase("varchar") || columnType.equalsIgnoreCase("char")) {
                javaType = "String";
            } else if (columnType.equalsIgnoreCase("date")) {
                datePattern = "yyyy-MM-dd";
                javaType = "Date";
            } else if (columnType.equalsIgnoreCase("datetime")) {
                datePattern = "yyyy-MM-dd HH:mm:ss";
                javaType = "Date";
            } else if (columnType.equalsIgnoreCase("decimal")) {
                javaType = "BigDecimal";
            } else if (columnType.equalsIgnoreCase("double") || columnType.equalsIgnoreCase("float") || columnType.equalsIgnoreCase("numeric")) {
                javaType = "Double";
            }
            boolean filtFlag = false;
		  /*for(int i = 0;i<columnFilterAry.length;i++){
			  if(fieldName.equalsIgnoreCase(columnFilterAry[i])){
				  filtFlag = true;break;
			  }
		  }*/
            if (!filtFlag) {
                String fieldHumpName = underlineToCamelhump(fieldName);//驼峰命名
                //fieldName = fieldName.toLowerCase();
                poColumnMap.put("columnType", columnType.toUpperCase());//数据库字段类型
                poColumnMap.put("fieldName", fieldName);//包含下划线
                poColumnMap.put("fieldHumpName", fieldHumpName);
                poColumnMap.put("fieldNameFirstUp", fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1).toLowerCase());
                poColumnMap.put("fieldHumpNameFirstUp", fieldHumpName.substring(0, 1).toUpperCase() + fieldHumpName.substring(1));
                poColumnMap.put("javaType", javaType);
                poColumnMap.put("completeJavaType", Cfg.poPackage + "." + javaType);
                poColumnMap.put("fieldComment", fieldComment);
                if (null != datePattern) poColumnMap.put("datePattern", datePattern);
                poColumnsMapList.add(poColumnMap);
            }
        }
        map.put("poColumnsMapList", poColumnsMapList);
    }

    /**
     * 将下划线风格替换为驼峰风格
     */
    public static String underlineToCamelhump(String str) {

        str = str.replace(Cfg.filter_prefix, "");//需要过滤的前缀：rtv_

        Matcher matcher = Pattern.compile("_[a-z]").matcher(str);
        StringBuilder builder = new StringBuilder(str);
        for (int i = 0; matcher.find(); i++) {
            builder.replace(matcher.start() - i, matcher.end() - i, matcher.group().substring(1).toUpperCase());
        }
        if (Character.isUpperCase(builder.charAt(0))) {
            builder.replace(0, 1, String.valueOf(Character.toLowerCase(builder.charAt(0))));
        }
        return builder.toString();
    }

    public static String getDateNumStr() {
        Calendar c = Calendar.getInstance();

        int month = c.get(Calendar.MONTH) + 1;
        String monthStr = String.valueOf(month);
        if (month < 10) monthStr = "0" + monthStr;

        int day = c.get(Calendar.DAY_OF_MONTH);
        String dayStr = String.valueOf(day);
        if (day < 10) dayStr = "0" + dayStr;

        int hour = c.get(Calendar.HOUR);
        String hourStr = String.valueOf(hour);
        if (hour < 10) hourStr = "0" + hourStr;

        int minute = c.get(Calendar.MINUTE);
        String minuteStr = String.valueOf(minute);
        if (minute < 10) minuteStr = "0" + minuteStr;

        int second = c.get(Calendar.SECOND);
        String secondStr = String.valueOf(second);
        if (second < 10) secondStr = "0" + secondStr;

        String timeStr = "" + c.get(Calendar.YEAR) + "-" + monthStr + "-" + dayStr + "-" + hourStr + "-" + minuteStr + "-" + second + "-" + c.get(Calendar.MILLISECOND);
        return timeStr;
    }

}
