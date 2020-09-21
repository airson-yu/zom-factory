package com.zom.common.dao.mapper;

import com.zom.common.dao.po.StaUserSosRecordOrigin;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: StaUserSosRecordOrigin
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 一键告警记录表
 * @time 2020-09-21 10:28:48
 */
public interface StaUserSosRecordOriginMapper {

    // select methods

    StaUserSosRecordOrigin load(long id);

    StaUserSosRecordOrigin selectByPrimaryKey(long id);

    List<StaUserSosRecordOrigin> selectListByCondition(Map<String, Object> map);

    StaUserSosRecordOrigin selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(StaUserSosRecordOrigin staUserSosRecordOrigin);

    int updateByPrimaryKeySelective(StaUserSosRecordOrigin staUserSosRecordOrigin);

    // insert methods

    int insert(StaUserSosRecordOrigin staUserSosRecordOrigin);

    int insertSelective(StaUserSosRecordOrigin staUserSosRecordOrigin);

    int insertBatch(List<StaUserSosRecordOrigin> staUserSosRecordOrigin);

    // delete methods

    int deleteByPrimaryKey(long id);

}