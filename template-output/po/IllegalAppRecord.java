package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: IllegalAppRecord
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark User installed illegal app record table
 * @time 2019-12-11 15:42:59
 */
@Data
public class IllegalAppRecord {

	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 
	 */
	private Long uid;
	
	/**
	 * 
	 */
	private String aname;
	
	/**
	 * install date
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date idate;
	
	/**
	 * uninstall date
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date udate;
	
	/**
	 * 0 deleted, 1 installing
	 */
	private Integer status;
	
	/**
	 * 
	 */
	private Integer corpId;
	
	/**
	 * the date when BMS report installed app record 
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date inreportDate;
	
	/**
	 *  the date when BMS report uninstalled app record 
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date unreportDate;
	
	/**
	 * app label name
	 */
	private String label;
	
	/**
	 * phone type, the app name may have different name on diffent type
	 */
	private String device;
	
	
	
}