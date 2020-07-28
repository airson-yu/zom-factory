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
 * @time 2020-07-28 17:20:12
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
	 * 车底号
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
	
	/**
	 * 列车的出库时间，在此时间前一个小时进行列车绑定
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	
	/**
	 * 否已经解析绑定的标识：0未解析，1已解析
	 */
	private Integer bindFlag;
	
	
	
}