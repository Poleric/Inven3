SELECT
    item.id,
    item.name,
    item.description,
    item.base_price,
    item.unit,
    category.name,
    category.description,
    item.created_at,
    item.last_updated_at
FROM Item
    LEFT JOIN Category category ON Item.category_id = Category.id
