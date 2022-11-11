-- liquibase formatted sql

-- changeset henry:20221105_create_author_table

CREATE TABLE IF NOT EXISTS author
(
    id                BIGINT UNSIGNED        NOT NULL AUTO_INCREMENT,
    name              CHARACTER VARYING(100) NOT NULL,
    birthdate         DATE                   NOT NULL,
    deathdate         DATE                   NULL,
    country           CHARACTER VARYING(50)  NOT NULL,
    age               INT                    NOT NULL,
    gender            CHARACTER VARYING(50)  NOT NULL,
    description       TEXT                   NOT NULL,
    image_url         CHARACTER VARYING(250) NOT NULL,

    created_date      TIMESTAMP              NOT NULL,
    modified_date     TIMESTAMP              NOT NULL,
    created_by        CHARACTER VARYING(100) NOT NULL,
    modified_by       CHARACTER VARYING(100) NOT NULL,
    marked_as_deleted BOOLEAN                NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id),
    CONSTRAINT author_name_unq UNIQUE KEY (name)

);
