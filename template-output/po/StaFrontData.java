package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: StaFrontData
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 前端数据存储表
 * @time 2021-01-04 12:10:36
 */
@Data
public class StaFrontData {

	/**
	 * 表项主键
	 */
	private Long id;
	
	/**
	 * 组织ID
	 */
	private Integer corpId;
	
	/**
	 * 调度台ID
	 */
	private Long uid;
	
	/**
	 * 数据唯一编号（同一组织下保持唯一）
	 */
	private String uniqueKey;
	
	/**
	 * 数据内容
	 */
	private String content;
	
	/**
	 * 备注或描述
	 */
	private String remark;
	
	/**
	 * 数据的类型，取值如下，默认为1：
1：最重要的配置，在登录接口中会返回此类数据。
2-9：由服务器保留，决定以后如何使用。
10-100：取值由前端来决定如何使用，对服务器是透明的。
	 */
	private Integer type;
	
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	
	
}