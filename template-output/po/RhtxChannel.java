package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: RhtxChannel
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 融合通信频道映射表
 * @time 2020-08-13 14:40:03
 */
@Data
public class RhtxChannel {

	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 关联的融合通信配置表ID
	 */
	private Long rhtxId;
	
	/**
	 * 对讲组名称
	 */
	private String name;
	
	/**
	 * 对讲组ID
	 */
	private String grpId;
	
	/**
	 * 800M,350M,其他
	 */
	private String grpType;
	
	/**
	 * 组所属部门
	 */
	private Long departmentFkId;
	
	/**
	 * 被分配使用的网关ID
	 */
	private Long gwUid;
	
	/**
	 * 互联网关类型
	 */
	private String gwType;
	
	/**
	 * 映射状态
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