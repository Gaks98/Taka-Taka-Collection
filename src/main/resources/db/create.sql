SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS estate (
    id serial PRIMARY KEY auto_increment,
     name VARCHAR,
     location VARCHAR,
     collector_id INTEGER

);