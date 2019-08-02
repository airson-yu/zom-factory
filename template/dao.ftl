package ${daoPackage};

import ${poPackage}.${className};

import java.util.List;
import java.util.Map;

/**
 * @author yuronghua-airson
 * @description Mybatis Mapper: ${className}
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark ${tableComment}
 * @time ${ts}
 */
public interface ${className}Mapper {

    // select methods

    ${className} load(long id);

    <#--@Select("SELECT * FROM ${table} where id=${r'#'}{id}")-->
    ${className} selectByPrimaryKey(long id);

    List<${className}> selectListByCondition(Map<String, Object> map);

    ${className} selectByCondition(Map<String, Object> map);

    Long selectCountByCondition(Map<String, Object> map);

    // update methods

    int updateByPrimaryKey(${className} ${objectName});

    int updateByPrimaryKeySelective(${className} ${objectName});

    // insert methods

    int insert(${className} ${objectName});

    int insertSelective(${className} ${objectName});

    int insertBatch(List<${className}> ${objectName});

    // delete methods

    <#--@Delete("delete from ${table} where id=${r'#'}{id}")-->
    int deleteByPrimaryKey(long id);

    <#--
    @Insert("insert into users(name,age) values(${r'#'}{name},${r'#'}{age})")
    int insert(User user);
    @Update("update users set name=${r'#'}{name},age=${r'#'}{age} where id=${r'#'}{id}")
    int update(User user);
    -->
}