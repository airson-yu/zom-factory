package ${poPackage};

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;

/**
* <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层对象：${className} <p>
* <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华-airson <p>
* <strong> 编写时间：</strong> 2018年8月1日09:00:00 <p>
* <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都零壹众科技有限公司 http://www.01more.com <p>
* <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 10.0 <p>
* <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> ${tableComment} <p>
 */
public class ${className} {

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
	
}