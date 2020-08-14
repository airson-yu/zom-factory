package com.zom.common.dao.mapper;

import com.zom.common.dao.po.Rhtx;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: Rhtx
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 融合通信配置表
 * @time 2020-08-13 14:40:03
 */
public interface RhtxMapper {

    // select methods

    Rhtx load(long id);

    Rhtx selectByPrimaryKey(long id);

    List<Rhtx> selectListByCondition(Map<String, Object> map);

    Rhtx selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(Rhtx rhtx);

    int updateByPrimaryKeySelective(Rhtx rhtx);

    // insert methods

    int insert(Rhtx rhtx);

    int insertSelective(Rhtx rhtx);

    int insertBatch(List<Rhtx> rhtx);

    // delete methods

    int deleteByPrimaryKey(long id);

}