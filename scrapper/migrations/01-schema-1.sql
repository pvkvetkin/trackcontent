--liquibase formatted sql

--changeset pvkvetkin:1
CREATE TABLE IF NOT EXISTS link
(
    id            bigserial PRIMARY KEY,
    url           text      NOT NULL UNIQUE,
    last_check_at timestamp with time zone NOT NULL,
    updated_at    timestamp with time zone NOT NULL
);

--changeset pvkvetkin:2
CREATE TABLE IF NOT EXISTS chat
(
    id bigint PRIMARY KEY
);

--changeset pvkvetkin:3
CREATE TABLE IF NOT EXISTS link_chat
(
    link_id bigint REFERENCES link (id) ON DELETE CASCADE NOT NULL,
    chat_id bigint REFERENCES chat (id) ON DELETE CASCADE NOT NULL,
    PRIMARY KEY (link_id, chat_id)
);
