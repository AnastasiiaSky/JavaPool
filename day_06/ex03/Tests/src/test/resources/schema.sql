DROP TABLE IF EXISTS Product;

CREATE TABLE IF NOT EXISTS Product (
    id                  INTEGER PRIMARY KEY NOT NULL,
    name                VARCHAR(250)        NOT NULL,
    price               FLOAT               NOT NULL
);


