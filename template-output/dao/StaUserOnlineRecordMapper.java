package com.zom.common.dao.mapper;

import com.zom.common.dao.po.StaUserOnlineRecord;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: StaUserOnlineRecord
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 用户在线时长计算表(base on logon_record)
 * @time 2020-09-17 18:51:39
 */
public interface StaUserOnlineRecordMapper {

    // select methods

    StaUserOnlineRecord load(long id);

    StaUserOnlineRecord selectByPrimaryKey(long id);

    List<StaUserOnlineRecord> selectListByCondition(Map<String, Object> map);

    StaUserOnlineRecord selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(StaUserOnlineRecord staUserOnlineRecord);

    int updateByPrimaryKeySelective(StaUserOnlineRecord staUserOnlineRecord);

    // insert methods

    int insert(StaUserOnlineRecord staUserOnlineRecord);

    int insertSelective(StaUserOnlineRecord staUserOnlineRecord);

    int insertBatch(List<StaUserOnlineRecord> staUserOnlineRecord);

    // delete methods

    int deleteByPrimaryKey(long id);

}