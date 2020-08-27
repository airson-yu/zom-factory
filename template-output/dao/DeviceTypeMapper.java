package com.zom.common.dao.mapper;

import com.zom.common.dao.po.DeviceType;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: DeviceType
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 
 * @time 2020-08-27 17:48:28
 */
public interface DeviceTypeMapper {

    // select methods

    DeviceType load(long id);

    DeviceType selectByPrimaryKey(long id);

    List<DeviceType> selectListByCondition(Map<String, Object> map);

    DeviceType selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(DeviceType deviceType);

    int updateByPrimaryKeySelective(DeviceType deviceType);

    // insert methods

    int insert(DeviceType deviceType);

    int insertSelective(DeviceType deviceType);

    int insertBatch(List<DeviceType> deviceType);

    // delete methods

    int deleteByPrimaryKey(long id);

}