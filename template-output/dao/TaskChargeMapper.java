package com.zom.common.dao.mapper;

import com.zom.common.dao.po.TaskCharge;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: TaskCharge
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 任务-负责调度台表
 * @time 2019-11-04 10:39:25
 */
public interface TaskChargeMapper {

    // select methods

    TaskCharge load(long id);

    TaskCharge selectByPrimaryKey(long id);

    List<TaskCharge> selectListByCondition(Map<String, Object> map);

    TaskCharge selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(TaskCharge taskCharge);

    int updateByPrimaryKeySelective(TaskCharge taskCharge);

    // insert methods

    int insert(TaskCharge taskCharge);

    int insertSelective(TaskCharge taskCharge);

    int insertBatch(List<TaskCharge> taskCharge);

    // delete methods

    int deleteByPrimaryKey(long id);

}