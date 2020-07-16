package com.zom.common.dao.mapper;

import com.zom.common.dao.po.IccidChangeRecord;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: IccidChangeRecord
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 用户的ICCID变更记录表
 * @time 2020-07-16 11:14:41
 */
public interface IccidChangeRecordMapper {

    // select methods

    IccidChangeRecord load(long id);

    IccidChangeRecord selectByPrimaryKey(long id);

    List<IccidChangeRecord> selectListByCondition(Map<String, Object> map);

    IccidChangeRecord selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(IccidChangeRecord iccidChangeRecord);

    int updateByPrimaryKeySelective(IccidChangeRecord iccidChangeRecord);

    // insert methods

    int insert(IccidChangeRecord iccidChangeRecord);

    int insertSelective(IccidChangeRecord iccidChangeRecord);

    int insertBatch(List<IccidChangeRecord> iccidChangeRecord);

    // delete methods

    int deleteByPrimaryKey(long id);

}