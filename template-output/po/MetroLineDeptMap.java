package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: MetroLineDeptMap
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 地铁线路与部门关联表
 * @time 2021-01-04 12:10:36
 */
@Data
public class MetroLineDeptMap {

	/**
	 * 表项主键
	 */
	private Long id;
	
	/**
	 * 组织ID
	 */
	private Integer corpid;
	
	/**
	 * 部门ID
	 */
	private String deptUniqueId;
	
	/**
	 * 部门类型：1普通 2公司 3分公司 4地铁线路 5地铁站区 6地铁站台
	 */
	private Integer deptType;
	
	/**
	 * 部门优化级
	 */
	private Integer deptLevel;
	
	/**
	 * 平台编号：CDDT, BJDT等
	 */
	private String platform;
	
	/**
	 * 线路编号：1，2，3，4等
	 */
	private String lineCode;
	
	
	
}