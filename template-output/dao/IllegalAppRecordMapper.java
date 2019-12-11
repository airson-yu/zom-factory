package com.zom.common.dao.mapper;

import com.zom.common.dao.po.IllegalAppRecord;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: IllegalAppRecord
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark User installed illegal app record table
 * @time 2019-12-11 15:42:59
 */
public interface IllegalAppRecordMapper {

    // select methods

    IllegalAppRecord load(long id);

    IllegalAppRecord selectByPrimaryKey(long id);

    List<IllegalAppRecord> selectListByCondition(Map<String, Object> map);

    IllegalAppRecord selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(IllegalAppRecord illegalAppRecord);

    int updateByPrimaryKeySelective(IllegalAppRecord illegalAppRecord);

    // insert methods

    int insert(IllegalAppRecord illegalAppRecord);

    int insertSelective(IllegalAppRecord illegalAppRecord);

    int insertBatch(List<IllegalAppRecord> illegalAppRecord);

    // delete methods

    int deleteByPrimaryKey(long id);

}