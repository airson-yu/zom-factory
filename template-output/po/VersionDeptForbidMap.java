package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: VersionDeptForbidMap
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 版本关联到部门，部门下的用户禁止用老版本登录
 * @time 2020-04-01 10:50:11
 */
@Data
public class VersionDeptForbidMap {

	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 版本ID
	 */
	private Long vid;
	
	/**
	 * 部门UNIQUE ID
	 */
	private String deptUniqueId;
	
	/**
	 * 禁止类型：1禁止低版本登录
	 */
	private Integer type;
	
	/**
	 * 组织ID
	 */
	private Integer corpId;
	
	
	
}