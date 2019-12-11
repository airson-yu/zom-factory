package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: UserSmsRecord
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark tx/rx sms record table
 * @time 2019-12-11 15:42:59
 */
@Data
public class UserSmsRecord {

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
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date start;
	
	/**
	 * peer phone number
	 */
	private Long phone;
	
	/**
	 * 0 tx sms, 1: rx sms
	 */
	private Integer type;
	
	/**
	 * 
	 */
	private String content;
	
	/**
	 * 
	 */
	private Integer corpId;
	
	/**
	 * the date when BMS report record
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date reportDate;
	
	
	
}