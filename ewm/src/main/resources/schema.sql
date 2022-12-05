CREATE TABLE IF NOT EXISTS "users"
(
    user_id SERIAL PRIMARY KEY NOT NULL,
    email   varchar(64)        NOT NULL,
    name    varchar(512)       NOT NULL,
    CONSTRAINT UQ_USER_EMAIL UNIQUE (email),
    CONSTRAINT UQ_USER_NAME UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS "categories"
(
    category_id SERIAL PRIMARY KEY NOT NULL,
    name        VARCHAR(512)       NOT NULL,
    CONSTRAINT UQ_CATEGORY_NAME UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS "compilations"
(
    compilation_id SERIAL PRIMARY KEY NOT NULL,
    title          VARCHAR(512)       NOT NULL,
    pinned         BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS "events"
(
    event_id          SERIAL PRIMARY KEY                                         NOT NULL,
    annotation        VARCHAR(10000)                                             NOT NULL,
    title             VARCHAR(5000)                                              NOT NULL,
    description       VARCHAR(10000)                                             NOT NULL,
    state             text CHECK (state IN ('PUBLISHED', 'PENDING', 'CANCELED')) NOT NULL,
    created           TIMESTAMP                                                           DEFAULT NOW(),
    published         TIMESTAMP,
    start_date        TIMESTAMP                                                  NOT NULL,
    initiator_id      INTEGER                                                    NOT NULL,
    lat               NUMERIC(6, 2)                                              NOT NULL,
    lon               NUMERIC(6, 2)                                              NOT NULL,
    category_id       INTEGER                                                    NOT NULL,
    paid              BOOLEAN                                                             DEFAULT FALSE,
    moderation        BOOLEAN                                                             DEFAULT FALSE,
    participant_limit INTEGER                                                    NOT NULL DEFAULT 0,
    CONSTRAINT fk_initiator FOREIGN KEY (initiator_id) REFERENCES users (user_id) ON DELETE CASCADE,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories (category_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS "requests"
(
    request_id SERIAL PRIMARY KEY NOT NULL,
    event_id   INTEGER            NOT NULL,
    user_id    INTEGER            NOT NULL,
    status     VARCHAR(100)       NOT NULL,
    created    TIMESTAMP DEFAULT NOW(),
    CONSTRAINT fk_event_request FOREIGN KEY (event_id) REFERENCES events (event_id) ON DELETE CASCADE,
    CONSTRAINT fk_user_request FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "events_compilations"
(
    event_compilation_id SERIAL PRIMARY KEY NOT NULL,
    event_id             INTEGER            NOT NULL,
    compilation_id       INTEGER            NOT NULL,
    CONSTRAINT fk_compilation_event FOREIGN KEY (event_id) REFERENCES events (event_id) ON DELETE CASCADE,
    CONSTRAINT fk_event_compilation FOREIGN KEY (compilation_id) REFERENCES compilations (compilation_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS "comments"
(
    comment_id SERIAL PRIMARY KEY NOT NULL,
    user_id    INTEGER            NOT NULL,
    event_id   INTEGER            NOT NULL,
    text       varchar(5000)      NOT NULL,
    created    TIMESTAMP DEFAULT now(),
    CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    CONSTRAINT fk_comment_event FOREIGN KEY (event_id) REFERENCES events (event_id) ON DELETE CASCADE
)
