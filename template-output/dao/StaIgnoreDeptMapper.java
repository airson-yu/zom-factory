package com.zom.common.dao.mapper;

import com.zom.common.dao.po.StaIgnoreDept;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: StaIgnoreDept
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 屏蔽部门表
 * @time 2020-11-05 10:49:50
 */
public interface StaIgnoreDeptMapper {

    // select methods

    StaIgnoreDept load(long id);

    StaIgnoreDept selectByPrimaryKey(long id);

    List<StaIgnoreDept> selectListByCondition(Map<String, Object> map);

    StaIgnoreDept selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(StaIgnoreDept staIgnoreDept);

    int updateByPrimaryKeySelective(StaIgnoreDept staIgnoreDept);

    // insert methods

    int insert(StaIgnoreDept staIgnoreDept);

    int insertSelective(StaIgnoreDept staIgnoreDept);

    int insertBatch(List<StaIgnoreDept> staIgnoreDept);

    // delete methods

    int deleteByPrimaryKey(long id);

}