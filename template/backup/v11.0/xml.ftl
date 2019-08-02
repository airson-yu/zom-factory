<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${daoPath}Mapper">

	<!-- @author yuronghua-airson @template 2019.08.02 v11.0 @organization Zero One More, Inc. http://www.01more.com @time ${ts} -->

	<!--====================================== 设置开始-CONFIG_BEGIN ======================================-->
	<sql id="table">${table}</sql>
	<!-- 所有基础字段 -->
    <sql id="baseColumns"><#list xmlColumnsMapList as cMap>A.${cMap.fieldName} AS ${cMap.fieldHumpName}<#if cMap_has_next>,</#if></#list></sql>
	<!-- 分组  -->
	<sql id="groupBy">
		<if test="groupBy != null and groupBy !='' "> GROUP BY ${r'$'}{groupBy,jdbcType=VARCHAR} </if>
	</sql>
	<!-- 排序  -->
	<sql id="orderBy">
		<if test="orderBy != null and orderBy !='' ">
			<if test="selfOrder == null">ORDER BY A.${r'$'}{orderBy}</if>
			<if test="selfOrder != null">ORDER BY ${r'$'}{orderBy}</if>
		</if>
		<if test="ascOrDesc != null and ascOrDesc !='' and selfOrder == null "> ${r'$'}{ascOrDesc}</if>
		<if test="orderBy == null and ascOrDesc == null "> ORDER BY A.id DESC</if>
	</sql>
	<!-- 分页 -->
	<sql id="limitCount">
		<if test="count != null and count !='' "> LIMIT ${r'$'}{start},${r'$'}{count}</if>
	</sql>
	<!--====================================== 设置结束-CONFIG_FINISH ======================================-->


	<!--=@@@@@@============================= 自定义内容 开始 _@CAUTION_SELF_BEGIN@_ =============================@@@@@@=-->
	<sql id="joinColumns"></sql>
	<sql id="baseSearch"></sql>
	<sql id="joinSearch"></sql>
	<sql id="leftJoin"></sql>
	<!--=@@@@@@============================= 自定义内容 结束 _@CAUTION_SELF_FINISH@_ =============================@@@@@@=-->


	<!--====================================== 查询开始-SELECT_BEGIN ======================================-->
	<select id="load" parameterType="java.lang.Long" resultType="${poPath}">
		SELECT <include refid="baseColumns"/> FROM <include refid="table"/> A WHERE A.id = ${r'#'}{id,jdbcType=BIGINT}
	</select>
	<select id="selectByPrimaryKey" parameterType="java.lang.Long" resultType="${poPath}">
		SELECT <include refid="baseColumns"/> FROM <include refid="table"/> A WHERE A.id = ${r'#'}{id,jdbcType=BIGINT}
	</select>

	<select id="selectByCondition" parameterType="java.util.Map" resultType="${poPath}">
    	SELECT <include refid="baseColumns"/> <include refid="joinColumns"/> FROM <include refid="table"/> A <include refid="leftJoin"/> <where> <include refid="baseSearch"/> <include refid="joinSearch"/> </where> <include refid="orderBy"/> LIMIT 0,1
	</select>

	<select id="selectListByCondition" parameterType="java.util.Map" resultType="${poPath}">
    	SELECT <include refid="baseColumns"/> <include refid="joinColumns"/> FROM <include refid="table"/> A <include refid="leftJoin"/> <where> <include refid="baseSearch"/> <include refid="joinSearch"/> </where> <include refid="orderBy"/> <include refid="limitCount"/>
	</select>

	<select id="selectCountByCondition" parameterType="java.util.Map" resultType="long">
		SELECT COUNT(*) FROM <include refid="table"/> A <include refid="leftJoin"/> <where> <include refid="baseSearch"/> <include refid="joinSearch"/> </where>
	</select>
	<!--====================================== 查询结束-SELECT_FINISH ======================================-->

	<!--====================================== 修改开始-UPDATE_BEGIN ======================================-->
	<!-- <-#-if cMap_has_next>,</-#-if> -->
	<update id="updateByPrimaryKey" parameterType="${poPath}">
	    UPDATE <include refid="table"/>
		<set><#list xmlColumnsMapList as cMap><#if cMap.fieldName != "id">${cMap.fieldName} = ${r'#'}{${cMap.fieldHumpName},jdbcType=${cMap.columnType}},</#if></#list></set>
	    WHERE id = ${r'#'}{id,jdbcType=BIGINT}
	</update>

	<update id="updateByPrimaryKeySelective" parameterType="${poPath}">
	    UPDATE <include refid="table"/>
		<set><#list xmlColumnsMapList as cMap><#if cMap.fieldName != "id"><if test="${cMap.fieldHumpName} != null">${cMap.fieldName} = ${r'#'}{${cMap.fieldHumpName},jdbcType=${cMap.columnType}},</if></#if></#list></set>
	    WHERE id = ${r'#'}{id,jdbcType=BIGINT}
	</update>
    <!--====================================== 修改结束-UPDATE_FINISH ======================================-->

	<!--====================================== 新增开始-INSERT_BEGIN ======================================-->
	<insert id="insert" parameterType="${poPath}" useGeneratedKeys="true" keyProperty="id">
	    INSERT INTO <include refid="table"/>(<#list xmlColumnsMapList as cMap><#if cMap.fieldName !='id'>${cMap.fieldName}<#if cMap_has_next>,</#if></#if></#list>)
	    VALUES( <#list xmlColumnsMapList as cMap><#if cMap.fieldName !='id'>${r'#'}{${cMap.fieldHumpName},jdbcType=${cMap.columnType}}<#if cMap_has_next>,</#if></#if></#list>)
	</insert>

	<insert id="insertSelective" parameterType="${poPath}" useGeneratedKeys="true" keyProperty="id">
	    INSERT INTO <include refid="table"/>
		<trim prefix="(" suffix=")" suffixOverrides=","> <#list xmlColumnsMapList as cMap><#if cMap.fieldName != "id"><if test="${cMap.fieldHumpName} != null"> ${cMap.fieldName},</if></#if></#list></trim>
		<trim prefix="values (" suffix=")" suffixOverrides=","><#list xmlColumnsMapList as cMap><#if cMap.fieldName != "id"><if test="${cMap.fieldHumpName} != null">${r'#'}{${cMap.fieldHumpName},jdbcType=${cMap.columnType}},</if></#if></#list></trim>
	</insert>

	<insert id="insertBatch">
		INSERT INTO <include refid="table"/>(<#list xmlColumnsMapList as cMap><#if cMap.fieldName !='id'>${cMap.fieldName}<#if cMap_has_next>,</#if></#if></#list>)
		VALUES <foreach collection="list" item="c" separator=",">(<#list xmlColumnsMapList as cMap><#if cMap.fieldName !='id'>${r'#'}{c.${cMap.fieldHumpName},jdbcType=${cMap.columnType}}<#if cMap_has_next>,</#if></#if></#list>)</foreach>
	</insert>
  	<!--====================================== 新增结束-INSERT_FINISH ======================================-->

	<!--====================================== 删除开始-DELETE_BEGIN ======================================-->
	<delete id="deleteByPrimaryKey" parameterType="java.lang.Long">
	    DELETE FROM <include refid="table"/> WHERE id = ${r'#'}{id,jdbcType=BIGINT}
	</delete>
	<!--====================================== 删除结束-DELETE_FINISH ======================================-->

</mapper>