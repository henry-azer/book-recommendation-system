-- liquibase formatted sql

-- changeset henry:20221111_create_refresh_token_table

CREATE SEQUENCE public.refresh_token_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public.refresh_token
(
    id                BIGINT                 NOT NULL DEFAULT nextval('refresh_token_id_sequence'::regclass),
    token             CHARACTER VARYING(200) NOT NULL,
    email             CHARACTER VARYING(50)  NOT NULL,
    refresh_count     INT                    NOT NULL,
    expiry_date       DATE                   NOT NULL,

    marked_as_deleted BOOLEAN                NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id)

) TABLESPACE pg_default;
