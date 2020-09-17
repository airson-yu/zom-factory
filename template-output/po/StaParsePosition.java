package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: StaParsePosition
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 数据解析位置标识表
 * @time 2020-09-17 18:51:39
 */
@Data
public class StaParsePosition {

	/**
	 * 表项主键
	 */
	private Long id;
	
	/**
	 * 数据表名
	 */
	private String tableName;
	
	/**
	 * 数据表主键ID：标识上次数据解析到哪个位置
	 */
	private Long lastPkId;
	
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	
	
}