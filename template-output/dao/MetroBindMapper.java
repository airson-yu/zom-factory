package com.zom.common.dao.mapper;

import com.zom.common.dao.po.MetroBind;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: MetroBind
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 
 * @time 2020-04-24 09:41:23
 */
public interface MetroBindMapper {

    // select methods

    MetroBind load(long id);

    MetroBind selectByPrimaryKey(long id);

    List<MetroBind> selectListByCondition(Map<String, Object> map);

    MetroBind selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(MetroBind metroBind);

    int updateByPrimaryKeySelective(MetroBind metroBind);

    // insert methods

    int insert(MetroBind metroBind);

    int insertSelective(MetroBind metroBind);

    int insertBatch(List<MetroBind> metroBind);

    // delete methods

    int deleteByPrimaryKey(long id);

}