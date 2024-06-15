CREATE TYPE PRODUCT_STATUS AS ENUM ('NOT_AVAILABLE', 'AVAILABLE', 'RESERVED', 'SOLD');

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL,
    description TEXT NOT NULL,
    is_infinite BOOLEAN NOT NULL,
    count INTEGER,
    price INTEGER NOT NULL
);

INSERT INTO products (name, description, is_infinite, count, price)
VALUES
    ('7 Days Premium', 'Good boost', true, null, 790),
    ('Season skin', 'Wow, it is so cool!', false, 250, 2990),
    ('Helm', 'Super duper helm', false, 1337, 1111);