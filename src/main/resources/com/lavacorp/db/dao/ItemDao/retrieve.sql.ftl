SELECT
    item.id as item_id,
    item.name as item_name,
    item.description as item_description,
    item.base_price as item_base_price,
    item.unit as item_unit,
    category.id as category_id,
    category.name as category_name,
    category.description as category_description,
    item.created_at as item_created_at,
    item.last_updated_at as item_last_updated_at
FROM Item
    LEFT JOIN Category category ON Item.category_id = Category.id
