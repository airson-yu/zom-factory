package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: DeviceType
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 
 * @time 2020-08-27 18:09:25
 */
@Data
public class DeviceType {

	/**
	 * 
	 */
	private Integer id;
	
	/**
	 * 
	 */
	private String device;
	
	/**
	 * 
	 */
	private String description;
	
	
	
}