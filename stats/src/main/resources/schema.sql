CREATE TABLE IF NOT EXISTS stats
(
    id        SERIAL PRIMARY KEY NOT NULL,
    app       VARCHAR(50)        NOT NULL,
    uri       VARCHAR(1000)      NOT NULL,
    ip        VARCHAR(500)       NOT NULL,
    timestamp TIMESTAMP          NOT NULL
);