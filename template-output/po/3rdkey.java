package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: 3rdkey
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 三方表
 * @time 2021-01-20 11:39:17
 */
@Data
public class 3rdkey {

	/**
	 * 编号
	 */
	private Integer id;
	
	/**
	 * API Key
	 */
	private String apiKey;
	
	/**
	 * SECURITY KEY
	 */
	private String securityKey;
	
	/**
	 * 申请API的公司ID
	 */
	private Integer corpId;
	
	/**
	 * peer server IP,用来做通知，不是必填
	 */
	private String home;
	
	/**
	 * 扩展JSON字段
	 */
	private String extjson;
	
	/**
	 * 三方平台名称
	 */
	private String platformName;
	
	/**
	 * 三方平台识别码
	 */
	private String platformCode;
	
	/**
	 * 过期时间，为空则永不过期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date expireTime;
	
	/**
	 * 时间戳有效分钟数，为0则始终有效（不安全）
	 */
	private Integer tsValidMinutes;
	
	/**
	 * 状态，0停用，1启动，2删除
	 */
	private Integer state;
	
	/**
	 * 权限级别，1系统级，2组织级，3部门级，4用户级
	 */
	private Integer authLevel;
	
	/**
	 * 允许访问的接口，为空则为所有接口可访问
	 */
	private String allowApis;
	
	/**
	 * 
	 */
	private String remark;
	
	/**
	 * 
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	
	
}