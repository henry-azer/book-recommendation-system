-- liquibase formatted sql

-- changeset henry:20221105_create_refresh_token_table

CREATE TABLE IF NOT EXISTS refresh_token
(
    id                BIGINT UNSIGNED        NOT NULL AUTO_INCREMENT,
    token             CHARACTER VARYING(200) NOT NULL,
    email             CHARACTER VARYING(50)  NOT NULL,
    refresh_count     LONG                   NOT NULL,
    expiry_date       DATE                   NOT NULL,

    marked_as_deleted BOOLEAN                NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id)
);
