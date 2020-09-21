package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: StaDayDept
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 部门按天汇总统计表
 * @time 2020-09-21 10:28:47
 */
@Data
public class StaDayDept {

	/**
	 * 表项主键
	 */
	private Long id;
	
	/**
	 * 
	 */
	private String uniqueId;
	
	/**
	 * 组织ID
	 */
	private Integer corpid;
	
	/**
	 * 业务产生时间：年月日(20200208)
	 */
	private Integer timeYmd;
	
	/**
	 * 部门ID
	 */
	private String deptUniqueId;
	
	/**
	 * 部门名称
	 */
	private String deptName;
	
	/**
	 * 在线时长,单位：秒
	 */
	private Long onlineDuration;
	
	/**
	 * 登录时间（保留字段）
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date logonTime;
	
	/**
	 * 语音通话次数（单呼+组呼）
	 */
	private Integer talkCount;
	
	/**
	 * 单呼通话次数
	 */
	private Integer individualTalkCount;
	
	/**
	 * 组呼通话次数
	 */
	private Integer groupTalkCount;
	
	/**
	 * 语音通话时长，单位：秒
	 */
	private Integer talkDuration;
	
	/**
	 * 单呼通话时长
	 */
	private Integer individualTalkDuration;
	
	/**
	 * 组呼通话时长
	 */
	private Integer groupTalkDuration;
	
	/**
	 * 所有视频业务次数
	 */
	private Integer videoAllCount;
	
	/**
	 * 视频通话次数
	 */
	private Integer videoCallCount;
	
	/**
	 * 视频上传次数
	 */
	private Integer videoUploadCount;
	
	/**
	 * 发起视频点名次数
	 */
	private Integer videoRollcallCount;
	
	/**
	 * 发起视频会商次数
	 */
	private Integer videoConfCount;
	
	/**
	 * 视频通话时长
	 */
	private Integer videoDuration;
	
	/**
	 * 一键报警次数
	 */
	private Integer sosCount;
	
	/**
	 * 回传照片次数
	 */
	private Integer photoUploadCount;
	
	/**
	 * 即时消息次数，包括IM 中的文件，包括下面的个人和群组即时消息次数
	 */
	private Integer imCount;
	
	/**
	 * 个人即时消息次数
	 */
	private Integer individualImCount;
	
	/**
	 * 群组即时消息次数
	 */
	private Integer groupImCount;
	
	/**
	 * 在岗时长,单位：秒
	 */
	private Integer onpostDuration;
	
	/**
	 * 里程数,单位：米
	 */
	private Long mileage;
	
	/**
	 * 临时组总数
	 */
	private Integer tmpGroupCount;
	
	/**
	 * 临时组归档数
	 */
	private Integer tmpGroupFileCount;
	
	/**
	 * 统计状态：1需要统计，2不需统计（隐藏数据）
	 */
	private Integer state;
	
	/**
	 * 最后更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	
	
}