SELECT
    item.id as id,
    item.name as name,
    item.description as description,
    item.base_price as base_price,
    item.unit as unit,

    category.id as category_id,
    category.name as category_name,
    category.description as category_description,
    
    item.created_at as created_at,
    item.last_updated_at as last_updated_at
FROM Item
    LEFT JOIN Category category ON Item.category_id = Category.id
