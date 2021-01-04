package com.zom.common.dao.mapper;

import com.zom.common.dao.po.StaDayGroup;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: StaDayGroup
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 部门按天汇总统计表
 * @time 2021-01-04 12:10:36
 */
public interface StaDayGroupMapper {

    // select methods

    StaDayGroup load(long id);

    StaDayGroup selectByPrimaryKey(long id);

    List<StaDayGroup> selectListByCondition(Map<String, Object> map);

    StaDayGroup selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(StaDayGroup staDayGroup);

    int updateByPrimaryKeySelective(StaDayGroup staDayGroup);

    // insert methods

    int insert(StaDayGroup staDayGroup);

    int insertSelective(StaDayGroup staDayGroup);

    int insertBatch(List<StaDayGroup> staDayGroup);

    // delete methods

    int deleteByPrimaryKey(long id);

}