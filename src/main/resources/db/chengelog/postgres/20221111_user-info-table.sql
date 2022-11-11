-- liquibase formatted sql

-- changeset henry:20221111_create_user_reading_info_table

CREATE SEQUENCE public.user_reading_info_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public.user_reading_info
(
    id                BIGINT                 NOT NULL DEFAULT nextval('user_reading_info_id_sequence'::regclass),
    user_id           BIGINT                 NOT NULL,
    reading_level     CHARACTER VARYING(50)  NOT NULL,

    created_date      TIMESTAMP              NOT NULL,
    modified_date     TIMESTAMP              NOT NULL,
    created_by        CHARACTER VARYING(100) NOT NULL,
    modified_by       CHARACTER VARYING(100) NOT NULL,
    marked_as_deleted BOOLEAN                NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id),
    CONSTRAINT user_reading_info_user_id_pk FOREIGN KEY (user_id) REFERENCES public.user (id),
    CONSTRAINT user_reading_info_user_id_unq UNIQUE (user_id)

) TABLESPACE pg_default;
