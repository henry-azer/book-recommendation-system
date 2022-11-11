-- liquibase formatted sql

-- changeset henry:20221111_create_author_table

CREATE SEQUENCE public.author_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public.author
(
    id                BIGINT                 NOT NULL DEFAULT nextval('author_id_sequence'::regclass),
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
    CONSTRAINT author_name_unq UNIQUE (name)

) TABLESPACE pg_default;