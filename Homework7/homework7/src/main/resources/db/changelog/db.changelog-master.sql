--liquibase formatted sql
--preconditions onFail:HALT onError:HALT

--changeset author:1
CREATE TABLE address (
    id BIGSERIAL PRIMARY KEY,
    city VARCHAR(255),
    street VARCHAR(255),
    zipcode VARCHAR(10)
);

--changeset author:2
CREATE TABLE weight (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    symbol VARCHAR(10),
    value NUMERIC(10,2)
);

--changeset author:3
CREATE TABLE quantity (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    symbol VARCHAR(10),
    value INTEGER
);

--changeset author:4
CREATE TABLE customer (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address_id BIGINT REFERENCES address(id)
);

--changeset author:5
CREATE TABLE item (
    id BIGSERIAL PRIMARY KEY,
    description TEXT,
    shipping_weight_id BIGINT REFERENCES weight(id)
);

--changeset author:6
CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    date TIMESTAMP,
    status VARCHAR(50),
    customer_id BIGINT REFERENCES customer(id)
);

--changeset author:7
CREATE TABLE payment (
    id BIGSERIAL PRIMARY KEY,
    amount REAL,
    order_id BIGINT REFERENCES orders(id)
);

--changeset author:8
CREATE TABLE order_detail (
    id BIGSERIAL PRIMARY KEY,
    tax_status VARCHAR(50),
    order_id BIGINT REFERENCES orders(id),
    item_id BIGINT REFERENCES item(id),
    quantity_id BIGINT REFERENCES quantity(id)
);

--changeset author:9
CREATE TABLE credit (
    payment_id BIGINT PRIMARY KEY REFERENCES payment(id),
    number VARCHAR(20),
    type VARCHAR(50),
    exp_date TIMESTAMP
);

--changeset author:10
CREATE TABLE cash (
    payment_id BIGINT PRIMARY KEY REFERENCES payment(id),
    cash_tendered REAL
);

--changeset author:11
CREATE TABLE payment_check (
    payment_id BIGINT PRIMARY KEY REFERENCES payment(id),
    name VARCHAR(100),
    bank_id VARCHAR(50)
);

--changeset author:12
CREATE TABLE order_detail_item (
    order_detail_id BIGINT REFERENCES order_detail(id),
    item_id BIGINT REFERENCES item(id),
    PRIMARY KEY (order_detail_id, item_id)
);