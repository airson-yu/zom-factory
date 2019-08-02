<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${daoPath}Mapper">
	<!-- 版本：4.0，时间：2017年1月6日16:48:53，公司：成都蓝海飞鱼科技有限公司 http://lhfeiyu.com，作者：虞荣华 -->
	<!--====================================== 设置开始-CONFIG_BEGIN ======================================-->
	<sql id="table">${table}</sql>
	<!-- 所有基础字段 --> <sql id="baseColumns"><#list xmlColumnsMapList as cMap>A.${cMap.fieldName} AS ${cMap.fieldHumpName}<#if cMap_has_next>,</#if></#list></sql>
	<!-- 重要基础字段 --> <sql id="baseHotColumns"><#list xmlColumnsMapList as cMap>A.${cMap.fieldName} AS ${cMap.fieldHumpName}<#if cMap_has_next>,</#if></#list></sql>
	<!-- 分组  --> <sql id="groupBy"><if test="groupBy != null and groupBy !='' "> GROUP BY ${r'#'}{groupBy,jdbcType=VARCHAR} </if></sql>
	<!-- 排序  --> <sql id="orderBy"><if test="orderBy != null and orderBy !='' "> ORDER BY ${r'$'}{orderBy} </if><if test="orderBy == null "> ORDER BY A.id DESC </if></sql>
	<!-- 分页 --> <sql id="limitCount"><if test="count != null and count !='' "> LIMIT ${r'$'}{start},${r'$'}{count}</if> </sql>
	<!--====================================== 设置结束-CONFIG_FINISH ======================================-->
	

	<!--=@@@@@@============================= 自定义内容 开始 _@CAUTION_SELF_BEGIN@_ =============================@@@@@@=-->
	<#if isInit == true>
	<sql id="joinColumns"></sql>
	<sql id="baseSearch"></sql>
	<sql id="joinSearch"></sql>
	<sql id="leftJoin"></sql>
	</#if>
	${selfAddContent}
	<!--=@@@@@@============================= 自定义内容 结束 _@CAUTION_SELF_FINISH@_ =============================@@@@@@=-->

		
	<!--====================================== 查询开始-SELECT_BEGIN ======================================-->
	<select id="selectListBaseByCondition" parameterType="java.util.Map" resultType="${poPath}">
    	SELECT <include refid="baseColumns"/> FROM <include refid="table"/> A <where> <include refid="baseSearch"/> </where> <include refid="orderBy"/> <include refid="limitCount"/>
	</select>
	
	<select id="selectListJoinByCondition" parameterType="java.util.Map" resultType="${poPath}">
    	SELECT <include refid="joinColumns"/><include refid="baseColumns"/> FROM <include refid="table"/> A <where> <include refid="baseSearch"/><include refid="joinSearch"/> </where> <include refid="orderBy"/> <include refid="limitCount"/>
	</select>
	
	<select id="selectListIdBaseByCondition" parameterType="java.util.Map" resultType="java.lang.Integer">
    	SELECT A.id FROM <include refid="table"/> A <where> <include refid="baseSearch"/> </where> <include refid="orderBy"/> <include refid="limitCount"/>
	</select>
	
	<select id="selectListIdJoinByCondition" parameterType="java.util.Map" resultType="java.lang.Integer">
    	SELECT A.id FROM <include refid="table"/> A <where> <include refid="baseSearch"/><include refid="joinSearch"/> </where> <include refid="orderBy"/> <include refid="limitCount"/>
	</select>
	
	<select id="selectOneBaseByCondition" parameterType="java.util.Map" resultType="${poPath}">
    	SELECT <include refid="baseColumns"/> FROM <include refid="table"/> A <where> <include refid="baseSearch"/> </where> <include refid="orderBy"/> LIMIT 0,1
	</select>
	
	<select id="selectOneJoinByCondition" parameterType="java.util.Map" resultType="${poPath}">
    	SELECT <include refid="joinColumns"/><include refid="baseColumns"/> FROM <include refid="table"/> A <where> <include refid="baseSearch"/><include refid="joinSearch"/> </where> <include refid="orderBy"/> LIMIT 0,1
	</select>
	
	<select id="selectOneBaseByPrimaryKey" resultType="${poPath}" parameterType="java.lang.Integer">
		SELECT <include refid="baseColumns"/> FROM <include refid="table"/> A WHERE A.id = ${r'#'}{id,jdbcType=INTEGER}
	</select>
	<select id="selectOneJoinByPrimaryKey" resultType="${poPath}" parameterType="java.lang.Integer">
		SELECT <include refid="joinColumns"/><include refid="baseColumns"/> FROM <include refid="table"/> A WHERE A.id = ${r'#'}{id,jdbcType=INTEGER}
	</select>
	
	<select id="selectOneBaseBySerial" resultType="${poPath}">
		SELECT <include refid="baseColumns"/> FROM <include refid="table"/> A WHERE A.serial = ${r'#'}{serial,jdbcType=VARCHAR} LIMIT 0,1
	</select>
	<select id="selectOneJoinBySerial" resultType="${poPath}">
		SELECT <include refid="joinColumns"/><include refid="baseColumns"/> FROM <include refid="table"/> A WHERE A.serial = ${r'#'}{serial,jdbcType=VARCHAR} LIMIT 0,1
	</select>
	
	<select id="selectCountBase" parameterType="java.util.Map" resultType="java.lang.Integer">
		SELECT COUNT(*) FROM <include refid="table"/> A <where> <include refid="baseSearch"/> </where>
	</select>
	<select id="selectCountJoin" parameterType="java.util.Map" resultType="java.lang.Integer">
		SELECT COUNT(*) FROM <include refid="table"/> A <include refid="leftJoin"/> <where> <include refid="baseSearch"/><include refid="joinSearch"/> </where>
	</select>
	
	<!--====================================== 查询结束-SELECT_FINISH ======================================-->
	
	<!--====================================== 修改开始-UPDATE_BEGIN ======================================-->
	<!-- <-#-if cMap_has_next>,</-#-if> -->
	<update id="updateByPrimaryKey" parameterType="${poPath}">
	    UPDATE <include refid="table"/>
		<set><#list xmlColumnsMapList as cMap><#if cMap.fieldName != "id" && cMap.fieldName != "updated_by">${cMap.fieldName} = ${r'#'}{${cMap.fieldHumpName},jdbcType=${cMap.columnType}},</#if></#list><if test="updatedBy != null">updated_by = ${r'#'}{updatedBy,jdbcType=VARCHAR},</if>updated_at = NOW()</set>
	    WHERE id = ${r'#'}{id,jdbcType=INTEGER}
	</update>
	
	<update id="updateBySerial" parameterType="${poPath}">
	    UPDATE <include refid="table"/>
		<set><#list xmlColumnsMapList as cMap><#if cMap.fieldName != "id" && cMap.fieldName != "updated_by">${cMap.fieldName} = ${r'#'}{${cMap.fieldHumpName},jdbcType=${cMap.columnType}},</#if></#list><if test="updatedBy != null">updated_by = ${r'#'}{updatedBy,jdbcType=VARCHAR},</if>updated_at = NOW()</set>
	    WHERE serial = ${r'#'}{serial,jdbcType=VARCHAR}
	</update>
	
	<update id="updateFieldById" parameterType="java.util.Map">
		UPDATE <include refid="table"/>
		<set><if test="expression1 != null">${r'$'}{expression1},</if><if test="expression2 != null">${r'$'}{expression2},</if><if test="expression3 != null">${r'$'}{expression3},</if><if test="expression4 != null">${r'$'}{expression4},</if><if test="expression5 != null">${r'$'}{expression5},</if><if test="updatedBy != null">updated_by = ${r'#'}{updatedBy,jdbcType=VARCHAR},</if>updated_at = NOW()</set>
		WHERE id = ${r'#'}{id,jdbcType=INTEGER}
	</update>
	
	<update id="updateByPrimaryKeySelective" parameterType="${poPath}">
	    UPDATE <include refid="table"/>
		<set><#list xmlColumnsMapList as cMap><#if cMap.fieldName != "id" && cMap.fieldName != "updated_by"><if test="${cMap.fieldHumpName} != null">${cMap.fieldName} = ${r'#'}{${cMap.fieldHumpName},jdbcType=${cMap.columnType}},</if></#if></#list><if test="updatedBy != null">updated_by = ${r'#'}{updatedBy,jdbcType=VARCHAR},</if>updated_at = NOW()</set>
	    WHERE id = ${r'#'}{id,jdbcType=INTEGER}
	</update>
	
	<update id="updateBySerialSelective" parameterType="${poPath}">
	    UPDATE <include refid="table"/>
		<set><#list xmlColumnsMapList as cMap><#if cMap.fieldName != "id" && cMap.fieldName != "updated_by"><if test="${cMap.fieldHumpName} != null">${cMap.fieldName} = ${r'#'}{${cMap.fieldHumpName},jdbcType=${cMap.columnType}},</if></#if></#list><if test="updatedBy != null">updated_by = ${r'#'}{updatedBy,jdbcType=VARCHAR},</if>updated_at = NOW()</set>
	    WHERE serial = ${r'#'}{serial,jdbcType=VARCHAR}
	</update>
	
    <!--====================================== 修改结束-UPDATE_FINISH ======================================-->
	
	<!--====================================== 新增开始-INSERT_BEGIN ======================================-->
	<insert id="insert" parameterType="${poPath}" useGeneratedKeys="true" keyProperty="id">
	    INSERT INTO <include refid="table"/>(<#list xmlColumnsMapList as cMap><#if cMap.fieldName !='id'>${cMap.fieldName}<#if cMap_has_next>,</#if></#if></#list>)
	    VALUES( <#list xmlColumnsMapList as cMap><#if cMap.fieldName !='id'>${r'#'}{${cMap.fieldHumpName},jdbcType=${cMap.columnType}}<#if cMap_has_next>,</#if></#if></#list>)
	</insert>
	
	<insert id="insertBatch">
		INSERT INTO <include refid="table"/>(<#list xmlColumnsMapList as cMap><#if cMap.fieldName !='id'>${cMap.fieldName}<#if cMap_has_next>,</#if></#if></#list>)
		VALUES <foreach collection="list" item="c" separator=",">(<#list xmlColumnsMapList as cMap><#if cMap.fieldName !='id'>${r'#'}{c.${cMap.fieldHumpName},jdbcType=${cMap.columnType}}<#if cMap_has_next>,</#if></#if></#list>)</foreach>
	</insert>
  	
	<insert id="insertSelective" parameterType="${poPath}" useGeneratedKeys="true" keyProperty="id">
	    INSERT INTO <include refid="table"/>
		<trim prefix="(" suffix=")" suffixOverrides=","> <#list xmlColumnsMapList as cMap><#if cMap.fieldName != "id"><if test="${cMap.fieldHumpName} != null"> ${cMap.fieldName},</if></#if></#list></trim>
		<trim prefix="values (" suffix=")" suffixOverrides=","><#list xmlColumnsMapList as cMap><#if cMap.fieldName != "id"><if test="${cMap.fieldHumpName} != null">${r'#'}{${cMap.fieldHumpName},jdbcType=${cMap.columnType}},</if></#if></#list></trim>
	</insert>
  	<!--====================================== 新增结束-INSERT_FINISH ======================================-->
	
	<!--====================================== 删除开始-DELETE_BEGIN ======================================-->
	<delete id="deleteByPrimaryKey" parameterType="java.lang.Integer">
	    DELETE FROM <include refid="table"/> WHERE id = ${r'#'}{id,jdbcType=INTEGER}
	</delete>
	<!--====================================== 删除结束-DELETE_FINISH ======================================-->
      
</mapper>