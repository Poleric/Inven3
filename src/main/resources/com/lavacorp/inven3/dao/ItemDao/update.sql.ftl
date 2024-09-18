UPDATE Item
SET
    name = :name,
    description = :description,
    base_price = :basePrice,
    unit = :unit,
    category_id = :category.id,
    min_stock = :minStock
WHERE
    id = :id;