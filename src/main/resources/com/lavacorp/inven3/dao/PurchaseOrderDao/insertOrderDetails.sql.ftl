INSERT INTO PurchaseOrder(status, supplier_id, purchase_date, target_date, arrived_date, reference)
VALUES (:status, :supplier.id, :purchaseDate, :targetDate, :arrivedDate, :reference);