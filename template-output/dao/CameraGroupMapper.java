package com.zom.common.dao.mapper;

import com.zom.common.dao.po.CameraGroup;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: CameraGroup
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 摄像机组节点表
 * @time 2020-10-26 11:21:03
 */
public interface CameraGroupMapper {

    // select methods

    CameraGroup load(long id);

    CameraGroup selectByPrimaryKey(long id);

    List<CameraGroup> selectListByCondition(Map<String, Object> map);

    CameraGroup selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(CameraGroup cameraGroup);

    int updateByPrimaryKeySelective(CameraGroup cameraGroup);

    // insert methods

    int insert(CameraGroup cameraGroup);

    int insertSelective(CameraGroup cameraGroup);

    int insertBatch(List<CameraGroup> cameraGroup);

    // delete methods

    int deleteByPrimaryKey(long id);

}