package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: IccidChangeRecord
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 用户的ICCID变更记录表
 * @time 2020-07-16 11:14:40
 */
@Data
public class IccidChangeRecord {

	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 用户ID
	 */
	private Long uid;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 旧iccid
	 */
	private String oldIccid;
	
	/**
	 * 新iccid
	 */
	private String newIccid;
	
	/**
	 * IMEI
	 */
	private String imei;
	
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	/**
	 * 
	 */
	private Integer corpId;
	
	
	
}