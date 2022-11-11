-- liquibase formatted sql

-- changeset henry:20221111_create_book_table

CREATE SEQUENCE public.book_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public.book
(
    id                BIGINT                 NOT NULL DEFAULT nextval('book_id_sequence'::regclass),
    author_id         BIGINT                 NOT NULL,
    category_id       BIGINT                 NOT NULL,
    name              CHARACTER VARYING(100) NOT NULL,
    price             DOUBLE PRECISION       NOT NULL,
    rate              DOUBLE PRECISION       NOT NULL,
    users_rate_count  BIGINT                 NOT NULL,
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
    CONSTRAINT book_author_id_fk FOREIGN KEY (author_id) REFERENCES public.author (id),
    CONSTRAINT book_category_id_fk FOREIGN KEY (category_id) REFERENCES public.book_category (id),
    CONSTRAINT book_name_unq UNIQUE (name)

) TABLESPACE pg_default;
