-- Create tables
CREATE TABLE ec_user (
    id_user UUID PRIMARY KEY NOT NULL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_type VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE client (
    id_client UUID PRIMARY KEY NOT NULL,
    fk_user UUID REFERENCES ec_user(id_user) NOT NULL,
    client_name VARCHAR(255) NOT NULL,
    contact VARCHAR(50),
    address TEXT,
    status BOOLEAN DEFAULT TRUE,
    created_by UUID REFERENCES ec_user(id_user) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by UUID REFERENCES ec_user(id_user) NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE product (
    id_product UUID PRIMARY KEY NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock_quantity INT NOT NULL,
    created_by UUID REFERENCES ec_user(id_user) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by UUID REFERENCES ec_user(id_user) NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE order_table (
    id_order UUID PRIMARY KEY NOT NULL,
    fk_client UUID REFERENCES client(id_client),
    status VARCHAR(50) NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10, 2),
    created_by UUID REFERENCES ec_user(id_user) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by UUID REFERENCES ec_user(id_user) NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE order_item (
    id_order_item UUID PRIMARY KEY NOT NULL,
    fk_order UUID NOT NULL REFERENCES order_table(id_order),
    fk_product UUID NOT NULL REFERENCES product(id_product),
    quantity INT NOT NULL CHECK (quantity > 0),
    price_per_unit DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    created_by UUID REFERENCES ec_user(id_user) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by UUID REFERENCES ec_user(id_user) NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create sales report view
CREATE OR REPLACE VIEW sales_report AS
SELECT
    o.id_order AS order_id,
    DATE_TRUNC('day', o.order_date) AS period,
    SUM(oi.quantity * oi.price_per_unit) AS total_sales,
    SUM(oi.quantity) AS products_sold,
    o.status,
    pr.id_product,
    pr.product_name,
    SUM(oi.quantity) AS quantity_sold,
    SUM(oi.subtotal) AS revenue_per_product
FROM
    order_table o
JOIN
    order_item oi ON o.id_order = oi.fk_order
JOIN
    product pr ON oi.fk_product = pr.id_product
GROUP BY
    o.id_order, period, pr.id_product, pr.product_name;
