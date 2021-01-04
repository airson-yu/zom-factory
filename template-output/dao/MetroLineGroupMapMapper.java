package com.zom.common.dao.mapper;

import com.zom.common.dao.po.MetroLineGroupMap;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: MetroLineGroupMap
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 地铁线路与群组关联表
 * @time 2021-01-04 12:10:36
 */
public interface MetroLineGroupMapMapper {

    // select methods

    MetroLineGroupMap load(long id);

    MetroLineGroupMap selectByPrimaryKey(long id);

    List<MetroLineGroupMap> selectListByCondition(Map<String, Object> map);

    MetroLineGroupMap selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(MetroLineGroupMap metroLineGroupMap);

    int updateByPrimaryKeySelective(MetroLineGroupMap metroLineGroupMap);

    // insert methods

    int insert(MetroLineGroupMap metroLineGroupMap);

    int insertSelective(MetroLineGroupMap metroLineGroupMap);

    int insertBatch(List<MetroLineGroupMap> metroLineGroupMap);

    // delete methods

    int deleteByPrimaryKey(long id);

}