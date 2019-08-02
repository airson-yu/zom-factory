package com.zom.common.dao.po;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: RolePlan
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 角色方案
 * @time 2019-08-02 16:19:22
 */
@Data
public class RolePlan {

    /**
     *
     */
    private Long id;

    /**
     * 方案唯一标识ID(数字)
     */
    private Integer uniqueId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 状态：1启用，0停用，2删除
     */
    private Integer state;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 组织ID
     */
    private Integer corpId;


}