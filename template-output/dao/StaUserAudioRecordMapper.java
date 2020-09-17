package com.zom.common.dao.mapper;

import com.zom.common.dao.po.StaUserAudioRecord;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: StaUserAudioRecord
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 语音通话记录表
 * @time 2020-09-17 18:51:39
 */
public interface StaUserAudioRecordMapper {

    // select methods

    StaUserAudioRecord load(long id);

    StaUserAudioRecord selectByPrimaryKey(long id);

    List<StaUserAudioRecord> selectListByCondition(Map<String, Object> map);

    StaUserAudioRecord selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(StaUserAudioRecord staUserAudioRecord);

    int updateByPrimaryKeySelective(StaUserAudioRecord staUserAudioRecord);

    // insert methods

    int insert(StaUserAudioRecord staUserAudioRecord);

    int insertSelective(StaUserAudioRecord staUserAudioRecord);

    int insertBatch(List<StaUserAudioRecord> staUserAudioRecord);

    // delete methods

    int deleteByPrimaryKey(long id);

}