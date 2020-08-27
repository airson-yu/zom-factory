package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: AuthRecord
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 
 * @time 2020-08-27 17:48:28
 */
@Data
public class AuthRecord {

	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 授权码
	 */
	private String authid;
	
	/**
	 * 用户ID
	 */
	private Long uid;
	
	/**
	 * 公司代号。警翼：737A6A79（szjy 的ASCII）
	 */
	private String corpCode;
	
	/**
	 * 硬件识别符类型
	 */
	private String hwidtype;
	
	/**
	 * 具体的硬件识别号
	 */
	private String hwid;
	
	/**
	 * 设备类型。如5v,G6,G7
	 */
	private String devicetype;
	
	/**
	 * OS类型，目前只支持android
	 */
	private String ostype;
	
	/**
	 * 应用类型，目前只支持djbmssdk
	 */
	private String apptype;
	
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatetime;
	
	
	
}