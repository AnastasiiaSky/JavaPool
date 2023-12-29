DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS "User";


CREATE TABLE IF NOT EXISTS Product (
    id                  INTEGER PRIMARY KEY NOT NULL,
    name                VARCHAR(250)        NOT NULL,
    price               FLOAT               NOT NULL
);

CREATE TABLE IF NOT EXISTS "User" (
  id             SERIAL PRIMARY KEY,
  login          TEXT             NOT NULL,
  "password"     TEXT             NOT NULL,
  authenticated  BOOLEAN          NOT NULL,
);

