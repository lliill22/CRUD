--DROP SCHEMA if exists tests cascade;
--DROP TABLE if exists tests.customer;

--CREATE SCHEMA if not exists tests;

CREATE TABLE IF NOT EXISTS customer (
    id          INT   PRIMARY KEY,
    fio         VARCHAR(255)        NOT NULL,
    phone       VARCHAR(255),
    address     VARCHAR(255),
    created     timestamp   DEFAULT now()
);

--SELECT * FROM customer;

INSERT INTO customer (fio, phone, address, created) values
('Bogdan', '898989898','school21', '2023-09-23 14:19:48'),
('Vova', '887873','school21', '2023-09-23 14:01:12'),
('Daria', '887889898','school21', '2023-09-23 14:02:14');