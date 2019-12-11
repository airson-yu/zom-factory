package com.zom.common.dao.mapper;

import com.zom.common.dao.po.UserPhoneRecord;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: UserPhoneRecord
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark tx/rx phone record table
 * @time 2019-12-11 15:42:59
 */
public interface UserPhoneRecordMapper {

    // select methods

    UserPhoneRecord load(long id);

    UserPhoneRecord selectByPrimaryKey(long id);

    List<UserPhoneRecord> selectListByCondition(Map<String, Object> map);

    UserPhoneRecord selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(UserPhoneRecord userPhoneRecord);

    int updateByPrimaryKeySelective(UserPhoneRecord userPhoneRecord);

    // insert methods

    int insert(UserPhoneRecord userPhoneRecord);

    int insertSelective(UserPhoneRecord userPhoneRecord);

    int insertBatch(List<UserPhoneRecord> userPhoneRecord);

    // delete methods

    int deleteByPrimaryKey(long id);

}