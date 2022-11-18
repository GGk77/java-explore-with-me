CREATE TABLE IF NOT EXISTS "users"
(
    "id"    SERIAL PRIMARY KEY NOT NULL,
    "email" varchar(30)  NOT NULL,
    "name"  varchar(20) NOT NULL
);