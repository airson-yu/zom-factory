package com.zom.common.dao.mapper;

import com.zom.common.dao.po.TaskJob;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: TaskJob
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 工作内容表
 * @time 2019-10-12 10:19:14
 */
public interface TaskJobMapper {

    // select methods

    TaskJob load(long id);

    TaskJob selectByPrimaryKey(long id);

    List<TaskJob> selectListByCondition(Map<String, Object> map);

    TaskJob selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(TaskJob taskJob);

    int updateByPrimaryKeySelective(TaskJob taskJob);

    // insert methods

    int insert(TaskJob taskJob);

    int insertSelective(TaskJob taskJob);

    int insertBatch(List<TaskJob> taskJob);

    // delete methods

    int deleteByPrimaryKey(long id);

}