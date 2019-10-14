package com.zom.common.dao.mapper;

import com.zom.common.dao.po.TaskUser;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: TaskUser
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 任务-用户关联表
 * @time 2019-10-12 10:19:14
 */
public interface TaskUserMapper {

    // select methods

    TaskUser load(long id);

    TaskUser selectByPrimaryKey(long id);

    List<TaskUser> selectListByCondition(Map<String, Object> map);

    TaskUser selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(TaskUser taskUser);

    int updateByPrimaryKeySelective(TaskUser taskUser);

    // insert methods

    int insert(TaskUser taskUser);

    int insertSelective(TaskUser taskUser);

    int insertBatch(List<TaskUser> taskUser);

    // delete methods

    int deleteByPrimaryKey(long id);

}