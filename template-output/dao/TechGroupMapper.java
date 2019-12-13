package com.zom.common.dao.mapper;

import com.zom.common.dao.po.TechGroup;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: TechGroup
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark group table
 * @time 2019-12-13 17:56:27
 */
public interface TechGroupMapper {

    // select methods

    TechGroup load(long id);

    TechGroup selectByPrimaryKey(long id);

    List<TechGroup> selectListByCondition(Map<String, Object> map);

    TechGroup selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(TechGroup techGroup);

    int updateByPrimaryKeySelective(TechGroup techGroup);

    // insert methods

    int insert(TechGroup techGroup);

    int insertSelective(TechGroup techGroup);

    int insertBatch(List<TechGroup> techGroup);

    // delete methods

    int deleteByPrimaryKey(long id);

}