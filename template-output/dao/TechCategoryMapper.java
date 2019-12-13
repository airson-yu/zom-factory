package com.zom.common.dao.mapper;

import com.zom.common.dao.po.TechCategory;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: TechCategory
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 分类表
 * @time 2019-12-13 17:56:27
 */
public interface TechCategoryMapper {

    // select methods

    TechCategory load(long id);

    TechCategory selectByPrimaryKey(long id);

    List<TechCategory> selectListByCondition(Map<String, Object> map);

    TechCategory selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(TechCategory techCategory);

    int updateByPrimaryKeySelective(TechCategory techCategory);

    // insert methods

    int insert(TechCategory techCategory);

    int insertSelective(TechCategory techCategory);

    int insertBatch(List<TechCategory> techCategory);

    // delete methods

    int deleteByPrimaryKey(long id);

}