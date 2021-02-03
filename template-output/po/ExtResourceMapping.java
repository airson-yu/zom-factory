package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: ExtResourceMapping
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 外部系统资源映射表
 * @time 2021-02-03 18:28:03
 */
@Data
public class ExtResourceMapping {

	/**
	 * 主键ID
	 */
	private Long id;
	
	/**
	 * 外部系统代号
	 */
	private Integer esysid;
	
	/**
	 * 资源在外部系统的id
	 */
	private String eid;
	
	/**
	 * 资源在外部系统的名称，如果没有配置，默认为eid
	 */
	private String ename;
	
	/**
	 * 级别，和内部的rank一样，0是终端，大于1是调度台
	 */
	private Integer rank;
	
	/**
	 * 保留字段，JSON格式，今后可以做些扩展
	 */
	private String reserve;
	
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	/**
	 * 外部系统在警迅系统映射公司ID
	 */
	private Integer corpId;
	
	/**
	 * 外部映射资源在警迅的单位号
	 */
	private String unitId;
	
	/**
	 * 外部映射资源在警迅的部门号
	 */
	private String departmentId;
	
	/**
	 * 
	 */
	private String originalName;
	
	/**
	 * 外部映射资源在警迅的单位主键ID
	 */
	private Long unitFkId;
	
	/**
	 * 外部映射资源在警迅的部门主键ID
	 */
	private Long departmentFkId;
	
	/**
	 * 状态，1：active, 2-delete
	 */
	private Integer state;
	
	/**
	 * 被可见级别，1所有下级部门可见，2下级部门第一层管辖可见，3直管可见，4全局可见
	 */
	private Integer visibleLevel;
	
	/**
	 * 优先级
	 */
	private Integer priority;
	
	/**
	 * 账号类型：1-终端账号，2-调度台账号，3-群组账号
	 */
	private Integer accountType;
	
	
	
}