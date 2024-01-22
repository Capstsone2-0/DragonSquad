DROP DATABASE IF EXISTS AccountingLedger;

CREATE DATABASE IF NOT EXISTS AccountingLedger;

USE AccountingLedger;

CREATE TABLE transactions (
    id INT NOT NULL AUTO_INCREMENT,
    date DATE NOT NULL,
    time VARCHAR(50) NOT NULL,
    description VARCHAR(100) NOT NULL,
    vendor VARCHAR(50) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO transactions(date, time, description, vendor, amount)
VALUES
    ('2023-04-15', '10:13:25', 'ergonomic keyboard', 'Amazon', 89.5),
    ('2013-04-15', '10:13:25', 'ergonomic keyboard', 'Amazon', 89.5),
    ('2024-01-05', '09:20:15', 'shoes', 'Target', 20.0),
    ('2023-04-15', '11:15:23', 'invoice 1001 paid', 'Joe', -1500.0),
    ('2023-05-17', '12:04:12', 'headphones', 'Macys', 50.99),
    ('2023-03-13', '03:10:55', 'scented candles', 'Bath & Body Works', 7.8),
    ('2022-05-13', '12:03:12', 'invoice 2345 paid', 'Joe', -40.78),
    ('2023-12-23', '12:23:12', 'PP', 'SS', 23.0);
