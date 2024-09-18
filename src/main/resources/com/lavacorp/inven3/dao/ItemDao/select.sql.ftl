<#include "common.ftl">

<#if count?? && count?? == true>
    SELECT count(*)
<#else>
    SELECT
        Item.id as id,
        Item.name as name,
        Item.description as description,
        Item.base_price as base_price,
        Item.unit as unit,
        Item.min_stock as min_stock,

        Category.id as category_id,
        Category.name as category_name,
        Category.description as category_description,

        Item.created_at as created_at,
        Item.last_updated_at as last_updated_at
</#if>

FROM Item
    LEFT JOIN Category ON Item.category_id = Category.id

<#if supplierId??>
    LEFT JOIN ItemSupplier ON Item.id = ItemSupplier.item_id
</#if>

<#if id?? || name?? || nameLike?? || supplierId?? || categoryId??>
    WHERE
    <#if id??>
        <@filter_id table=table_name operator="=" id=idVar/>
    <#elseif name??>
        <@filter_name table=table_name name=nameVar/>
    <#elseif nameLike??>
        <@filter_name_like table=table_name name_like=nameLikeVar/>
    <#elseif supplierId??>
        ItemSupplier.supplier_id = :supplierId
    <#elseif categoryId??>
        Category.id = :categoryId
    </#if>
</#if>

<#if orderColumn?? && orderDirection??>
    ORDER BY ${orderColumn} ${orderDirection}
</#if>

<#if page?? && pageSize??>
    <@paginate table=table_name page=page page_size=pageSize/>
</#if>
