<#include "common.ftl">

DELETE FROM Stock
<#if id??>
    WHERE
    <#if id??>
        <@filter_id table=table_name operator="=" id=idVar/>
    </#if>
</#if>