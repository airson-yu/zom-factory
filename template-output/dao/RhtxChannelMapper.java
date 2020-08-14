package com.zom.common.dao.mapper;

import com.zom.common.dao.po.RhtxChannel;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: RhtxChannel
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 融合通信频道映射表
 * @time 2020-08-13 14:40:03
 */
public interface RhtxChannelMapper {

    // select methods

    RhtxChannel load(long id);

    RhtxChannel selectByPrimaryKey(long id);

    List<RhtxChannel> selectListByCondition(Map<String, Object> map);

    RhtxChannel selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(RhtxChannel rhtxChannel);

    int updateByPrimaryKeySelective(RhtxChannel rhtxChannel);

    // insert methods

    int insert(RhtxChannel rhtxChannel);

    int insertSelective(RhtxChannel rhtxChannel);

    int insertBatch(List<RhtxChannel> rhtxChannel);

    // delete methods

    int deleteByPrimaryKey(long id);

}