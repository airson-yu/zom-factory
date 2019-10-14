package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: TaskJob
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 工作内容表
 * @time 2019-10-12 10:19:14
 */
@Data
public class TaskJob {

	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 
	 */
	private Integer corpId;
	
	/**
	 * 所属任务ID，0为模板
	 */
	private Long taskId;
	
	/**
	 * 
	 */
	private String uniqueId;
	
	/**
	 * 创建人ID
	 */
	private Long uid;
	
	/**
	 * 1卡点，2巡逻，3处警
	 */
	private Integer type;
	
	/**
	 * 
	 */
	private String name;
	
	/**
	 * 不带日期的时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	
	/**
	 * 不带日期的时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	
	/**
	 * 登录人数
	 */
	private Integer logonNumber;
	
	/**
	 * NULL为模板，非NULL为实例
	 */
	private Long templateId;
	
	/**
	 * 位置信息json
	 */
	private String locJson;
	
	/**
	 * 预留
	 */
	private Integer state;
	
	/**
	 * 部门
	 */
	private String dept;
	
	
	
}