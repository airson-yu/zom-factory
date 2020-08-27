package com.zom.common.dao.mapper;

import com.zom.common.dao.po.Corp;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: Corp
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 
 * @time 2020-08-27 17:48:28
 */
public interface CorpMapper {

    // select methods

    Corp load(long id);

    Corp selectByPrimaryKey(long id);

    List<Corp> selectListByCondition(Map<String, Object> map);

    Corp selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(Corp corp);

    int updateByPrimaryKeySelective(Corp corp);

    // insert methods

    int insert(Corp corp);

    int insertSelective(Corp corp);

    int insertBatch(List<Corp> corp);

    // delete methods

    int deleteByPrimaryKey(long id);

}