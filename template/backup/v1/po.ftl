package ${poPackage};

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
/**============================== 自定义导入 开始 _@CAUTION_SELF_IMPORT_BEGIN@_ ==============================*/
${selfAddImportContent}
/**============================== 自定义导入 结束 _@CAUTION_SELF_IMPORT_FINISH@_ ==============================*/

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：${className} <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华  <p>
* <strong> 编写时间：</strong> 2017年1月6日15:16:08 <p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 4.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> ${tableComment} <p>
 */
public class ${className} extends Parent {

	/**============================== 自定义字段 开始 _@CAUTION_SELF_FIELD_BEGIN@_ ==============================*/
	${selfAddFieldContent}
	/**============================== 自定义字段 结束 _@CAUTION_SELF_FIELD_FINISH@_ ==============================*/

	<#list poColumnsMapList as cMap>
	/** ${cMap.fieldComment}  */
	<#if cMap.javaType == 'Date'>
	@DateTimeFormat(pattern = "${cMap.datePattern}")
	</#if>
	private ${cMap.javaType} ${cMap.fieldHumpName};
	
	</#list>
	
	<#list poColumnsMapList as cMap>
	public ${cMap.javaType} get${cMap.fieldHumpNameFirstUp}() {
		return ${cMap.fieldHumpName};
	}
	public void set${cMap.fieldHumpNameFirstUp}(${cMap.javaType} ${cMap.fieldHumpName}) {
		<#if cMap.javaType != 'String'>
		this.${cMap.fieldHumpName} = ${cMap.fieldHumpName};
		</#if>
		<#if cMap.javaType == 'String'>
		 this.${cMap.fieldHumpName} = ${cMap.fieldHumpName} == null ? null : ${cMap.fieldHumpName}.trim();
		</#if>
	}
	</#list>
	
	/**=========================== 自定义GETSET方法开始 _@CAUTION_SELF_GETSET_BEGIN@_ ===========================*/
	${selfAddGetSetContent}
	/**=========================== 自定义GETSET方法结束 _@CAUTION_SELF_GETSET_FINISH@_ ===========================*/
	
}