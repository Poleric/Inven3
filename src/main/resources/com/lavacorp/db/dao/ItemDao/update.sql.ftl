UPDATE Item
SET
    name = :name,
    description = :description,
    base_price = :basePrice,
    unit = :unit,
    category_id = :category.id
WHERE
    id = :id