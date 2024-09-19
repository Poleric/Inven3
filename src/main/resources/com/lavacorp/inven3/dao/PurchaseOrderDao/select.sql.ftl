<#include "common.ftl">

<#if count?? && count?? == true>
    SELECT count(*)
<#else>
    SELECT
        PO.id                      AS id,

        PO.purchase_date           AS purchase_date,
        PO.target_date             AS target_date,
        PO.arrived_date            AS arrived_date,
        PO.status                  AS status,
        PO.reference               AS reference,

        OrderSupplier.id           AS supplier_id,
        OrderSupplier.name         AS supplier_name,
        OrderSupplier.address      AS supplier_address,
        OrderSupplier.phone_number AS supplier_phone_number,
        OrderSupplier.email        AS supplier_email,

        Stock.id                   AS stock_id,

        Item.id                    AS stock_item_id,
        Item.name                  AS stock_item_name,
        Item.description           AS stock_item_description,
        Item.base_price            AS stock_item_base_price,
        Item.unit                  AS stock_item_unit,
        Item.created_at            AS stock_item_created_at,
        Item.last_updated_at       AS stock_item_last_created_at,

        ItemCategory.id            AS stock_item_category_id,
        ItemCategory.name          AS stock_item_category_name,
        ItemCategory.description   AS stock_item_category_description,

        StockSupplier.id           AS stock_supplier_id,
        StockSupplier.name         AS stock_supplier_name,
        StockSupplier.email        AS stock_supplier_address,
        StockSupplier.phone_number AS stock_supplier_phone_number,
        StockSupplier.email        AS stock_supplier_email,

        Location.id                AS stock_location_id,
        Location.name              AS stock_location_name,
        Location.description       AS stock_location_description,

        Stock.quantity             AS stock_quantity,
        Stock.status               AS stock_status,
        Stock.expiry_date          AS stock_expiry_date,
        Stock.notes                AS stock_notes,
        Stock.created_at           AS stock_created_at,
        Stock.last_updated_at      AS stock_last_updated_at,

        POL.order_quantity         AS order_quantity
</#if>

FROM PurchaseOrder PO
     LEFT JOIN PurchaseOrderLine POL ON PO.id = POL.purchase_order_id
     LEFT JOIN Supplier OrderSupplier ON PO.supplier_id = OrderSupplier.id
     LEFT JOIN Stock ON POL.stock_id = Stock.id
     LEFT JOIN Item ON Stock.item_id = Item.id
     LEFT JOIN Category ItemCategory ON Item.category_id = ItemCategory.id
     LEFT JOIN Supplier StockSupplier ON Stock.supplier_id = StockSupplier.id
     LEFT JOIN Location ON Stock.location_id = Location.id

<#if id?? || status?? || itemId??  || orderSupplierId?? || stockId??>
    WHERE
    <#if id??>
        <@filter_id table=table_name operator="=" id=idVar/>
    <#elseif status??>
        PO.status = :status
    <#elseif itemId??>
        Item.id = :itemId
    <#elseif supplierId??>
        OrderSupplier.id = :orderSupplierId
    <#elseif stockId??>
        Stock.id = :stockId
    </#if>
</#if>

<#if orderColumn?? && orderDirection??>
    ORDER BY ${orderColumn} ${orderDirection}
</#if>

<#if page?? && pageSize??>
    <@paginate table=table_name page=page page_size=pageSize/>
</#if>