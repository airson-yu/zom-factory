package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: UserServerMap
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 用户账号与服务器关联表
 * @time 2021-03-10 15:09:03
 */
@Data
public class UserServerMap {

	/**
	 * 主键ID
	 */
	private Long id;
	
	/**
	 * 用户账号，可为phone,cerId,code,nfc,uid,iccid,imei等，登录类型需要匹配
	 */
	private String account;
	
	/**
	 * 关联的服务器IP
	 */
	private String serverIp;
	
	/**
	 * 关联的服务器端口
	 */
	private String serverPort;
	
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	
	
}