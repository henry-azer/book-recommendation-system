-- liquibase formatted sql

-- changeset henry:20221110_create_user_reading_info_table

CREATE TABLE IF NOT EXISTS user_reading_info
(
    id                BIGINT UNSIGNED        NOT NULL AUTO_INCREMENT,
    user_id           BIGINT UNSIGNED        NOT NULL,
    reading_level     CHARACTER VARYING(50)  NOT NULL,

    created_date      TIMESTAMP              NOT NULL,
    modified_date     TIMESTAMP              NOT NULL,
    created_by        CHARACTER VARYING(100) NOT NULL,
    modified_by       CHARACTER VARYING(100) NOT NULL,
    marked_as_deleted BOOLEAN                NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id),
    CONSTRAINT user_reading_info_user_id_pk FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT user_reading_info_user_id_unq UNIQUE KEY (user_id)

);