1 = 1
<#if id??>OR id = :id</#if>
<#if name??>OR name = :name</#if>

<#if ids??>OR id IN (<#list ids as id>${id}<#sep>, </#list>)</#if>

<#if nameLike??>OR name LIKE nameLike</#if>