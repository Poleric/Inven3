SELECT SUM(quantity)
FROM Stock
WHERE status = 'OK' AND item_id = :itemId