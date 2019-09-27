package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: Task
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 任务表
 * @time 2019-09-27 12:16:52
 */
@Data
public class Task {

	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 
	 */
	private Integer corpId;
	
	/**
	 * 
	 */
	private String uniqueId;
	
	/**
	 * 创建人ID
	 */
	private Long uid;
	
	/**
	 * 任务组ID
	 */
	private Long tgid;
	
	/**
	 * 1一级勤务，2二级勤务，3三级勤务，4日常处警
	 */
	private Integer type;
	
	/**
	 * 
	 */
	private String name;
	
	/**
	 * 
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	
	/**
	 * 
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	
	/**
	 * 预留
	 */
	private Integer state;
	
	
	
}