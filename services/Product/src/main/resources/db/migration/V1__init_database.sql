-- Create the category table
CREATE TABLE IF NOT EXISTS category (
    id SERIAL PRIMARY KEY, -- Use SERIAL for auto-incrementing primary keys
    description VARCHAR(255),
    name VARCHAR(255) NOT NULL -- Adding NOT NULL for important fields
);

-- Create the product table
CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY, -- Use SERIAL for auto-incrementing primary keys
    available_quantity DOUBLE PRECISION NOT NULL,
    description VARCHAR(255),
    name VARCHAR(255) NOT NULL, -- Adding NOT NULL for important fields
    price NUMERIC(38, 2),
    category_id INTEGER REFERENCES category(id) ON DELETE CASCADE -- Ensure foreign key constraints with cascading delete
);

-- Create sequences (optional if SERIAL is used, as SERIAL implicitly creates sequences)
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relkind = 'S' AND relname = 'category_seq') THEN
        CREATE SEQUENCE category_seq INCREMENT BY 50;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relkind = 'S' AND relname = 'product_seq') THEN
        CREATE SEQUENCE product_seq INCREMENT BY 50;
    END IF;
END $$;
