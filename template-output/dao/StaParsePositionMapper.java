package com.zom.common.dao.mapper;

import com.zom.common.dao.po.StaParsePosition;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: StaParsePosition
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 数据解析位置标识表
 * @time 2020-09-17 18:51:39
 */
public interface StaParsePositionMapper {

    // select methods

    StaParsePosition load(long id);

    StaParsePosition selectByPrimaryKey(long id);

    List<StaParsePosition> selectListByCondition(Map<String, Object> map);

    StaParsePosition selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(StaParsePosition staParsePosition);

    int updateByPrimaryKeySelective(StaParsePosition staParsePosition);

    // insert methods

    int insert(StaParsePosition staParsePosition);

    int insertSelective(StaParsePosition staParsePosition);

    int insertBatch(List<StaParsePosition> staParsePosition);

    // delete methods

    int deleteByPrimaryKey(long id);

}