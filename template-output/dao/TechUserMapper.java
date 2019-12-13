package com.zom.common.dao.mapper;

import com.zom.common.dao.po.TechUser;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: TechUser
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 用户表
 * @time 2019-12-13 17:56:27
 */
public interface TechUserMapper {

    // select methods

    TechUser load(long id);

    TechUser selectByPrimaryKey(long id);

    List<TechUser> selectListByCondition(Map<String, Object> map);

    TechUser selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(TechUser techUser);

    int updateByPrimaryKeySelective(TechUser techUser);

    // insert methods

    int insert(TechUser techUser);

    int insertSelective(TechUser techUser);

    int insertBatch(List<TechUser> techUser);

    // delete methods

    int deleteByPrimaryKey(long id);

}