package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: ExternalSystemCfg
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 系统互联外系统基础配置表
 * @time 2021-02-02 18:06:17
 */
@Data
public class ExternalSystemCfg {

	/**
	 * 主键ID
	 */
	private Long id;
	
	/**
	 * 外系统类型，目前仅支持XHZH
	 */
	private String etype;
	
	/**
	 * 外系统类型名称
	 */
	private String ename;
	
	/**
	 * 外系统的配置，使用内部的json格式定义，包括信令，语音，视频接入地址
	 */
	private String cfg;
	
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	
	
}