package com.zom.common.dao.mapper;

import com.zom.common.dao.po.ExternalSystemCfg;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: ExternalSystemCfg
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 系统互联外系统基础配置表
 * @time 2021-02-03 18:28:03
 */
public interface ExternalSystemCfgMapper {

    // select methods

    ExternalSystemCfg load(long id);

    ExternalSystemCfg selectByPrimaryKey(long id);

    List<ExternalSystemCfg> selectListByCondition(Map<String, Object> map);

    ExternalSystemCfg selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(ExternalSystemCfg externalSystemCfg);

    int updateByPrimaryKeySelective(ExternalSystemCfg externalSystemCfg);

    // insert methods

    int insert(ExternalSystemCfg externalSystemCfg);

    int insertSelective(ExternalSystemCfg externalSystemCfg);

    int insertBatch(List<ExternalSystemCfg> externalSystemCfg);

    // delete methods

    int deleteByPrimaryKey(long id);

}