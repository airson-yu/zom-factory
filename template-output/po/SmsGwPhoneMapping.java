package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: SmsGwPhoneMapping
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 短信网关状态表
 * @time 2020-11-23 10:50:01
 */
@Data
public class SmsGwPhoneMapping {

	/**
	 * 主键ID
	 */
	private Long id;
	
	/**
	 * 执行上报的UID
	 */
	private Long uid;
	
	/**
	 * 组织ID
	 */
	private Integer corpId;
	
	/**
	 * 端口号
	 */
	private Integer port;
	
	/**
	 * 端口对应的ICCID，通过此字段关联设备类型为sms_gw的user
	 */
	private String iccid;
	
	/**
	 * 端口对应的电话号码
	 */
	private String phone;
	
	/**
	 * 工作状态。1 正常， 2-不工作， 3-未插卡
	 */
	private Integer state;
	
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	
	
}