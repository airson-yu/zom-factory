package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: StaOriginalLog
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 原始业务日志记录表（定期清理）
 * @time 2020-09-21 10:28:47
 */
@Data
public class StaOriginalLog {

	/**
	 * 表项主键
	 */
	private Long id;
	
	/**
	 * 日志内容（JSON格式）
	 */
	private String content;
	
	
	
}