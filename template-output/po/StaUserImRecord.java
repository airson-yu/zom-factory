package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: StaUserImRecord
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 即时消息记录表
 * @time 2020-09-21 10:28:47
 */
@Data
public class StaUserImRecord {

	/**
	 * 表项主键
	 */
	private Long id;
	
	/**
	 * 组织ID
	 */
	private Integer corpid;
	
	/**
	 * 人员ID
	 */
	private Long uid;
	
	/**
	 * 人员姓名
	 */
	private String name;
	
	/**
	 * 业务产生时间：年月(202002)
	 */
	private String timeYm;
	
	/**
	 * 业务产生时间：年月日(20200208)
	 */
	private Integer timeYmd;
	
	/**
	 * 业务产生时间：年月日时(2020020809)
	 */
	private Integer timeYmdh;
	
	/**
	 * 部门ID
	 */
	private String deptUniqueId;
	
	/**
	 * 部门名称
	 */
	private String deptName;
	
	/**
	 * 个人：对方uid，群组：通话组ID
	 */
	private Long targetId;
	
	/**
	 * 个人：对方的名称，群组：通话组名称
	 */
	private String targetName;
	
	/**
	 * 通话类型：1个人，2群组
	 */
	private Integer type;
	
	/**
	 * 消息类型：1文字，2文件，3离线语音
	 */
	private Integer imType;
	
	/**
	 * 开始时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	
	/**
	 * 结束时间 （保留字段）
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	
	/**
	 * 会话ID（非必填）
	 */
	private String session;
	
	/**
	 * 结束标识：1已结束，2未结束（保留字段）
	 */
	private Integer endFlag;
	
	/**
	 * 统计状态：1需要统计，2不需统计（隐藏数据）
	 */
	private Integer state;
	
	
	
}