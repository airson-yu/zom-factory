package com.zom.common.dao.mapper;

import com.zom.common.dao.po.AppWhiteList;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: AppWhiteList
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark APP白名单记录表
 * @time 2019-12-11 15:42:59
 */
public interface AppWhiteListMapper {

    // select methods

    AppWhiteList load(long id);

    AppWhiteList selectByPrimaryKey(long id);

    List<AppWhiteList> selectListByCondition(Map<String, Object> map);

    AppWhiteList selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(AppWhiteList appWhiteList);

    int updateByPrimaryKeySelective(AppWhiteList appWhiteList);

    // insert methods

    int insert(AppWhiteList appWhiteList);

    int insertSelective(AppWhiteList appWhiteList);

    int insertBatch(List<AppWhiteList> appWhiteList);

    // delete methods

    int deleteByPrimaryKey(long id);

}