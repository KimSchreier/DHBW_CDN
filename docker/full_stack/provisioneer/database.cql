CREATE KEYSPACE IF NOT EXISTS shop WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '3'}  AND durable_writes = true;

CREATE TABLE IF NOT EXISTS shop.users (
    id uuid,
    name text,
    login text,
    password text,
    roles list<text>,
    PRIMARY KEY (id, login)
);

CREATE TABLE IF NOT EXISTS shop.cart (
    id uuid,
    user uuid,
    products list<uuid>,
    PRIMARY KEY (id, user)
);

CREATE TABLE IF NOT EXISTS shop.product (
    id uuid,
    category text,
    price double,
    titel text,
    text text,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS shop.purches (
    id uuid,
    creation_date timestamp,
    user uuid,
    products list<uuid>,
    PRIMARY KEY (id)
);

CREATE INDEX on shop.purches (user);

