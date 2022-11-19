CREATE TABLE IF NOT EXISTS "users"
(
    user_id SERIAL PRIMARY KEY NOT NULL,
    email   varchar(30)        NOT NULL,
    name    varchar(20)        NOT NULL,
    CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS "categories"
(
    category_id SERIAL PRIMARY KEY NOT NULL,
    name        VARCHAR(30)        NOT NULL,
    CONSTRAINT UQ_CATEGORY_NAME UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS "compilations"
(
    compilation_id SERIAL PRIMARY KEY NOT NULL,
    title          VARCHAR(128)       NOT NULL,
    pinned         BOOLEAN DEFAULT FALSE
);