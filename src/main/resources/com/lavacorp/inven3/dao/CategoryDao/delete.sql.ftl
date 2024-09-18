<#include "common.ftl">

DELETE FROM Category
<#if id?? || name??>
    WHERE
    <#if id??>
        <@filter_id table=table_name operator="=" id=idVar/>
    <#elseif name??>
        <@filter_name table=table_name name=nameVar/>
    </#if>
</#if>
