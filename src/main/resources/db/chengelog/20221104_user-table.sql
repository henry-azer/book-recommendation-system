-- liquibase formatted sql

-- changeset henry:20221104_create_user_table

CREATE TABLE IF NOT EXISTS user
(
    id                BIGINT UNSIGNED        NOT NULL AUTO_INCREMENT,
    first_name        CHARACTER VARYING(50)  NOT NULL,
    last_name         CHARACTER VARYING(50)  NOT NULL,
    email             CHARACTER VARYING(50)  NOT NULL,
    password          CHARACTER VARYING(100) NOT NULL,
    phone_number      CHARACTER VARYING(50)  NULL,
    birthdate         DATE                   NULL,
    country           CHARACTER VARYING(50)  NULL,
    age               INT                    NULL,
    gender            CHARACTER VARYING(50)  NULL,
    marital_status    CHARACTER VARYING(50)  NULL,
    image_url         CHARACTER VARYING(150) NULL,

    created_date      TIMESTAMP              NOT NULL,
    modified_date     TIMESTAMP              NOT NULL,
    created_by        CHARACTER VARYING(100) NOT NULL,
    modified_by       CHARACTER VARYING(100) NOT NULL,
    marked_as_deleted BOOLEAN                NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id),
    CONSTRAINT user_email_unq UNIQUE KEY (email)

);
