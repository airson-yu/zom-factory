package com.zom.common.dao.mapper;

import com.zom.common.dao.po.FeatureCfg;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: FeatureCfg
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 功能开关配置表
 * @time 2020-07-16 11:14:41
 */
public interface FeatureCfgMapper {

    // select methods

    FeatureCfg load(long id);

    FeatureCfg selectByPrimaryKey(long id);

    List<FeatureCfg> selectListByCondition(Map<String, Object> map);

    FeatureCfg selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(FeatureCfg featureCfg);

    int updateByPrimaryKeySelective(FeatureCfg featureCfg);

    // insert methods

    int insert(FeatureCfg featureCfg);

    int insertSelective(FeatureCfg featureCfg);

    int insertBatch(List<FeatureCfg> featureCfg);

    // delete methods

    int deleteByPrimaryKey(long id);

}