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

INSERT INTO shop.product (id, category, price, titel, text)
    VALUES(2de85368-016d-11e6-8d22-5e5517507c66,'test', 12.00, 'iBuggy', 'Mit diesem Produkt...');

INSERT INTO shop.product (id, category, price, titel, text)
    VALUES(2de8523c-016d-11e6-8d22-5e5517507c66,'test', 512.00, 'Herpes Detektor ox', 'Mit diesem Produkt...');

INSERT INTO shop.product (id, category, price, titel, text)
    VALUES(2de84f80-016d-11e6-8d22-5e5517507c66,'test', 5.00, 'Zitroneneis', 'Hmmmmmmm');
