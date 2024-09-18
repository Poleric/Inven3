UPDATE Stock
SET
    item_id = :item.id,
    supplier_id = :supplier.id,
    location_id = :location.id,
    quantity = :quantity,
    status = :status,
    expiry_date = :expiryDate,
    notes = :notes
WHERE
    id = :id;