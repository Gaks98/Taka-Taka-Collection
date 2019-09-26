SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS customers(
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 location VARCHAR,
 address VARCHAR,
 estateId INTEGER
);
CREATE TABLE IF NOT EXISTS estate_customers(

);