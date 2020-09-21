package com.zom.common.dao.mapper;

import com.zom.common.dao.po.StaUserVideoRecordOrigin;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: StaUserVideoRecordOrigin
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 视频通话记录表
 * @time 2020-09-21 10:28:48
 */
public interface StaUserVideoRecordOriginMapper {

    // select methods

    StaUserVideoRecordOrigin load(long id);

    StaUserVideoRecordOrigin selectByPrimaryKey(long id);

    List<StaUserVideoRecordOrigin> selectListByCondition(Map<String, Object> map);

    StaUserVideoRecordOrigin selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(StaUserVideoRecordOrigin staUserVideoRecordOrigin);

    int updateByPrimaryKeySelective(StaUserVideoRecordOrigin staUserVideoRecordOrigin);

    // insert methods

    int insert(StaUserVideoRecordOrigin staUserVideoRecordOrigin);

    int insertSelective(StaUserVideoRecordOrigin staUserVideoRecordOrigin);

    int insertBatch(List<StaUserVideoRecordOrigin> staUserVideoRecordOrigin);

    // delete methods

    int deleteByPrimaryKey(long id);

}