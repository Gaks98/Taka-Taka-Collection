SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS collectors (
    id int PRIMARY KEY  auto_increment,
    firmName VARCHAR,
    estate VARCHAR,
    feeCharge INTEGER,
    operationDay VARCHAR,
    disposalMode VARCHAR,
    recyclingSite VARCHAR,
    customersNumber INTEGER
);

