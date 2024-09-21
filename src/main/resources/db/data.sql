INSERT INTO Users (name, hashed_password, role)
VALUES ('Hong Jun', '$2a$12$.xLkXP3/oGa/VGghBFgqEuJ6vw1rMOpLki2GvKjjKNBZm.98wmkUW', 'ROLE_STAFF'),
       ('Mingy', '$2a$12$nsO1aC8vCN1xotUrqXHiHOA8J6ctxBkDuT6334ISae46YqA1/8zuS', 'ROLE_ADMIN'),
       ('dizzynight', '$2a$12$3hKYBYxw/2PuD8CcGr.q7.gcauEhKedNFDBnSdjuMdzn/AxqAAqBm', 'ROLE_ADMIN')
ON CONFLICT (name) DO NOTHING;

INSERT INTO Category (name)
VALUES ('CPU'),
       ('Graphics card'),
       ('SSD'),
       ('Pendrive'),
       ('Charging cables'),
       ('Mouse'),
       ('Mechanical Keyboard'),
       ('Membrane Keyboard'),
       ('Television'),
       ('Laptop'),
       ('Smartphone'),
       ('Console'),
       ('Smartwatch')
ON CONFLICT (name) DO NOTHING;

INSERT INTO Item (name, description, base_price, unit, category_id, min_stock)
VALUES ('Samseng FHD Smart Television', 'The first model, black television from the year 2023, equipped with Full HD technology.', 4000, 'pcs', (SELECT id FROM Category WHERE name = 'Television'), 5),
       ('Samseng SmartBooK', 'The second model, white laptop from the year 2021, designed for studying purposes.', 3000.00, 'pcs', (SELECT id FROM Category WHERE name = 'Laptop'), 5),
       ('Samseng Universe S23 Ultra', 'The first and only model, pink smartphone from the year 2022, featuring high-end specs and bigger size.', 5000.00, 'pcs', (SELECT id FROM Category WHERE name = 'Smartphone'), 5),
       ('Samseng SmartFridge', 'The fifth model, silver smart fridge from the year 2023.', 4000.00, 'pcs', (SELECT id FROM Category WHERE name = 'Console'), 5),
       ('Samseng SmartWatch', 'The second model, black smart watch from the year 2020.', 500.00, 'pcs', (SELECT id FROM Category WHERE name = 'Smartwatch'), 5),
       ('Nvisaya RTX 6700', 'The 3 model of the NVISAYA 6000 RTX series with the most cutting edge of technologies', 3500.00, 'pcs', (SELECT id FROM Category WHERE name = 'Graphics card'), 5),
       ('Nvisaya RTX 6800', 'The 4 model of the NVISAYA 6000 RTX series with the most cutting edge of technologies', 4800.00, 'pcs', (SELECT id FROM Category WHERE name = 'Graphics card'), 5),
       ('Nvisaya RTX 6900', 'The 5 model of the NVISAYA 6000 RTX series with the most cutting edge of technologies', 6000.00, 'pcs', (SELECT id FROM Category WHERE name = 'Graphics card'), 5),
       ('Outel Apple Lake 16700K', 'Outel Gen 16 ultra-high performance CPU with a clock speed of 20GHz and 16 cores.', 4000.00, 'pcs', (SELECT id FROM Category WHERE name = 'CPU'), 5)
ON CONFLICT (name) DO NOTHING;

INSERT INTO Location (name)
VALUES ('Storage'),
       ('Aisle 1'),
       ('Aisle 2'),
       ('Aisle 3'),
       ('Aisle 4'),
       ('Aisle 5'),
       ('Counter')
ON CONFLICT (name) DO NOTHING;

INSERT INTO Supplier (name, address, phone_number, email)
VALUES ('Samseng', 'Samseng Factory, Road Samseng', '0111 1111 1111', 'support@samseng.com'),
       ('Logitek', 'Lausanne, Switzerland', '+1 646-454-3200', 'support@logitek.com'),
       ('Nvisaya', 'Santa Clara, California, United States', '+1 (408) 486-2000', 'info@nvidia.com'),
       ('Outel', 'Santa Clara, California, United States', '916-377-7000', ''),
       ('AMD', 'Santa Clara, California, United States', ' 877-284-1566', 'info@nvidia.com')
ON CONFLICT (name) DO NOTHING;

INSERT INTO ItemSupplier (item_id, supplier_id, sold_price)
VALUES (1, 1, 3500.00),
       (2, 1, 2700.00),
       (3, 1, 4800.00),
       (4, 1, 3000.00),
       (5, 1, 450.00),
       (6, 3, 3000.00),
       (7, 3, 4500.00),
       (8, 3, 5500.00)
ON CONFLICT (item_id, supplier_id) DO NOTHING;

INSERT INTO Stock (id, item_id, supplier_id, location_id, quantity, status)
VALUES (1, 1, 1, 2, 3, 'OK'),
       (2, 2, 1, 4, 5, 'OK'),
       (3, 2, 1, 1, 20, 'OK'),
       (4, 4, 1, 6, 2, 'OK'),
       (5, 6, 3, 6, 10, 'IN_TRANSIT'),
       (6, 7, 3, 6, 10, 'IN_TRANSIT'),
       (7, 7, 3, 6, 20, 'IN_TRANSIT'),
       (8, 9, 4, 6, 20, 'IN_TRANSIT'),
       (9, 9, 4, 6, 10, 'IN_TRANSIT'),
       (10, 2, 1, 6, 6, 'RETURNED'),
       (11, 3, 1, 6, 5, 'RETURNED')
ON CONFLICT (id) DO NOTHING;

INSERT INTO PurchaseOrder (id, status, supplier_id, purchase_date, target_date, arrived_date)
VALUES (1, 'FULFILLED', 1, NOW(), NOW(), NOW()),
       (2, 'FULFILLED', 1, NOW(), NOW(), NOW()),
       (3, 'IN_TRANSIT', 3, NOW(), NOW(), NULL),
       (4, 'IN_TRANSIT', 3, NOW(), NOW(), NULL),
       (5, 'IN_TRANSIT', 4, NOW(), NOW(), NULL),
       (6, 'REFUNDED', 1, NOW(), NOW(), NULL)
ON CONFLICT (id) DO NOTHING;

INSERT INTO PurchaseOrderLine(purchase_order_id, stock_id, order_quantity)
VALUES (1, 1, 5),
       (1, 2, 10),
       (1, 3, 50),
       (2, 4, 5),
       (3, 5, 10),
       (3, 6, 10),
       (4, 7, 20),
       (5, 8, 20),
       (5, 9, 10),
       (6, 10, 6),
       (6, 11, 5)
ON CONFLICT (purchase_order_id, stock_id) DO NOTHING;

INSERT INTO PurchaseOrderReturn (id, status, order_id)
VALUES (1, 'IN_TRANSIT', 6)
ON CONFLICT (id) DO NOTHING;

INSERT INTO SalesOrder(id ,status, sales_date, shipment_date, arrived_date)
VALUES (1, 'FULFILLED', NOW(), NOW(), NOW()),
       (2, 'IN_TRANSIT', NOW(), NOW(), NOW()),
       (3, 'PENDING', NOW(), NOW(), null)
ON CONFLICT (id) DO NOTHING ;

INSERT INTO SalesOrderLine(sales_order_id, stock_id, order_quantity)
VALUES (1, 1, 2),
       (1, 2, 5),
       (2, 3, 30),
       (2, 4, 3),
       (3, 1, 3),
       (3, 3, 3),
       (3,4, 2)
ON CONFLICT (sales_order_id, stock_id) DO NOTHING;
