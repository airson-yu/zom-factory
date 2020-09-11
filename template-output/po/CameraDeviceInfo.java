package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: CameraDeviceInfo
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 摄像机设备信息表
 * @time 2020-09-11 18:12:02
 */
@Data
public class CameraDeviceInfo {

	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 设备ID
	 */
	private String deviceid;
	
	/**
	 * 摄像机通道id
	 */
	private String cid;
	
	/**
	 * 华为子设备信息的设备ID
	 */
	private String code;
	
	/**
	 * 设备名称
	 */
	private String name;
	
	/**
	 * 设备所属组名称
	 */
	private String deviceGroupCode;
	
	/**
	 * 父设备编码
	 */
	private String parentCode;
	
	/**
	 * 域的编号 --域是国标28181中的称谓
	 */
	private String domaincode;
	
	/**
	 * 主设备型号
	 */
	private String deviceModelType;
	
	/**
	 * 主设备互联编码
	 */
	private String parentConnectCode;
	
	/**
	 * 设备提供商类型
	 */
	private String vendorType;
	
	/**
	 * 主设备类型，1-IPC ，2-DVS，3-DVR ，4-eNVR
	 */
	private Integer deviceFormType;
	
	/**
	 * 摄像机类型：0 固定枪机 1 有云台枪机 2 球机• 3 半球-固定摄像机 4:筒机
	 */
	private Integer cameraType;
	
	/**
	 * 摄像机安装位置描述
	 */
	private String cameraLocation;
	
	/**
	 * 设备状态，0-离线 ，1-在线，2-休眠
	 */
	private Integer status;
	
	/**
	 * 网络类型，0-有线 ，1-无线
	 */
	private Integer netType;
	
	/**
	 * 是否支持智能分析，0-不支持 ，1-支持
	 */
	private Integer isSupportIntelligent;
	
	/**
	 * 设备所属NVR编码
	 */
	private String nvrCode;
	
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
	 * 设备创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date deviceCreateTime;
	
	/**
	 * 前端IP
	 */
	private String deviceIp;
	
	/**
	 * 经度
	 */
	private Double longitude;
	
	/**
	 * 纬度
	 */
	private Double latitude;
	
	/**
	 * 高度
	 */
	private Double height;
	
	/**
	 * 
	 */
	private Integer corpId;
	
	/**
	 * 设备备注
	 */
	private String remark;
	
	/**
	 * 保留字段，今后可以放不用来查询的一些扩展信息
	 */
	private String reserve;
	
	
	
}