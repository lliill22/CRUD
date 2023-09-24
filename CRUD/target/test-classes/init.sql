DROP TABLE if exists customer;

CREATE TABLE IF NOT EXISTS customer (
    id          INT             IDENTITY PRIMARY KEY,
    fio         VARCHAR(255)    NOT NULL,
    phone       VARCHAR(255),
    address     VARCHAR(255),
    created     timestamp       DEFAULT now()
);

INSERT INTO customer (id, fio, phone, address, created) VALUES
(1, 'Bogdan', '898989898', 'school21', '2023-09-23 14:19:48');
INSERT INTO customer (id, fio, phone, address, created) VALUES
(2, 'Vova', '887873', 'school21', '2023-09-23 14:01:12');
INSERT INTO customer (id, fio, phone, address, created) VALUES
(3, 'Daria', '887889898', 'school21', '2023-09-23 14:02:14');
