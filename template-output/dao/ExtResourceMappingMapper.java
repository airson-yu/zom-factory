package com.zom.common.dao.mapper;

import com.zom.common.dao.po.ExtResourceMapping;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: ExtResourceMapping
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 外部系统资源映射表
 * @time 2021-02-01 12:10:25
 */
public interface ExtResourceMappingMapper {

    // select methods

    ExtResourceMapping load(long id);

    ExtResourceMapping selectByPrimaryKey(long id);

    List<ExtResourceMapping> selectListByCondition(Map<String, Object> map);

    ExtResourceMapping selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(ExtResourceMapping extResourceMapping);

    int updateByPrimaryKeySelective(ExtResourceMapping extResourceMapping);

    // insert methods

    int insert(ExtResourceMapping extResourceMapping);

    int insertSelective(ExtResourceMapping extResourceMapping);

    int insertBatch(List<ExtResourceMapping> extResourceMapping);

    // delete methods

    int deleteByPrimaryKey(long id);

}