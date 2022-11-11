-- liquibase formatted sql

-- changeset henry:20221111_create_book_category_table

CREATE SEQUENCE public.book_category_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public.book_category
(
    id                BIGINT                 NOT NULL DEFAULT nextval('book_category_id_sequence'::regclass),
    name              CHARACTER VARYING(50)  NOT NULL,
    description       CHARACTER VARYING(150) NULL,

    created_date      TIMESTAMP              NOT NULL,
    modified_date     TIMESTAMP              NOT NULL,
    created_by        CHARACTER VARYING(100) NOT NULL,
    modified_by       CHARACTER VARYING(100) NOT NULL,
    marked_as_deleted BOOLEAN                NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id),
    CONSTRAINT book_category_name_unq UNIQUE (name)

) TABLESPACE pg_default;

-- changeset henry:20221111_insert_into_book_category_table
INSERT INTO public.book_category (name, description, created_date, modified_date, created_by, modified_by, marked_as_deleted)
VALUES ('Science Fiction', '', current_timestamp, current_timestamp, 'admin', 'admin', false);
INSERT INTO public.book_category (name, description, created_date, modified_date, created_by, modified_by, marked_as_deleted)
VALUES ('Horror', '', current_timestamp, current_timestamp, 'admin', 'admin', false);
INSERT INTO public.book_category (name, description, created_date, modified_date, created_by, modified_by, marked_as_deleted)
VALUES ('Classic', '', current_timestamp, current_timestamp, 'admin', 'admin', false);
INSERT INTO public.book_category (name, description, created_date, modified_date, created_by, modified_by, marked_as_deleted)
VALUES ('Action and Adventure', '', current_timestamp, current_timestamp, 'admin', 'admin', false);
INSERT INTO public.book_category (name, description, created_date, modified_date, created_by, modified_by, marked_as_deleted)
VALUES ('Romantic', '', current_timestamp, current_timestamp, 'admin', 'admin', false);
INSERT INTO public.book_category (name, description, created_date, modified_date, created_by, modified_by, marked_as_deleted)
VALUES ('Kids', '', current_timestamp, current_timestamp, 'admin', 'admin', false);
INSERT INTO public.book_category (name, description, created_date, modified_date, created_by, modified_by, marked_as_deleted)
VALUES ('History', '', current_timestamp, current_timestamp, 'admin', 'admin', false);
INSERT INTO public.book_category (name, description, created_date, modified_date, created_by, modified_by, marked_as_deleted)
VALUES ('Sport', '', current_timestamp, current_timestamp, 'admin', 'admin', false);
