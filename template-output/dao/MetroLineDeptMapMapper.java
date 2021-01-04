package com.zom.common.dao.mapper;

import com.zom.common.dao.po.MetroLineDeptMap;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: MetroLineDeptMap
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 地铁线路与部门关联表
 * @time 2021-01-04 12:10:36
 */
public interface MetroLineDeptMapMapper {

    // select methods

    MetroLineDeptMap load(long id);

    MetroLineDeptMap selectByPrimaryKey(long id);

    List<MetroLineDeptMap> selectListByCondition(Map<String, Object> map);

    MetroLineDeptMap selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(MetroLineDeptMap metroLineDeptMap);

    int updateByPrimaryKeySelective(MetroLineDeptMap metroLineDeptMap);

    // insert methods

    int insert(MetroLineDeptMap metroLineDeptMap);

    int insertSelective(MetroLineDeptMap metroLineDeptMap);

    int insertBatch(List<MetroLineDeptMap> metroLineDeptMap);

    // delete methods

    int deleteByPrimaryKey(long id);

}