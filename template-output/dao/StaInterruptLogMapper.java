package com.zom.common.dao.mapper;

import com.zom.common.dao.po.StaInterruptLog;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: StaInterruptLog
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 手动停止业务记录表
 * @time 2020-11-05 10:49:50
 */
public interface StaInterruptLogMapper {

    // select methods

    StaInterruptLog load(long id);

    StaInterruptLog selectByPrimaryKey(long id);

    List<StaInterruptLog> selectListByCondition(Map<String, Object> map);

    StaInterruptLog selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(StaInterruptLog staInterruptLog);

    int updateByPrimaryKeySelective(StaInterruptLog staInterruptLog);

    // insert methods

    int insert(StaInterruptLog staInterruptLog);

    int insertSelective(StaInterruptLog staInterruptLog);

    int insertBatch(List<StaInterruptLog> staInterruptLog);

    // delete methods

    int deleteByPrimaryKey(long id);

}