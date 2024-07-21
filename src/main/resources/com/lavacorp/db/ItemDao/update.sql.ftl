UPDATE Item
SET
    name = :name,
    description = :description,
    base_price = :basePrice
WHERE
    id = :id