package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: Corp
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 
 * @time 2020-09-28 15:33:53
 */
@Data
public class Corp {

	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 公司代号。警翼：737A6A79（szjy 的ASCII）
	 */
	private String code;
	
	/**
	 * 登录名
	 */
	private String logonName;
	
	/**
	 * 名称
	 */
	private String corpName;
	
	/**
	 * 密码
	 */
	private String corpPassword;
	
	/**
	 * 权限等级:0普通，1普通管理员，2超级管理员
	 */
	private Integer level;
	
	/**
	 * 注册时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date registerDate;
	
	/**
	 * 过期时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date expireDate;
	
	/**
	 * 状态:0停用，1启用，2删除
	 */
	private Integer state;
	
	/**
	 * 
	 */
	private String email;
	
	/**
	 * 
	 */
	private String phone;
	
	/**
	 * 上次登录时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLogonDate;
	
	/**
	 * 上次登录IP
	 */
	private String lastLogonIp;
	
	/**
	 * 账号过期后的行为：1禁止管理台和终端登录，2组织设置为停用，禁止管理台和终端登录
	 */
	private Integer expireOperation;
	
	/**
	 * 账号过期的判断方式：1与证书一致，2指定组织过期时间
	 */
	private Integer expireWay;
	
	/**
	 * 最大用户数
	 */
	private Integer maxUser;
	
	/**
	 * 允许的设备类型，为空则允许全部类型
	 */
	private String deviceTypes;
	
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	
	
}