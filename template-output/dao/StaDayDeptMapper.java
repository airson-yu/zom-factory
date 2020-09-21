package com.zom.common.dao.mapper;

import com.zom.common.dao.po.StaDayDept;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: StaDayDept
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 部门按天汇总统计表
 * @time 2020-09-21 10:28:48
 */
public interface StaDayDeptMapper {

    // select methods

    StaDayDept load(long id);

    StaDayDept selectByPrimaryKey(long id);

    List<StaDayDept> selectListByCondition(Map<String, Object> map);

    StaDayDept selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(StaDayDept staDayDept);

    int updateByPrimaryKeySelective(StaDayDept staDayDept);

    // insert methods

    int insert(StaDayDept staDayDept);

    int insertSelective(StaDayDept staDayDept);

    int insertBatch(List<StaDayDept> staDayDept);

    // delete methods

    int deleteByPrimaryKey(long id);

}