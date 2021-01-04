package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: MetroLineGroupMap
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 地铁线路与群组关联表
 * @time 2021-01-04 12:10:36
 */
@Data
public class MetroLineGroupMap {

	/**
	 * 表项主键
	 */
	private Long id;
	
	/**
	 * 组织ID
	 */
	private Integer corpid;
	
	/**
	 * 群组ID
	 */
	private Long groupId;
	
	/**
	 * 群组类型：1普通，2地铁工作组，3地铁行车组
	 */
	private Integer groupType;
	
	/**
	 * 群组优化级
	 */
	private Integer groupLevel;
	
	/**
	 * 平台编号：CDDT, BJDT等
	 */
	private String platform;
	
	/**
	 * 线路编号：1，2，3，4等
	 */
	private String lineCode;
	
	
	
}