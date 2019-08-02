package ${poPackage};

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * @author yuronghua-airson
 * @description PO: ${className}
 * @template 2019.08.02 v11.0
 * @organization Zero One More, Inc. http://www.01more.com
 * @remark ${tableComment}
 * @time ${ts}
 */
@Data
public class ${className} {

	<#list poColumnsMapList as cMap>
	/**
	 * ${cMap.fieldComment}
	 */
	<#if cMap.javaType == 'Date'>
	@DateTimeFormat(pattern = "${cMap.datePattern}")
	</#if>
	private ${cMap.javaType} ${cMap.fieldHumpName};
	
	</#list>
	
	<#--<#list poColumnsMapList as cMap>
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
	</#list>-->
	
}