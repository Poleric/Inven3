INSERT INTO Users (id, name, hashed_password, role)
VALUES (1, 'Hong Jun', '$2a$12$.xLkXP3/oGa/VGghBFgqEuJ6vw1rMOpLki2GvKjjKNBZm.98wmkUW', 'ROLE_STAFF'),
       (2, 'Mingy', '$2a$12$nsO1aC8vCN1xotUrqXHiHOA8J6ctxBkDuT6334ISae46YqA1/8zuS', 'ROLE_ADMIN'),
       (3, 'dizzynight', '$2a$12$3hKYBYxw/2PuD8CcGr.q7.gcauEhKedNFDBnSdjuMdzn/AxqAAqBm', 'ROLE_ADMIN')
ON CONFLICT (name) DO NOTHING;

INSERT INTO Category (id, name)
VALUES (1, 'CPU'),
       (2, 'Graphics card'),
       (3, 'SSD'),
       (4, 'Pendrive'),
       (5, 'Charging cables'),
       (6, 'Mouse'),
       (7, 'Mechanical Keyboard'),
       (8, 'Membrane Keyboard'),
       (9, 'Laptop'),
       (10, 'Smartphone'),
       (11, 'Console'),
       (12, 'Smartwatch')
ON CONFLICT (name) DO NOTHING;

INSERT INTO Item (id, name, description, base_price, unit, category_id)
VALUES (1, 'Samseng FHD Smart Television', 'The first model, black television from the year 2023, equipped with Full HD technology.', 4000, 'pcs', (SELECT id FROM Category WHERE name = 'Television')),
       (2, 'Samseng SmartBooK', 'The second model, white laptop from the year 2021, designed for studying purposes.', 3000.00, 'pcs', (SELECT id FROM Category WHERE name = 'Laptop')),
       (3, 'Samseng Universe S23 Ultra', 'The first and only model, pink smartphone from the year 2022, featuring high-end specs and bigger size.', 5000.00, 'pcs', (SELECT id FROM Category WHERE name = 'Smartphone')),
       (4, 'Samseng SmartFridge', 'The fifth model, silver smart fridge from the year 2023.', 4000.00, 'pcs', (SELECT id FROM Category WHERE name = 'Console')),
       (5, 'Samseng SmartWatch', 'The second model, black smart watch from the year 2020.', 500.00, 'pcs', (SELECT id FROM Category WHERE name = 'Smartwatch')),
       (6, 'Nvisaya RTX 6700', 'The 3 model of the NVISAYA 6000 RTX series with the most cutting edge of technologies', 3500.00, 'pcs', (SELECT id FROM Category WHERE name = 'Graphics Card')),
       (7, 'Nvisaya RTX 6800', 'The 4 model of the NVISAYA 6000 RTX series with the most cutting edge of technologies', 4800.00, 'pcs', (SELECT id FROM Category WHERE name = 'Graphics Card')),
       (8, 'Nvisaya RTX 6900', 'The 5 model of the NVISAYA 6000 RTX series with the most cutting edge of technologies', 6000.00, 'pcs', (SELECT id FROM Category WHERE name = 'Graphics Card')),
       (9, 'Outel Apple Lake 16700K', 'Outel Gen 16 ultra-high performance CPU with a clock speed of 20GHz and 16 cores.', 4000.00, 'pcs', (SELECT id FROM Category WHERE name = 'CPU'))
ON CONFLICT (name) DO NOTHING;

INSERT INTO Location (id, name)
VALUES (1, 'Storage'),
       (2, 'Aisle 1'),
       (3, 'Aisle 2'),
       (4, 'Aisle 3'),
       (5, 'Aisle 4'),
       (6, 'Aisle 5'),
       (7, 'Counter')
ON CONFLICT (name) DO NOTHING;

INSERT INTO Supplier (id, name, address, phone_number, email)
VALUES (1, 'Samseng', 'Samseng Factory, Road Samseng', '0111 1111 1111', 'support@samseng.com'),
       (2, 'Logitek', 'Lausanne, Switzerland', '+1 646-454-3200', 'support@logitek.com'),
       (3, 'Nvisaya', 'Santa Clara, California, United States', '+1 (408) 486-2000', 'info@nvidia.com'),
       (4, 'Outel', 'Santa Clara, California, United States', '916-377-7000', ''),
       (5, 'AMD', 'Santa Clara, California, United States', ' 877-284-1566', 'info@nvidia.com')
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
       (4, 4, 1, 6, 2, 'OK')
ON CONFLICT (id) DO NOTHING;