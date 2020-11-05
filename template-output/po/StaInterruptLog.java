package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: StaInterruptLog
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 手动停止业务记录表
 * @time 2020-11-05 10:49:50
 */
@Data
public class StaInterruptLog {

	/**
	 * 日志ID
	 */
	private Long id;
	
	/**
	 * 
	 */
	private Long subId;
	
	/**
	 * 用户ID
	 */
	private Long uid;
	
	/**
	 * 用户名称
	 */
	private String uname;
	
	/**
	 * 部门ID
	 */
	private String deptId;
	
	/**
	 * 部门名称
	 */
	private String deptName;
	
	/**
	 * 业务ID
	 */
	private Integer busiId;
	
	/**
	 * 业务类型（1在线， 2语音，3，视频）
	 */
	private Integer busiType;
	
	/**
	 * 细分类型
语音：1个人通话 2群组通话，
视频：1视频通话，2视频回传，3视频点名，4视频会商
细分类型》语音：1个人通话 2群组通话，视频：1视频通话，2视频回传，3视频点名，4视频会商 
	 */
	private Integer imType;
	
	/**
	 * 1：未结束  2：结束   
	 */
	private Integer flag;
	
	/**
	 * 
	 */
	private Integer real;
	
	/**
	 * 
	 */
	private Long targetId;
	
	/**
	 * 
	 */
	private String targetName;
	
	/**
	 * 业务开始时间
	 */
	private String startTime;
	
	/**
	 * 业务结束时间
	 */
	private String endTime;
	
	/**
	 * 操作人ID
	 */
	private Long operUid;
	
	/**
	 * 操作人
	 */
	private String operUsername;
	
	/**
	 * 中断类型
	 */
	private Integer interruptType;
	
	/**
	 * 会话ID
	 */
	private String session;
	
	/**
	 * 
	 */
	private String createTime;
	
	/**
	 * 扩展字段1  备用
	 */
	private String exp1;
	
	/**
	 * 扩展字段2  备用
	 */
	private String exp2;
	
	/**
	 * 扩展字段3  备用
	 */
	private String exp3;
	
	
	
}