SET MODE PostgreSQL;


CREATE TABLE IF NOT EXISTS estate (
    id serial PRIMARY KEY auto_increment,
     name VARCHAR,
     location VARCHAR,
     collector_id INTEGER

);

CREATE TABLE IF NOT EXISTS customers(
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 location VARCHAR,
 address VARCHAR,
 estateId INTEGER
);

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


