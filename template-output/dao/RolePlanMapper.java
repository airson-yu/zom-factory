package com.zom.common.dao.mapper;

import com.zom.common.dao.po.RolePlan;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: RolePlan
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 角色方案
 * @time 2019-08-02 16:19:22
 */
public interface RolePlanMapper {

    // select methods

    RolePlan load(long id);

    RolePlan selectByPrimaryKey(long id);

    List<RolePlan> selectListByCondition(Map<String, Object> map);

    RolePlan selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(RolePlan rolePlan);

    int updateByPrimaryKeySelective(RolePlan rolePlan);

    // insert methods

    int insert(RolePlan rolePlan);

    int insertSelective(RolePlan rolePlan);

    int insertBatch(List<RolePlan> rolePlan);

    // delete methods

    int deleteByPrimaryKey(long id);

}