package com.zom.common.dao.mapper;

import com.zom.common.dao.po.StaUserVideoRecord;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: StaUserVideoRecord
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 视频通话记录表
 * @time 2020-09-17 18:51:39
 */
public interface StaUserVideoRecordMapper {

    // select methods

    StaUserVideoRecord load(long id);

    StaUserVideoRecord selectByPrimaryKey(long id);

    List<StaUserVideoRecord> selectListByCondition(Map<String, Object> map);

    StaUserVideoRecord selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(StaUserVideoRecord staUserVideoRecord);

    int updateByPrimaryKeySelective(StaUserVideoRecord staUserVideoRecord);

    // insert methods

    int insert(StaUserVideoRecord staUserVideoRecord);

    int insertSelective(StaUserVideoRecord staUserVideoRecord);

    int insertBatch(List<StaUserVideoRecord> staUserVideoRecord);

    // delete methods

    int deleteByPrimaryKey(long id);

}