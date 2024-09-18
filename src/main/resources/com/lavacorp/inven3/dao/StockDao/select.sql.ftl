<#include "common.ftl">

<#if count?? && count?? == true>
    SELECT count(*)
<#else>
    SELECT
        Stock.id as id,

        Item.id as item_id,
        Item.name as item_name,
        Item.description as item_description,
        Item.base_price as item_base_price,
        Item.unit as item_unit,
        Item.created_at as item_created_at,
        Item.last_updated_at as item_last_updated_at,

        category.id as item_category_id,
        category.name as item_category_name,
        category.description as item_category_description,

        Supplier.id as supplier_id,
        Supplier.name as supplier_name,
        Supplier.address as supplier_address,
        Supplier.phone_number as supplier_phone_number,
        Supplier.email as supplier_email,

        Location.id as location_id,
        Location.name as location_name,
        Location.description as location_description,

        Stock.quantity as quantity,
        Stock.status as status,
        Stock.expiry_date as expiry_date,
        Stock.notes as notes,
        Stock.created_at as created_at,
        Stock.last_updated_at as last_updated_at
</#if>

FROM Stock
    LEFT JOIN Item ON Stock.item_id = Item.id
    LEFT JOIN Category ON Item.category_id = Category.id
    LEFT JOIN Supplier ON Stock.supplier_id = Supplier.id
    LEFT JOIN Location ON Stock.location_id = Location.id

<#if id?? || name?? || nameLike?? || itemId?? || supplierId??>
    WHERE
    <#if id??>
        <@filter_id table=table_name operator="=" id=idVar/>
    <#elseif name??>
        <@filter_name table=table_name name=nameVar/>
    <#elseif nameLike??>
        <@filter_name_like table=table_name name_like=nameLikeVar/>
    <#elseif itemId??>
        Item.id = :itemId
    <#elseif supplierId??>
        Supplier.id = :supplierId
    </#if>
</#if>

<#if orderColumn?? && orderDirection??>
    ORDER BY ${orderColumn} ${orderDirection}
</#if>

<#if page?? && pageSize??>
    <@paginate table=table_name page=page page_size=pageSize/>
</#if>