<#include "common.ftl">

<#if count?? && count?? == true>
    SELECT COUNT(*) FROM (
        SELECT SOR.id
<#else>
     SELECT
        SOR.id                     AS id,
        SOR.status                 AS status,

        SO.id                      AS order_id,

        SO.sales_date              AS order_sales_date,
        SO.shipment_date           AS order_shipment_date,
        SO.arrived_date            AS order_arrived_date,
        SO.status                  AS order_status,
        SO.reference               AS order_reference,

        Stock.id                   AS order_stock_id,

        Item.id                    AS order_stock_item_id,
        Item.name                  AS order_stock_item_name,
        Item.description           AS order_stock_item_description,
        Item.base_price            AS order_stock_item_base_price,
        Item.unit                  AS order_stock_item_unit,
        Item.created_at            AS order_stock_item_created_at,
        Item.last_updated_at       AS order_stock_item_last_created_at,

        ItemCategory.id            AS order_stock_item_category_id,
        ItemCategory.name          AS order_stock_item_category_name,
        ItemCategory.description   AS order_stock_item_category_description,

        StockSupplier.id           AS order_stock_supplier_id,
        StockSupplier.name         AS order_stock_supplier_name,
        StockSupplier.email        AS order_stock_supplier_address,
        StockSupplier.phone_number AS order_stock_supplier_phone_number,
        StockSupplier.email        AS order_stock_supplier_email,

        Location.id                AS order_stock_location_id,
        Location.name              AS order_stock_location_name,
        Location.description       AS order_stock_location_description,

        Stock.quantity             AS order_stock_quantity,
        Stock.status               AS order_stock_status,
        Stock.expiry_date          AS order_stock_expiry_date,
        Stock.notes                AS order_stock_notes,
        Stock.created_at           AS order_stock_created_at,
        Stock.last_updated_at      AS order_stock_last_updated_at,

        POL.order_quantity         AS order_order_quantity,

        SOR.return_date            AS return_date,
        SOR.reference              AS reference
</#if>

FROM SalesOrderReturn SOR
     JOIN SalesOrder SO ON SOR.order_id = SO.id
     LEFT JOIN SalesOrderLine POL ON SO.id = POL.sales_order_id
     LEFT JOIN Stock ON POL.stock_id = Stock.id
     LEFT JOIN Item ON Stock.item_id = Item.id
     LEFT JOIN Category ItemCategory ON Item.category_id = ItemCategory.id
     LEFT JOIN Supplier StockSupplier ON Stock.supplier_id = StockSupplier.id
     LEFT JOIN Location ON Stock.location_id = Location.id

<#if id?? || status?? || referenceOrderId??>
    WHERE
    <#if id??>
        <@filter_id table=table_name operator="=" id=idVar/>
    <#elseif status??>
        SOR.status = :status
    <#elseif referenceOrderId??>
        SO.id = :referenceOrderId
    </#if>
</#if>

<#if count?? && count?? == true>
GROUP BY POR.id)
</#if>

<#if orderColumn?? && orderDirection??>
    ORDER BY ${orderColumn} ${orderDirection}
</#if>

<#if page?? && pageSize??>
    <@paginate table=table_name page=page page_size=pageSize/>
</#if>