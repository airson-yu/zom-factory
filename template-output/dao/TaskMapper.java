package com.zom.common.dao.mapper;

import com.zom.common.dao.po.Task;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: Task
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 任务表
 * @time 2019-09-30 14:03:25
 */
public interface TaskMapper {

    // select methods

    Task load(long id);

    Task selectByPrimaryKey(long id);

    List<Task> selectListByCondition(Map<String, Object> map);

    Task selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(Task task);

    int updateByPrimaryKeySelective(Task task);

    // insert methods

    int insert(Task task);

    int insertSelective(Task task);

    int insertBatch(List<Task> task);

    // delete methods

    int deleteByPrimaryKey(long id);

}