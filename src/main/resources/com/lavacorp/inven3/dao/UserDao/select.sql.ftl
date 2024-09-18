<#include "common.ftl">

<#if count?? && count?? == true>
    SELECT count(*)
<#else>
    SELECT
        id,
        name,
        hashed_password,
        role
</#if>

FROM Users

<#if id?? || name?? || nameLike?? || role??>
    WHERE
    <#if id??>
        <@filter_id table=table_name operator="=" id=idVar/>
    <#elseif name??>
        <@filter_name table=table_name name=nameVar/>
    <#elseif nameLike??>
        <@filter_name_like table=table_name name_like=nameLikeVar/>
    <#elseif role??>
        role = :role
    </#if>
</#if>

<#if orderColumn?? && orderDirection??>
    ORDER BY ${orderColumn} ${orderDirection}
</#if>

<#if page?? && pageSize??>
    <@paginate table=table_name page=page page_size=pageSize/>
</#if>