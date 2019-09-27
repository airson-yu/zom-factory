package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: TaskUser
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 任务-用户关联表
 * @time 2019-09-27 12:16:52
 */
@Data
public class TaskUser {

	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 
	 */
	private Integer corpId;
	
	/**
	 * 任务ID
	 */
	private Long taskId;
	
	/**
	 * 工作内容ID
	 */
	private Long taskJobId;
	
	/**
	 * 分配的用户ID
	 */
	private Long uid;
	
	/**
	 * 登录时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date logonTime;
	
	
	
}