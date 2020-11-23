package com.zom.common.dao.mapper;

import com.zom.common.dao.po.ConsoleBindPhoneMap;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: ConsoleBindPhoneMap
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 调度台与短信号码绑定表
 * @time 2020-11-23 16:26:00
 */
public interface ConsoleBindPhoneMapMapper {

    // select methods

    ConsoleBindPhoneMap load(long id);

    ConsoleBindPhoneMap selectByPrimaryKey(long id);

    List<ConsoleBindPhoneMap> selectListByCondition(Map<String, Object> map);

    ConsoleBindPhoneMap selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(ConsoleBindPhoneMap consoleBindPhoneMap);

    int updateByPrimaryKeySelective(ConsoleBindPhoneMap consoleBindPhoneMap);

    // insert methods

    int insert(ConsoleBindPhoneMap consoleBindPhoneMap);

    int insertSelective(ConsoleBindPhoneMap consoleBindPhoneMap);

    int insertBatch(List<ConsoleBindPhoneMap> consoleBindPhoneMap);

    // delete methods

    int deleteByPrimaryKey(long id);

}