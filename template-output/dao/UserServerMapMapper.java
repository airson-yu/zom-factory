package com.zom.common.dao.mapper;

import com.zom.common.dao.po.UserServerMap;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: UserServerMap
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 用户账号与服务器关联表
 * @time 2021-03-10 15:09:03
 */
public interface UserServerMapMapper {

    // select methods

    UserServerMap load(long id);

    UserServerMap selectByPrimaryKey(long id);

    List<UserServerMap> selectListByCondition(Map<String, Object> map);

    UserServerMap selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(UserServerMap userServerMap);

    int updateByPrimaryKeySelective(UserServerMap userServerMap);

    // insert methods

    int insert(UserServerMap userServerMap);

    int insertSelective(UserServerMap userServerMap);

    int insertBatch(List<UserServerMap> userServerMap);

    // delete methods

    int deleteByPrimaryKey(long id);

}