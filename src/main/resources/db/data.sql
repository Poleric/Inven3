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
       ('Laptop'),
       ('Smartphone'),
       ('Console'),
       ('Smartwatch')
ON CONFLICT (name) DO NOTHING;

INSERT INTO Item (name, description, base_price, unit, category_id)
VALUES ('Samseng FHD Smart Television', 'The first model, black television from the year 2023, equipped with Full HD technology.', 4000, 'pcs', (SELECT id FROM Category WHERE name = 'Television')),
       ('Samseng SmartBooK', 'The second model, white laptop from the year 2021, designed for studying purposes.', 3000.00, 'pcs', (SELECT id FROM Category WHERE name = 'Laptop')),
       ('Samseng Universe S23 Ultra', 'The first and only model, pink smartphone from the year 2022, featuring high-end specs and bigger size.', 5000.00, 'pcs', (SELECT id FROM Category WHERE name = 'Smartphone')),
       ('Samseng SmartFridge', 'The fifth model, silver smart fridge from the year 2023.', 4000.00, 'pcs', (SELECT id FROM Category WHERE name = 'Console')),
       ('Samseng SmartWatch', 'The second model, black smart watch from the year 2020.', 500.00, 'pcs', (SELECT id FROM Category WHERE name = 'Smartwatch'))
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
       ('Logitek', 'Lausanne, Switzerland', '+1 646-454-3200', 'support@logitek.com')
ON CONFLICT (name) DO NOTHING;

INSERT INTO ItemSupplier (item_id, supplier_id, sold_price)
VALUES (1, 1, 3500.00),
       (2, 1, 2700.00),
       (3, 1, 4800.00),
       (4, 1, 3000.00),
       (5, 1, 450.00)
ON CONFLICT (item_id, supplier_id) DO NOTHING;

INSERT INTO Stock (id, item_id, supplier_id, location_id, quantity, status)
VALUES (1, 1, 1, 2, 3, 'OK'),
       (2, 2, 1, 4, 5, 'OK'),
       (3, 2, 1, 1, 20, 'OK'),
       (4, 4, 1, 6, 2, 'OK')
ON CONFLICT (id) DO NOTHING;