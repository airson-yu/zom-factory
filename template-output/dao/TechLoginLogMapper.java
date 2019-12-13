package com.zom.common.dao.mapper;

import com.zom.common.dao.po.TechLoginLog;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: TechLoginLog
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 登录日志表
 * @time 2019-12-13 17:56:27
 */
public interface TechLoginLogMapper {

    // select methods

    TechLoginLog load(long id);

    TechLoginLog selectByPrimaryKey(long id);

    List<TechLoginLog> selectListByCondition(Map<String, Object> map);

    TechLoginLog selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(TechLoginLog techLoginLog);

    int updateByPrimaryKeySelective(TechLoginLog techLoginLog);

    // insert methods

    int insert(TechLoginLog techLoginLog);

    int insertSelective(TechLoginLog techLoginLog);

    int insertBatch(List<TechLoginLog> techLoginLog);

    // delete methods

    int deleteByPrimaryKey(long id);

}