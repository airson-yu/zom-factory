package com.zom.common.dao.mapper;

import com.zom.common.dao.po.TechArticle;

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: TechArticle
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark 文章表
 * @time 2019-12-13 17:56:27
 */
public interface TechArticleMapper {

    // select methods

    TechArticle load(long id);

    TechArticle selectByPrimaryKey(long id);

    List<TechArticle> selectListByCondition(Map<String, Object> map);

    TechArticle selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(TechArticle techArticle);

    int updateByPrimaryKeySelective(TechArticle techArticle);

    // insert methods

    int insert(TechArticle techArticle);

    int insertSelective(TechArticle techArticle);

    int insertBatch(List<TechArticle> techArticle);

    // delete methods

    int deleteByPrimaryKey(long id);

}