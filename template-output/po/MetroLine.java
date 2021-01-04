package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: MetroLine
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 地铁线路表
 * @time 2021-01-04 12:10:36
 */
@Data
public class MetroLine {

	/**
	 * 表项主键
	 */
	private Long id;
	
	/**
	 * 组织ID
	 */
	private Integer corpid;
	
	/**
	 * 平台编号：CDDT, BJDT等
	 */
	private String platform;
	
	/**
	 * 平台名称：成都地铁，北京地铁等
	 */
	private String platformName;
	
	/**
	 * 线路编号：1，2，3，4等
	 */
	private String lineCode;
	
	/**
	 * 线路名称：一号线，二号线等
	 */
	private String lineName;
	
	/**
	 * 状态：1启用，2停用
	 */
	private Integer state;
	
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	
	
}