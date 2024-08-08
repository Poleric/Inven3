SELECT
    stock.id as id,

    item.id as item_id,
    item.name as item_name,
    item.description as item_description,
    item.base_price as item_base_price,
    item.unit as item_unit,
    item.created_at as item_created_at,
    item.last_updated_at as item_last_updated_at,

    category.id as item_category_id,
    category.name as item_category_name,
    category.description as item_category_description,

    supplier.id as supplier_id,
    supplier.name as supplier_name,
    supplier.address as supplier_address,
    supplier.phone_number as supplier_phone_number,
    supplier.email as supplier_email,

    location.id as location_id,
    location.name as location_name,
    location.description as location_description,

    stock.quantity as quantity,
    stock.status as status,
    stock.expiry_date as expiry_date,
    stock.notes as notes,
    stock.created_at as created_at,
    stock.last_updated_at as last_updated_at
FROM Stock
    LEFT JOIN Item ON Stock.item_id = Item.id
    LEFT JOIN Category ON Item.category_id = Category.id
    LEFT JOIN Supplier ON Stock.supplier_id = Supplier.id
    LEFT JOIN Location ON Stock.location_id = Location.id
