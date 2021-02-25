package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: AuthBind
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 服务器绑定的授权码表
 * @time 2021-02-25 17:54:30
 */
@Data
public class AuthBind {

	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 授权码
	 */
	private String authid;
	
	/**
	 * 服务器绑定的授权码
	 */
	private String serverAuthid;
	
	/**
	 * 公司代号。警翼：737A6A79（szjy 的ASCII）
	 */
	private String corpCode;
	
	/**
	 * 在线用户1，最多允许同时两个终端登录，超量时才检查
	 */
	private Long onlineUid1;
	
	/**
	 * 在线用户2，最多允许同时两个终端登录，超量时才检查
	 */
	private Long onlineUid2;
	
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
	 * 虚拟授权硬件码的uuid
	 */
	private String uuid;
	
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatetime;
	
	
	
}