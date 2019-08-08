package com.zom.common.dao.mapper;

import com.zom.common.dao.po.RolePlanRole;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: RolePlanRole
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 角色
 * @time 2019-08-07 15:16:28
 */
public interface RolePlanRoleMapper {

    // select methods

    RolePlanRole load(long id);

    RolePlanRole selectByPrimaryKey(long id);

    List<RolePlanRole> selectListByCondition(Map<String, Object> map);

    RolePlanRole selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(RolePlanRole rolePlanRole);

    int updateByPrimaryKeySelective(RolePlanRole rolePlanRole);

    // insert methods

    int insert(RolePlanRole rolePlanRole);

    int insertSelective(RolePlanRole rolePlanRole);

    int insertBatch(List<RolePlanRole> rolePlanRole);

    // delete methods

    int deleteByPrimaryKey(long id);

}