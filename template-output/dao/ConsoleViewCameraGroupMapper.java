package com.zom.common.dao.mapper;

import com.zom.common.dao.po.ConsoleViewCameraGroup;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: ConsoleViewCameraGroup
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 客户端能够访问摄像机组的节点记录表
 * @time 2020-09-30 15:00:59
 */
public interface ConsoleViewCameraGroupMapper {

    // select methods

    ConsoleViewCameraGroup load(long id);

    ConsoleViewCameraGroup selectByPrimaryKey(long id);

    List<ConsoleViewCameraGroup> selectListByCondition(Map<String, Object> map);

    ConsoleViewCameraGroup selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(ConsoleViewCameraGroup consoleViewCameraGroup);

    int updateByPrimaryKeySelective(ConsoleViewCameraGroup consoleViewCameraGroup);

    // insert methods

    int insert(ConsoleViewCameraGroup consoleViewCameraGroup);

    int insertSelective(ConsoleViewCameraGroup consoleViewCameraGroup);

    int insertBatch(List<ConsoleViewCameraGroup> consoleViewCameraGroup);

    // delete methods

    int deleteByPrimaryKey(long id);

}