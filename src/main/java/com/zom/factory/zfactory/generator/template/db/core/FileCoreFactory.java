package com.zom.factory.zfactory.generator.template.db.core;

public class FileCoreFactory {

}

/*package com.zom.factory.template.db.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import po.Column;
import po.Table;

public class FileCoreFactory {
	
	private static String producePart = "all";
	
	private static String sep = File.separator;
	
	private static String db_name = null;
	private static String project_name = null;
	private static String project_location = "D:"+sep+"workspace"+sep+"zzzz_test_dir"+sep;
	private static String backup_location = "F:"+sep+"autoback"+sep;
	
	//private final static String table_names = "";
	private static String[] tableFilterAry = {};//需要过滤的表
	private static String charset = "UTF-8";
	private final static String[] columnFilterAry = {};
	private final static String[] columnFilterAry = //不过滤
			{"id","serial","type_id","type_code","dict_code","link_type","owner_id","owner_ids","link_id",
			"group_id","grade_id","main_status","logic_status","main_ids","attr_str","attr_str2",
			"attr_int","attr_int2","attr_num","attr_date","attr_datetime","attr_decimal","sequence","description",
			"remark","deleted_at","deleted_by","created_at","created_by","updated_at","updated_by"};
	//private static String[] columnFilterAry = {};
	
	private static String poPackage = "com.lhfeiyu.po";
	private static String daoPackage = "com.lhfeiyu.dao";
	private static String servicePackage = "com.lhfeiyu.service";
	private static String path_xml  = null;
	private static String path_base = null;
	private static String path_po   = null;
	private static String path_mapper = null;
	private static String path_service = null;
	private static String auto_backup_dir = null;
	
	public static void init(String dbName, String projectName, String projectLocation, String backupLocation, String[] filtTableAry, String produce) throws Exception{
		db_name = dbName;
		project_name = projectName;
		if(null != projectLocation)project_location = projectLocation;
		if(null != backupLocation)backup_location = backupLocation;
		if(null != tableFilterAry)tableFilterAry = filtTableAry;
		if(null != produce)producePart = produce;
		
		auto_backup_dir = backup_location + project_name + sep;
		path_base = project_location + project_name + sep+"src"+sep+"main"+sep+"java"+sep+"com"+sep+"lhfeiyu"+sep;
		path_xml  = project_location + project_name + sep+"src"+sep+"main"+sep+"resources"+sep+"com"+sep+"lhfeiyu"+sep+"dao";
		path_po   = path_base+"po";
		path_mapper = path_base+"dao";
		path_service = path_base+"service";
		
		if(producePart.equals("base")){
			//auto_backup_dir = backup_location + project_name + "-";
			//path_base = project_location + project_name + "-src-main-java-com-lhfeiyu-";
			path_xml  = project_location + project_name + sep+"src"+sep+"main"+sep+"resources"+sep+"com"+sep+"lhfeiyu"+sep+"dao"+sep+"base";
			path_po   = path_base+"po"+sep+"base";
			path_mapper = path_base+"dao"+sep+"base";
			path_service = path_base+"service"+sep+"base";
			
			poPackage = poPackage+".base";
			daoPackage = daoPackage+".base";
			servicePackage = servicePackage+".base";
		}
		
	}
	
	public static void produceDomain(String dbName, String projectName, String projectLocation, String backupLocation, String[] filtTableAry) throws Exception{
		init(dbName, projectName, projectLocation, backupLocation, filtTableAry, "domain");
		beginProduce();
	}
	
	public static void produceBase(String dbName, String projectName, String projectLocation, String backupLocation, String[] filtTableAry) throws Exception{
		init(dbName, projectName, projectLocation, backupLocation, filtTableAry, "base");
		beginProduce();
	}
	
	public static void beginProduce() throws Exception{
		if(null == db_name || null == project_name)return;
		backupBeforeProduce();
		produceAll(false);
	}
	
	public static void produceAll(boolean isInit) throws Exception{
		if(null == db_name || null == project_name)return;
		System.out.println("\n提示： ----------------------------------------开始生成文件----------------------------------------\n");
		Connection conn = DB.connectMysql(db_name);
		List<Table> tableList = DB.getTableList(conn, db_name, tableFilterAry);
		if(producePart.equals("domain") || producePart.equals("base")){//生成全部的表
			List<Table> domainTableList = new ArrayList<Table>();
			for(Table table : tableList){//循环表
				String tableComment = table.getTable_comment();
				if(producePart.equals("domain")){//只生成业务表
					if(null != tableComment && tableComment.contains("业务：")){
						domainTableList.add(table);
					}
				}else if(producePart.equals("base")){//只生成基本表
					if(null == tableComment || !tableComment.contains("业务：")){
						domainTableList.add(table);
					}
				}
			}
			tableList = domainTableList;
		}
		
		//List<String> tableNameList = new ArrayList<String>();//TODO 测试
		//tableNameList.add("user");
		//tableNameList.add("login_log");
		//String serviceFtl = "Service.ftl";
		String mapperFtl = "Mapper.ftl";
		if(producePart.equals("base")){ // 基础库模板
			serviceFtl = "base/BaseService.ftl";
			mapperFtl = "base/BaseMapper.ftl";
		}
		
		System.out.println("\n提示： --------------------开始生成Service--------------------\n");
		//produceNonColumnFile(conn, tableList, path_service, "Service.java", serviceFtl);
		
		System.out.println("\n提示： --------------------开始生成Mapper--------------------\n");
		produceNonColumnFile(conn, tableList, path_mapper, "Mapper.java", mapperFtl);
		
		System.out.println("\n提示： --------------------开始生成Xml--------------------\n");
		produceXml(conn, tableList, path_xml, isInit);
		
		System.out.println("\n提示： --------------------开始生成PO--------------------\n");
		producePo(conn, tableList, path_po);
		
		System.out.println("\n提示： ----------------------------------------生成文件结束----------------------------------------\n");
	}
	
	public static void backupBeforeProduce() throws Exception{
		System.out.println("\n提示： -------------------- 开始备份 --------------------\n");
		String timeStr = getDateNumStr();
		String poZipPath 		= auto_backup_dir+timeStr+sep+"po.zip";
		String xmlZipPath 		= auto_backup_dir+timeStr+sep+"xml.zip";
		String mapperZipPath 	= auto_backup_dir+timeStr+sep+"mapper.zip";
		String serviceZipPath 	= auto_backup_dir+timeStr+sep+"service.zip";
		
		File file = new File(auto_backup_dir+timeStr+sep);
		if(!file.exists())file.mkdirs();
		
		System.out.println("提示： --------------------开始备份Service文件--------------------");
		ZipUtil.zip(path_service, serviceZipPath);
		System.out.println("提示： --------------------开始备份Mapper文件--------------------");
		ZipUtil.zip(path_mapper, mapperZipPath);
		System.out.println("提示： --------------------开始备份Xml文件--------------------");
		ZipUtil.zip(path_xml, xmlZipPath);
		System.out.println("提示： --------------------开始备份Po文件--------------------");
		ZipUtil.zip(path_po, poZipPath);
		System.out.println("提示： -------------------- 备份结束 --------------------");
	}
	
	@SuppressWarnings("deprecation")
	public static void produceXml(Connection conn, List<Table> tableList, String path, boolean isInit) throws Exception{  
		Configuration cfg = Configuration.getDefaultConfiguration();//Configuration cfg = new Configuration();
		cfg.setObjectWrapper(new DefaultObjectWrapper());// 指定模板如何检索数据模型，这是一个高级的主题了… 但先可以这么来用：
		cfg.setDirectoryForTemplateLoading(new File("templates"));// 指定模板文件从何处加载的数据源，这里设置成一个文件目录。
		
		Map<String,Object> root = new HashMap<String,Object>();
		for(Table table : tableList){//循环表
			String tableName = table.getTable_name();
			String objectName = underlineToCamelhump(tableName);//驼峰命名
			String className = objectName.substring(0,1).toUpperCase()+objectName.substring(1);//表名对应的对象名，首字母大写
			if(producePart.equals("base")){
				objectName = "base"+className;
				className = "Base"+className;
			}
			System.out.println("正在处理： "+tableName);
			root.put("table", tableName);
			root.put("isInit", isInit);
			root.put("className", className);
			root.put("objectName", objectName);
			File xmlFile = new File(path+sep+className+"Mapper.xml");// 生成XML 只有xml和po需要知道具体字段信息
			String tableComment = table.getTable_comment();
			root.put("tableComment", tableComment);
			if(!xmlFile.exists()){xmlFile.createNewFile();}  
			Template xmlTemplate = cfg.getTemplate("MapperXml.ftl");
			if(producePart.equals("base")){ // 基础库模板
				xmlTemplate = cfg.getTemplate("base/BaseMapperXml.ftl");
			}
			LineIterator it = FileUtils.lineIterator(xmlFile, charset);
			
			String selfAddContent = "";//自定义内容，需要保留
			boolean beginFlag = false;
			boolean firstLine = false;
			try {
			   while (it.hasNext()) {
			     String line = it.nextLine();
			     if(line.contains("_@CAUTION_SELF_FINISH@_")) {//遇到结束标记则退出
			    	 break;
			     }
			     if(beginFlag){
			    	 if(firstLine){
			    		 line = line.replace("\t", ""); 
			    		 firstLine = false;
			    	 }
			    	 selfAddContent += line +"\r\n";continue;//已经有开始标记则持续读取
			     }
			     if(line.contains("_@CAUTION_SELF_BEGIN@_")){//遇到开始标记则标记为开始
			    	 firstLine = true;
			    	 beginFlag = true;
			     }
			   }
			 } finally {
			   LineIterator.closeQuietly(it);
			 }
			if(selfAddContent.length()>0){
				selfAddContent = selfAddContent.substring(0, selfAddContent.length()-2);//去掉首行空格
			}
			root.put("selfAddContent", selfAddContent);//自定义添加的内容，需要保留
			root.put("poPath", poPackage+"."+className);
			root.put("daoPath", daoPackage+"."+className);
			root.put("poPackage", poPackage);
			root.put("daoPackage", daoPackage);
			List<Column> columnsList = DB.getColumnsList(conn, db_name, tableName);
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
	public static void producePo(Connection conn, List<Table> tableList, String path) throws Exception{  
		Configuration cfg = Configuration.getDefaultConfiguration();//Configuration cfg = new Configuration();
		cfg.setObjectWrapper(new DefaultObjectWrapper());// 指定模板如何检索数据模型，这是一个高级的主题了… 但先可以这么来用：
		cfg.setDirectoryForTemplateLoading(new File("templates"));// 指定模板文件从何处加载的数据源，这里设置成一个文件目录。
		
		Map<String,Object> root = new HashMap<String,Object>();
		for(Table table : tableList){//循环表
			String tableName = table.getTable_name();
			String objectName = underlineToCamelhump(tableName);//驼峰命名
			String className = objectName.substring(0,1).toUpperCase()+objectName.substring(1);//表名对应的对象名，首字母大写
			System.out.println("正在处理： "+tableName);
			root.put("table", tableName);
			root.put("className", className);
			root.put("objectName", objectName);
			if(producePart.equals("base")){
				root.put("className", "Base"+className);
				root.put("objectName", "base"+className);
			}
			File poFile = new File(path+sep+className+".java");// 生成PO
			if(producePart.equals("base")){
				poFile = new File(path+sep+"Base"+className+".java");
			}
			String tableComment = table.getTable_comment();
			root.put("tableComment", tableComment);
			if(!poFile.exists()){poFile.createNewFile();}  
			Template poTemplate = cfg.getTemplate("Po.ftl");
			if(producePart.equals("base")){ // 基础库模板
				poTemplate = cfg.getTemplate("base/BasePo.ftl");
			}
			LineIterator it = FileUtils.lineIterator(poFile, charset);
			
			String selfAddImportContent = "";//自定义内容，需要保留 - 导入
			String selfAddFieldContent = "";//自定义内容，需要保留 - 字段
			String selfAddGetSetContent = "";//自定义内容，需要保留 - getset
			boolean importBeginFlag = false;
			boolean fieldBeginFlag = false;
			boolean getsetBeginFlag = false;
			
			boolean importFirstLine = false;
			boolean fieldFirstLine = false;
			boolean getsetFirstLine = false;
			try {
			   while (it.hasNext()) {
			     String line = it.nextLine();
			     if(line.contains("_@CAUTION_SELF_IMPORT_FINISH@_")) {//遇到结束标记则退出 - 导入
			    	 importFirstLine = false;
			    	 importBeginFlag = false;
			    	 continue;
			     }
			     if(line.contains("_@CAUTION_SELF_FIELD_FINISH@_")) {//遇到结束标记则退出 - 字段
			    	 fieldFirstLine = false;
			    	 fieldBeginFlag = false;
			    	 continue;
			     }
			     if(line.contains("_@CAUTION_SELF_GETSET_FINISH@_")) {//遇到结束标记则退出 - getset
			    	 break;
			     }
			     
			     //导入
			     if(importBeginFlag){
			    	 if(importFirstLine){
			    		 line = line.replace("\t", ""); 
			    		 importFirstLine = false;
			    	 }
			    	 selfAddImportContent += line +"\r\n";continue;//已经有开始标记则持续读取 - 导入
			     }
			     if(line.contains("_@CAUTION_SELF_IMPORT_BEGIN@_")){//遇到开始标记则标记为开始
			    	 importFirstLine = true;
			    	 importBeginFlag = true;
			     }
			     
			     //字段
			     if(fieldBeginFlag){
			    	 if(fieldFirstLine){
			    		 line = line.replace("\t", ""); 
			    		 fieldFirstLine = false;
			    	 }
			    	 selfAddFieldContent += line +"\r\n";continue;//已经有开始标记则持续读取 - 字段
			     }
			     if(line.contains("_@CAUTION_SELF_FIELD_BEGIN@_")){//遇到开始标记则标记为开始
			    	 fieldFirstLine = true;
			    	 fieldBeginFlag = true;
			     }
			     
			     //getset
			     if(getsetBeginFlag){
			    	 if(getsetFirstLine){
			    		 line = line.replace("\t", ""); 
			    		 getsetFirstLine = false;
			    	 }
			    	 selfAddGetSetContent += line +"\r\n";continue;//已经有开始标记则持续读取
			     }
			     if(line.contains("_@CAUTION_SELF_GETSET_BEGIN@_")){//遇到开始标记则标记为开始
			    	 getsetFirstLine = true;
			    	 getsetBeginFlag = true;
			     }
			     
			   }
			 } finally {
			   LineIterator.closeQuietly(it);
			 }
			
			if(selfAddImportContent.length()>0){
				selfAddImportContent = selfAddImportContent.substring(0, selfAddImportContent.length()-2);//去掉首行空格 - 导入
			}
			
			if(selfAddFieldContent.length()>0){
				selfAddFieldContent = selfAddFieldContent.substring(0, selfAddFieldContent.length()-2);//去掉首行空格 - 字段
			}
			注释后setget方法第一行则不会再产生空格
			 if(selfAddGetSetContent.length()>0){
				selfAddGetSetContent = selfAddGetSetContent.substring(0, selfAddGetSetContent.length()-2);//去掉首行空格 - getset
			}
			
			root.put("selfAddImportContent", selfAddImportContent);//自定义添加的内容，需要保留
			root.put("selfAddFieldContent", selfAddFieldContent);//自定义添加的内容，需要保留
			root.put("selfAddGetSetContent", selfAddGetSetContent);//自定义添加的内容，需要保留
			root.put("servicePackage", servicePackage);
			root.put("daoPackage", daoPackage);
			root.put("poPackage", poPackage);
			List<Column> columnsList = DB.getColumnsList(conn, db_name, tableName);
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
	public static void produceNonColumnFile(Connection conn, List<Table> tableList,
			String filePreffix, String fileSuffix, String ftl) throws Exception{  
		Configuration cfg = Configuration.getDefaultConfiguration();//Configuration cfg = new Configuration();
		cfg.setObjectWrapper(new DefaultObjectWrapper());// 指定模板如何检索数据模型，这是一个高级的主题了… 但先可以这么来用：
		cfg.setDirectoryForTemplateLoading(new File("templates"));// 指定模板文件从何处加载的数据源，这里设置成一个文件目录。
		Map<String,Object> root = new HashMap<String,Object>();
		for(Table table : tableList){//循环表
			String tableName = table.getTable_name();
			String objectName = underlineToCamelhump(tableName);//驼峰命名
			String className = objectName.substring(0,1).toUpperCase()+objectName.substring(1);//表名对应的对象名，首字母大写
			if(producePart.equals("base")){
				objectName = "base"+className;
				className = "Base"+className;
			}
			System.out.println("正在处理： "+tableName);
			root.put("table", tableName);
			root.put("className", className);
			root.put("objectName", objectName);
			File serviceFile = new File(filePreffix+sep+className+fileSuffix);// 生成Service 或 mapper
			String tableComment = table.getTable_comment();
			root.put("tableComment", tableComment);
			root.put("poPath", poPackage+"."+className);
			root.put("daoPath", daoPackage+"."+className);
			root.put("servicePackage", servicePackage);
			root.put("daoPackage", daoPackage);
			root.put("poPackage", poPackage);
			if(!serviceFile.exists()){
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
	
	public static void produceXmlColumnData(Map<String,Object> map, List<Column> columnsList) throws Exception{
		List<Map<String,String>> xmlColumnsMapList = new ArrayList<Map<String,String>>();
		for(Column c : columnsList){
		  if(null == c)continue;
		  Map<String,String> xmlColumnMap = new HashMap<String,String>();
		  String columnType = c.getData_type(); 
		  String fieldName = c.getColumn_name();
		  String fieldComment = c.getColumn_comment();
		  String fieldHumpName = underlineToCamelhump(fieldName);//驼峰命名
		  
		  String javaType = columnType.toLowerCase();
		  if(columnType.contains("int")){
			  javaType = "INTEGER";
		  }else if(columnType.contains("char")){
			  javaType = "VARCHAR";
		  }else if( columnType.contains("datetime")){
			  javaType = "TIMESTAMP";
		  }else if(columnType.contains("date")){
			  javaType = "DATE";
		  }else if(columnType.contains("decimal")){
			  javaType = "DECIMAL";
		  }else if(columnType.contains("text")){
			  javaType = "LONGVARCHAR";
		  }else{
			  javaType = columnType.toUpperCase();
		  }
		  
		  xmlColumnMap.put("columnType", javaType);//数据库字段类型
		  xmlColumnMap.put("fieldName", fieldName);//包含下划线
		  xmlColumnMap.put("fieldHumpName", fieldHumpName);
		  xmlColumnMap.put("fieldNameFirstUp", fieldName.substring(0,1).toUpperCase()+fieldName.substring(1).toLowerCase());
		  xmlColumnMap.put("fieldHumpNameFirstUp", fieldHumpName.substring(0,1).toUpperCase()+fieldHumpName.substring(1));
		  //xmlColumnMap.put("javaType", javaType);
		  //xmlColumnMap.put("completeJavaType", poPackage+"."+javaType);
		  xmlColumnMap.put("fieldComment", fieldComment);
		  xmlColumnsMapList.add(xmlColumnMap);
	  }
	  map.put("xmlColumnsMapList", xmlColumnsMapList);
	}
	
	public static void producePoColumnData(Map<String,Object> map, List<Column> columnsList) throws Exception{
		List<Map<String,String>> poColumnsMapList = new ArrayList<Map<String,String>>();
		for(Column c : columnsList){
		  Map<String,String> poColumnMap = new HashMap<String,String>();
		  String columnType = c.getData_type(); 
		  String fieldName = c.getColumn_name();
		  String fieldComment = c.getColumn_comment();
		  String javaType = "String";
		  String datePattern = null;
		  if(columnType.equalsIgnoreCase("int") || columnType.equalsIgnoreCase("smallint")){
			  javaType = "Integer";
		  }else if(columnType.equalsIgnoreCase("bigint")){
			  javaType = "Long";
		  }else if(columnType.equalsIgnoreCase("varchar") || columnType.equalsIgnoreCase("char")){
			  javaType = "String";
		  }else if(columnType.equalsIgnoreCase("date")){
			  datePattern = "yyyy-MM-dd";
			  javaType = "Date";
		  }else if(columnType.equalsIgnoreCase("datetime")){
			  datePattern = "yyyy-MM-dd HH:mm:ss";
			  javaType = "Date";
		  }else if(columnType.equalsIgnoreCase("decimal")){
			  javaType = "BigDecimal";
		  }else if(columnType.equalsIgnoreCase("double") || columnType.equalsIgnoreCase("float") || columnType.equalsIgnoreCase("numeric")){
			  javaType = "Double";
		  }
		  boolean filtFlag = false;
		  for(int i = 0;i<columnFilterAry.length;i++){
			  if(fieldName.equalsIgnoreCase(columnFilterAry[i])){
				  filtFlag = true;break;
			  }
		  }
		  if(!filtFlag){
			  String fieldHumpName = underlineToCamelhump(fieldName);//驼峰命名
			  //fieldName = fieldName.toLowerCase();
			  poColumnMap.put("columnType", columnType.toUpperCase());//数据库字段类型
			  poColumnMap.put("fieldName", fieldName);//包含下划线
			  poColumnMap.put("fieldHumpName", fieldHumpName);
			  poColumnMap.put("fieldNameFirstUp", fieldName.substring(0,1).toUpperCase()+fieldName.substring(1).toLowerCase());
			  poColumnMap.put("fieldHumpNameFirstUp", fieldHumpName.substring(0,1).toUpperCase()+fieldHumpName.substring(1));
			  poColumnMap.put("javaType", javaType);
			  poColumnMap.put("completeJavaType", poPackage+"."+javaType);
			  poColumnMap.put("fieldComment", fieldComment);
			  if(null != datePattern)poColumnMap.put("datePattern", datePattern);
			  poColumnsMapList.add(poColumnMap);
		  }
	  }
	  map.put("poColumnsMapList", poColumnsMapList);
	}
	
	*//**
 * 将下划线风格替换为驼峰风格
 *//*
							public static String underlineToCamelhump(String str) {
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
							
							public static String getDateNumStr(){
							Calendar c = Calendar.getInstance();
							
							int month = c.get(Calendar.MONTH)+1;
							String monthStr = String.valueOf(month);
							if(month<10)monthStr = "0"+monthStr;
							
							int day = c.get(Calendar.DAY_OF_MONTH);
							String dayStr = String.valueOf(day);
							if(day<10)dayStr = "0"+dayStr;
							
							int hour = c.get(Calendar.HOUR);
							String hourStr = String.valueOf(hour);
							if(hour<10)hourStr = "0"+hourStr;
							
							int minute = c.get(Calendar.MINUTE);
							String minuteStr = String.valueOf(minute);
							if(minute<10)minuteStr = "0"+minuteStr;
							
							int second = c.get(Calendar.SECOND);
							String secondStr = String.valueOf(second);
							if(second<10)secondStr = "0"+secondStr;
							
							String timeStr = ""+c.get(Calendar.YEAR)+"-"+monthStr+"-"+dayStr+"-"+hourStr+"-"+minuteStr+"-"+second+"-"+c.get(Calendar.MILLISECOND);
							return timeStr;
							}
							
							}
							*/