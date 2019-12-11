package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: AppWhiteList
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark APP白名单记录表
 * @time 2019-12-11 15:42:59
 */
@Data
public class AppWhiteList {

	/**
	 * 
	 */
	private Long id;
	
	/**
	 * app name
	 */
	private String aname;
	
	/**
	 * app label name
	 */
	private String label;
	
	/**
	 * 
	 */
	private String descp;
	
	/**
	 * phone type, the app name may have different name on diffent type
	 */
	private String device;
	
	/**
	 * corp Id
	 */
	private Integer corpId;
	
	
	
}