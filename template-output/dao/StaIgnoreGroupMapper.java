package com.zom.common.dao.mapper;

import com.zom.common.dao.po.StaIgnoreGroup;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: StaIgnoreGroup
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 屏蔽群组表
 * @time 2021-01-04 12:10:36
 */
public interface StaIgnoreGroupMapper {

    // select methods

    StaIgnoreGroup load(long id);

    StaIgnoreGroup selectByPrimaryKey(long id);

    List<StaIgnoreGroup> selectListByCondition(Map<String, Object> map);

    StaIgnoreGroup selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(StaIgnoreGroup staIgnoreGroup);

    int updateByPrimaryKeySelective(StaIgnoreGroup staIgnoreGroup);

    // insert methods

    int insert(StaIgnoreGroup staIgnoreGroup);

    int insertSelective(StaIgnoreGroup staIgnoreGroup);

    int insertBatch(List<StaIgnoreGroup> staIgnoreGroup);

    // delete methods

    int deleteByPrimaryKey(long id);

}