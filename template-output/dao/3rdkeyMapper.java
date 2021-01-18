package com.zom.common.dao.mapper;

import com.zom.common.dao.po.3rdkey;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: 3rdkey
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 三方表
 * @time 2021-01-18 17:04:24
 */
public interface 3rdkeyMapper {

    // select methods

    3rdkey load(long id);

    3rdkey selectByPrimaryKey(long id);

    List<3rdkey> selectListByCondition(Map<String, Object> map);

    3rdkey selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(3rdkey 3rdkey);

    int updateByPrimaryKeySelective(3rdkey 3rdkey);

    // insert methods

    int insert(3rdkey 3rdkey);

    int insertSelective(3rdkey 3rdkey);

    int insertBatch(List<3rdkey> 3rdkey);

    // delete methods

    int deleteByPrimaryKey(long id);

}