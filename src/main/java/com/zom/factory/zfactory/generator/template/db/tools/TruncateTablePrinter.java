package com.zom.factory.zfactory.generator.template.db.tools;

/**
 * 生成清空表语句
 * @author airson
 */
public class TruncateTablePrinter {

	public static String[] tables = {"rtv_user","rtv_group"};
	
	public static void main(String[] args) {
		for(int i = 0; i<tables.length; i++){
			String sql = "truncate `"+tables[i]+"`;alter table `"+tables[i]+"` auto_increment=1;";
			System.out.println(sql);
		}
	}
	
}
