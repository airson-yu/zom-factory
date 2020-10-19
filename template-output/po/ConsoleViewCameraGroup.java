package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: ConsoleViewCameraGroup
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 客户端能够访问摄像机组的节点记录表
 * @time 2020-10-19 13:46:11
 */
@Data
public class ConsoleViewCameraGroup {

	/**
	 * 
	 */
	private Long id;
	
	/**
	 * BMS或Console uid
	 */
	private Integer uid;
	
	/**
	 * 摄像机组的在数据库中的主键ID
	 */
	private Integer groupid;
	
	/**
	 * 1--可以看，0--不能看
	 */
	private Integer viewstate;
	
	
	
}