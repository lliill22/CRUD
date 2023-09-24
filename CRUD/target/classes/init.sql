CREATE TABLE customer(
    id          BIGSERIAL   PRIMARY KEY,
    fio         TEXT        NOT NULL,
    phone       TEXT,
    address     TEXT,
    created     timestamp   DEFAULT now()
);

SELECT * FROM customer;

INSERT INTO customer (fio, phone, address, created) values
("Bogdan", "898989898","school21", "2023 Sep 23 14:19:48"),
("Vova", "887873","school22", "2023 Sep 23 14:01:12"),
("Daria", "887889898","school21", "2023 Sep 23 14:02:14")