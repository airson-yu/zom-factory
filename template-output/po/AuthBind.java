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
 * @time 2021-02-24 18:18:02
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
	 * 用户ID
	 */
	private Long uid;
	
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatetime;
	
	
	
}