package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: MetroBind
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 
 * @time 2020-04-24 09:41:23
 */
@Data
public class MetroBind {

	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 序号
	 */
	private String serial;
	
	/**
	 * 车次
	 */
	private String lineNum;
	
	/**
	 * 列车编号
	 */
	private String metroNum;
	
	/**
	 * 用户ID
	 */
	private Long userId;
	
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	/**
	 * 
	 */
	private Integer corpId;
	
	
	
}