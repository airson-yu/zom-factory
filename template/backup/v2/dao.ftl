package ${daoPackage};

import ${poPackage}.${className};

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层Mapper：${className} <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华-airson <p>
* <strong> 编写时间：</strong> 2018年8月1日09:00:00 <p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都零壹众科技有限公司 http://www.01more.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 10.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> ${tableComment} <p>
 */

public interface ${className}Mapper {

    // select methods

    @Select("select * from ${table} where id=#{id}")
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

    @Delete("delete from ${table} where id=#{id}")
    int deleteByPrimaryKey(long id);


    /*@Insert("insert into users(name,age) values(#{name},#{age})")
    int insert(User user);
    @Update("update users set name=#{name},age=#{age} where id=#{id}")
    int update(User user);*/

}