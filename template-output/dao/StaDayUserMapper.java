package com.zom.common.dao.mapper;

import com.zom.common.dao.po.StaDayUser;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: StaDayUser
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 用户按天汇总统计表
 * @time 2020-02-27 09:57:44
 */
public interface StaDayUserMapper {

    // select methods

    StaDayUser load(long id);

    StaDayUser selectByPrimaryKey(long id);

    List<StaDayUser> selectListByCondition(Map<String, Object> map);

    StaDayUser selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(StaDayUser staDayUser);

    int updateByPrimaryKeySelective(StaDayUser staDayUser);

    // insert methods

    int insert(StaDayUser staDayUser);

    int insertSelective(StaDayUser staDayUser);

    int insertBatch(List<StaDayUser> staDayUser);

    // delete methods

    int deleteByPrimaryKey(long id);

}