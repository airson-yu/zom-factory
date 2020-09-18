package com.zom.common.dao.mapper;

import com.zom.common.dao.po.StaOriginalLog;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: StaOriginalLog
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 原始业务日志记录表（定期清理）
 * @time 2020-09-18 13:57:23
 */
public interface StaOriginalLogMapper {

    // select methods

    StaOriginalLog load(long id);

    StaOriginalLog selectByPrimaryKey(long id);

    List<StaOriginalLog> selectListByCondition(Map<String, Object> map);

    StaOriginalLog selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(StaOriginalLog staOriginalLog);

    int updateByPrimaryKeySelective(StaOriginalLog staOriginalLog);

    // insert methods

    int insert(StaOriginalLog staOriginalLog);

    int insertSelective(StaOriginalLog staOriginalLog);

    int insertBatch(List<StaOriginalLog> staOriginalLog);

    // delete methods

    int deleteByPrimaryKey(long id);

}