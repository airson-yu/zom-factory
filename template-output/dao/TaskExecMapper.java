package com.zom.common.dao.mapper;

import com.zom.common.dao.po.TaskExec;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: TaskExec
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 任务-执行调度台表
 * @time 2019-11-04 10:39:25
 */
public interface TaskExecMapper {

    // select methods

    TaskExec load(long id);

    TaskExec selectByPrimaryKey(long id);

    List<TaskExec> selectListByCondition(Map<String, Object> map);

    TaskExec selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(TaskExec taskExec);

    int updateByPrimaryKeySelective(TaskExec taskExec);

    // insert methods

    int insert(TaskExec taskExec);

    int insertSelective(TaskExec taskExec);

    int insertBatch(List<TaskExec> taskExec);

    // delete methods

    int deleteByPrimaryKey(long id);

}