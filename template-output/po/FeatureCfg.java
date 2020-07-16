package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: FeatureCfg
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 功能开关配置表
 * @time 2020-07-16 11:14:40
 */
@Data
public class FeatureCfg {

	/**
	 * 自增整型ID
	 */
	private Long id;
	
	/**
	 * 平台
	 */
	private String platform;
	
	/**
	 * 组织ID（NULL为通用）
	 */
	private Integer corpId;
	
	/**
	 * 类型
	 */
	private String ftype;
	
	/**
	 * 名称
	 */
	private String fname;
	
	/**
	 * 值
	 */
	private String fvalue;
	
	/**
	 * 代码(固定的ID标识)
	 */
	private String uniqueCode;
	
	/**
	 * 父代码
	 */
	private String parentCode;
	
	/**
	 * 状态：1启用，2删除
	 */
	private Integer state;
	
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	
	
}