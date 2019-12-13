package com.zom.common.dao.mapper;

import com.zom.common.dao.po.TechIm;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: TechIm
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark user table
 * @time 2019-12-13 17:56:27
 */
public interface TechImMapper {

    // select methods

    TechIm load(long id);

    TechIm selectByPrimaryKey(long id);

    List<TechIm> selectListByCondition(Map<String, Object> map);

    TechIm selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(TechIm techIm);

    int updateByPrimaryKeySelective(TechIm techIm);

    // insert methods

    int insert(TechIm techIm);

    int insertSelective(TechIm techIm);

    int insertBatch(List<TechIm> techIm);

    // delete methods

    int deleteByPrimaryKey(long id);

}