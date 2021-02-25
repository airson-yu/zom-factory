package com.zom.common.dao.mapper;

import com.zom.common.dao.po.AuthBind;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: AuthBind
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 服务器绑定的授权码表
 * @time 2021-02-25 17:54:30
 */
public interface AuthBindMapper {

    // select methods

    AuthBind load(long id);

    AuthBind selectByPrimaryKey(long id);

    List<AuthBind> selectListByCondition(Map<String, Object> map);

    AuthBind selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(AuthBind authBind);

    int updateByPrimaryKeySelective(AuthBind authBind);

    // insert methods

    int insert(AuthBind authBind);

    int insertSelective(AuthBind authBind);

    int insertBatch(List<AuthBind> authBind);

    // delete methods

    int deleteByPrimaryKey(long id);

}