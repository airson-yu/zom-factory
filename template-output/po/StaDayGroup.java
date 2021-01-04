package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: StaDayGroup
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 部门按天汇总统计表
 * @time 2021-01-04 12:10:36
 */
@Data
public class StaDayGroup {

	/**
	 * 表项主键
	 */
	private Long id;
	
	/**
	 * 唯一ID:ymd+group_id
	 */
	private String uniqueId;
	
	/**
	 * 组织ID
	 */
	private Integer corpid;
	
	/**
	 * 通用关联ID，如：地铁线路
	 */
	private String linkId;
	
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
	 * 群组ID
	 */
	private Long groupId;
	
	/**
	 * 群组名称
	 */
	private String groupName;
	
	/**
	 * 组呼通话次数
	 */
	private Integer talkCount;
	
	/**
	 * 组呼通话时长，单位：秒
	 */
	private Integer talkDuration;
	
	/**
	 * 所有视频业务次数，预留
	 */
	private Integer videoCount;
	
	/**
	 * 视频通话时长，预留
	 */
	private Integer videoDuration;
	
	/**
	 * 群组即时消息次数
	 */
	private Integer imCount;
	
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