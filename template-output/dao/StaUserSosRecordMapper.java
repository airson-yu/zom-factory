package com.zom.common.dao.mapper;

import com.zom.common.dao.po.StaUserSosRecord;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: StaUserSosRecord
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 一键告警记录表
 * @time 2020-09-18 13:57:23
 */
public interface StaUserSosRecordMapper {

    // select methods

    StaUserSosRecord load(long id);

    StaUserSosRecord selectByPrimaryKey(long id);

    List<StaUserSosRecord> selectListByCondition(Map<String, Object> map);

    StaUserSosRecord selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(StaUserSosRecord staUserSosRecord);

    int updateByPrimaryKeySelective(StaUserSosRecord staUserSosRecord);

    // insert methods

    int insert(StaUserSosRecord staUserSosRecord);

    int insertSelective(StaUserSosRecord staUserSosRecord);

    int insertBatch(List<StaUserSosRecord> staUserSosRecord);

    // delete methods

    int deleteByPrimaryKey(long id);

}