package com.zom.common.dao.mapper;

import com.zom.common.dao.po.VersionDeptForbidMap;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: VersionDeptForbidMap
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 版本关联到部门，部门下的用户禁止用老版本登录
 * @time 2020-04-01 10:50:11
 */
public interface VersionDeptForbidMapMapper {

    // select methods

    VersionDeptForbidMap load(long id);

    VersionDeptForbidMap selectByPrimaryKey(long id);

    List<VersionDeptForbidMap> selectListByCondition(Map<String, Object> map);

    VersionDeptForbidMap selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(VersionDeptForbidMap versionDeptForbidMap);

    int updateByPrimaryKeySelective(VersionDeptForbidMap versionDeptForbidMap);

    // insert methods

    int insert(VersionDeptForbidMap versionDeptForbidMap);

    int insertSelective(VersionDeptForbidMap versionDeptForbidMap);

    int insertBatch(List<VersionDeptForbidMap> versionDeptForbidMap);

    // delete methods

    int deleteByPrimaryKey(long id);

}