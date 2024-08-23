INSERT INTO Stock(item_id, supplier_id, location_id, quantity, status, expiry_date, notes, created_at)
VALUES (:item.id, :supplier.id, :location.id, :quantity, :status, :expiryDate, :notes, :createdAt);
