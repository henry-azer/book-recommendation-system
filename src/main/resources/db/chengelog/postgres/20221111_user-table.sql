-- liquibase formatted sql

-- changeset henry:20221111_create_user_table

CREATE SEQUENCE public.user_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public.user
(
    id                BIGINT                 NOT NULL DEFAULT nextval('user_id_sequence'::regclass),
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
    CONSTRAINT user_email_unq UNIQUE (email)

) TABLESPACE pg_default;
