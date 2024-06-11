CREATE TYPE PRODUCT_STATUS AS ENUM ('NOT_AVAILABLE', 'AVAILABLE', 'RESERVED', 'SOLD');

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL,
    description TEXT NOT NULL,
    is_infinite BOOLEAN NOT NULL,
    count INTEGER,
    is_endless BOOLEAN NOT NULL,
    end_date TIMESTAMP,
    price INTEGER NOT NULL,
    discount_price INTEGER,
    status PRODUCT_STATUS NOT NULL DEFAULT 'NOT_AVAILABLE'
);

INSERT INTO products (name, description, is_infinite, count, is_endless, end_date, price, discount_price, status)
VALUES
    ('7 Days Premium', 'Good boost', true, null, false, null, 790, null, 'AVAILABLE'),
    ('Season skin', 'Wow, it is so cool!', false, 250, true, '2024-09-15 00:00:00', 2990, null, 'AVAILABLE'),
    ('Season skin', 'Wow, it is so cool!', false, 25, true, '2024-09-15 00:00:00', 2990, null, 'RESERVED'),
    ('Season skin', 'Wow, it is so cool!', false, 25, true, '2024-09-15 00:00:00', 2990, null, 'SOLD'),
    ('Draft skin', 'IN PROCESS!!!', false, 1337, false, null, 9999, 1111, 'NOT_AVAILABLE');