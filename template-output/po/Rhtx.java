package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: Rhtx
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 融合通信配置表
 * @time 2020-08-13 14:40:03
 */
@Data
public class Rhtx {

	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 融合通信名称
	 */
	private String name;
	
	/**
	 * 警迅对讲组ID
	 */
	private Long itrunkGrpId;
	
	/**
	 * 组所属部门
	 */
	private Long departmentFkId;
	
	/**
	 * 状态：0停用，1启用，2删除
	 */
	private Integer state;
	
	/**
	 * 公司ID
	 */
	private Integer corpId;
	
	/**
	 * 修改时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastUpdateTime;
	
	
	
}