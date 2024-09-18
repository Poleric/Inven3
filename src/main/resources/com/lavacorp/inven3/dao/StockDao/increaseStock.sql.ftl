UPDATE Stock
SET quantity = quantity + :quantity
WHERE id = :id;