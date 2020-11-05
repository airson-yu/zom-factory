package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: StaIgnoreDept
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 屏蔽部门表
 * @time 2020-11-05 10:49:50
 */
@Data
public class StaIgnoreDept {

	/**
	 * 表主键ID
	 */
	private Long id;
	
	/**
	 * 调度台ID
	 */
	private Long uid;
	
	/**
	 * 部门ID
	 */
	private String deptId;
	
	/**
	 * 状态：0 屏蔽，1 启用
	 */
	private Integer state;
	
	
	
}