package com.zom.common.dao.mapper;

import com.zom.common.dao.po.MetroLine;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: MetroLine
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 地铁线路表
 * @time 2021-01-04 12:10:36
 */
public interface MetroLineMapper {

    // select methods

    MetroLine load(long id);

    MetroLine selectByPrimaryKey(long id);

    List<MetroLine> selectListByCondition(Map<String, Object> map);

    MetroLine selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(MetroLine metroLine);

    int updateByPrimaryKeySelective(MetroLine metroLine);

    // insert methods

    int insert(MetroLine metroLine);

    int insertSelective(MetroLine metroLine);

    int insertBatch(List<MetroLine> metroLine);

    // delete methods

    int deleteByPrimaryKey(long id);

}