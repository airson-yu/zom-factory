package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: ConsoleBindPhoneMap
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 调度台与短信号码绑定表
 * @time 2020-11-23 16:26:00
 */
@Data
public class ConsoleBindPhoneMap {

	/**
	 * 主键ID
	 */
	private Long id;
	
	/**
	 * 调度台ID
	 */
	private Long uid;
	
	/**
	 * 组织ID
	 */
	private Integer corpId;
	
	/**
	 * 绑定的电话号码，从rtv_phone中选取，rtv_phone变化时需同步更新
	 */
	private String phone;
	
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	
	
}