package com.zom.common.dao.mapper;

import com.zom.common.dao.po.SmsGwPhoneMapping;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: SmsGwPhoneMapping
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 短信网关状态表
 * @time 2020-11-23 16:26:00
 */
public interface SmsGwPhoneMappingMapper {

    // select methods

    SmsGwPhoneMapping load(long id);

    SmsGwPhoneMapping selectByPrimaryKey(long id);

    List<SmsGwPhoneMapping> selectListByCondition(Map<String, Object> map);

    SmsGwPhoneMapping selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(SmsGwPhoneMapping smsGwPhoneMapping);

    int updateByPrimaryKeySelective(SmsGwPhoneMapping smsGwPhoneMapping);

    // insert methods

    int insert(SmsGwPhoneMapping smsGwPhoneMapping);

    int insertSelective(SmsGwPhoneMapping smsGwPhoneMapping);

    int insertBatch(List<SmsGwPhoneMapping> smsGwPhoneMapping);

    // delete methods

    int deleteByPrimaryKey(long id);

}