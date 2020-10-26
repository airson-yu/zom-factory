package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: CameraGroup
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 摄像机组节点表
 * @time 2020-10-26 11:21:03
 */
@Data
public class CameraGroup {

	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 摄像机组的编号
	 */
	private String groupcode;
	
	/**
	 * 摄像机组的名称
	 */
	private String groupname;
	
	/**
	 * 父节点摄像机组的名称
	 */
	private String parentgroupcode;
	
	/**
	 * 域的编号 --域是国标28181中的称谓
	 */
	private String domaincode;
	
	/**
	 * 是否是外域，0-- 本域， 1--外域
	 */
	private Integer isExDomain;
	
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatetime;
	
	/**
	 * 
	 */
	private Integer corpId;
	
	/**
	 * 
	 */
	private Integer state;
	
	/**
	 * 下级链节点摄像头总数
	 */
	private Integer camCount;
	
	/**
	 * 下级链节点摄像头在线总数
	 */
	private Integer camOnlineCount;
	
	/**
	 * 上级链节点全路径
	 */
	private String fullpath;
	
	/**
	 * 序号，默认100000， 序号越小，在调度台的排序越靠前
	 */
	private Integer ordernum;
	
	
	
}