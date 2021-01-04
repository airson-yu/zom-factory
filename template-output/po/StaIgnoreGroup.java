package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: StaIgnoreGroup
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 屏蔽群组表
 * @time 2021-01-04 12:10:36
 */
@Data
public class StaIgnoreGroup {

	/**
	 * 表主键ID
	 */
	private Long id;
	
	/**
	 * 调度台ID
	 */
	private Long uid;
	
	/**
	 * 群组ID
	 */
	private Long groupId;
	
	/**
	 * 状态：0 屏蔽，1 启用
	 */
	private Integer state;
	
	
	
}