package com.zom.common.dao.mapper;

import com.zom.common.dao.po.StaFrontData;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: StaFrontData
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 前端数据存储表
 * @time 2021-01-04 12:10:36
 */
public interface StaFrontDataMapper {

    // select methods

    StaFrontData load(long id);

    StaFrontData selectByPrimaryKey(long id);

    List<StaFrontData> selectListByCondition(Map<String, Object> map);

    StaFrontData selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(StaFrontData staFrontData);

    int updateByPrimaryKeySelective(StaFrontData staFrontData);

    // insert methods

    int insert(StaFrontData staFrontData);

    int insertSelective(StaFrontData staFrontData);

    int insertBatch(List<StaFrontData> staFrontData);

    // delete methods

    int deleteByPrimaryKey(long id);

}