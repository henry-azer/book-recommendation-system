-- liquibase formatted sql

-- changeset henry:20221105_create_book_table

CREATE TABLE IF NOT EXISTS book
(
    id                BIGINT UNSIGNED        NOT NULL AUTO_INCREMENT,
    author_id         BIGINT UNSIGNED        NOT NULL,
    name              CHARACTER VARYING(100) NOT NULL,
    category          CHARACTER VARYING(50)  NOT NULL,
    price             DOUBLE                 NOT NULL,
    pages_number      INT                    NOT NULL,
    reading_duration  INT                    NOT NULL,
    publish_date      DATE                   NOT NULL,
    description       TEXT                   NOT NULL,
    image_url         CHARACTER VARYING(150) NOT NULL,

    created_date      TIMESTAMP              NOT NULL,
    modified_date     TIMESTAMP              NOT NULL,
    created_by        CHARACTER VARYING(100) NOT NULL,
    modified_by       CHARACTER VARYING(100) NOT NULL,
    marked_as_deleted BOOLEAN                NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id),
    CONSTRAINT book_author_id_fk FOREIGN KEY (author_id) REFERENCES author (id)
);
