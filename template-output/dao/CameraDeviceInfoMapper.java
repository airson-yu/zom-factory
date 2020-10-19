package com.zom.common.dao.mapper;

import com.zom.common.dao.po.CameraDeviceInfo;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: CameraDeviceInfo
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 摄像机设备信息表
 * @time 2020-10-19 13:46:11
 */
public interface CameraDeviceInfoMapper {

    // select methods

    CameraDeviceInfo load(long id);

    CameraDeviceInfo selectByPrimaryKey(long id);

    List<CameraDeviceInfo> selectListByCondition(Map<String, Object> map);

    CameraDeviceInfo selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(CameraDeviceInfo cameraDeviceInfo);

    int updateByPrimaryKeySelective(CameraDeviceInfo cameraDeviceInfo);

    // insert methods

    int insert(CameraDeviceInfo cameraDeviceInfo);

    int insertSelective(CameraDeviceInfo cameraDeviceInfo);

    int insertBatch(List<CameraDeviceInfo> cameraDeviceInfo);

    // delete methods

    int deleteByPrimaryKey(long id);

}