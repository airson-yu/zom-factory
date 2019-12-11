package com.zom.common.dao.mapper;

import com.zom.common.dao.po.AppBlackList;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: AppBlackList
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark APP黑名单记录表
 * @time 2019-12-11 15:42:59
 */
public interface AppBlackListMapper {

    // select methods

    AppBlackList load(long id);

    AppBlackList selectByPrimaryKey(long id);

    List<AppBlackList> selectListByCondition(Map<String, Object> map);

    AppBlackList selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(AppBlackList appBlackList);

    int updateByPrimaryKeySelective(AppBlackList appBlackList);

    // insert methods

    int insert(AppBlackList appBlackList);

    int insertSelective(AppBlackList appBlackList);

    int insertBatch(List<AppBlackList> appBlackList);

    // delete methods

    int deleteByPrimaryKey(long id);

}