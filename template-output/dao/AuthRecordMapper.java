package com.zom.common.dao.mapper;

import com.zom.common.dao.po.AuthRecord;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: AuthRecord
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 
 * @time 2020-08-27 17:48:28
 */
public interface AuthRecordMapper {

    // select methods

    AuthRecord load(long id);

    AuthRecord selectByPrimaryKey(long id);

    List<AuthRecord> selectListByCondition(Map<String, Object> map);

    AuthRecord selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(AuthRecord authRecord);

    int updateByPrimaryKeySelective(AuthRecord authRecord);

    // insert methods

    int insert(AuthRecord authRecord);

    int insertSelective(AuthRecord authRecord);

    int insertBatch(List<AuthRecord> authRecord);

    // delete methods

    int deleteByPrimaryKey(long id);

}