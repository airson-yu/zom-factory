package com.zom.common.dao.mapper;

import com.zom.common.dao.po.UserSmsRecord;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: UserSmsRecord
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark tx/rx sms record table
 * @time 2019-12-11 15:42:59
 */
public interface UserSmsRecordMapper {

    // select methods

    UserSmsRecord load(long id);

    UserSmsRecord selectByPrimaryKey(long id);

    List<UserSmsRecord> selectListByCondition(Map<String, Object> map);

    UserSmsRecord selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(UserSmsRecord userSmsRecord);

    int updateByPrimaryKeySelective(UserSmsRecord userSmsRecord);

    // insert methods

    int insert(UserSmsRecord userSmsRecord);

    int insertSelective(UserSmsRecord userSmsRecord);

    int insertBatch(List<UserSmsRecord> userSmsRecord);

    // delete methods

    int deleteByPrimaryKey(long id);

}