-- liquibase formatted sql

-- changeset henry:20221110_create_user_book_category_table

CREATE TABLE IF NOT EXISTS user_book_category
(
    id                BIGINT UNSIGNED        NOT NULL AUTO_INCREMENT,
    user_id           BIGINT UNSIGNED        NOT NULL,
    book_category_id  BIGINT UNSIGNED        NOT NULL,

    created_date      TIMESTAMP              NOT NULL,
    modified_date     TIMESTAMP              NOT NULL,
    created_by        CHARACTER VARYING(100) NOT NULL,
    modified_by       CHARACTER VARYING(100) NOT NULL,
    marked_as_deleted BOOLEAN                NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id),
    CONSTRAINT user_book_category_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT user_book_category_book_category_fk FOREIGN KEY (book_category_id) REFERENCES book_category (id),
    CONSTRAINT user_reading_info_user_id_book_category_id_unq UNIQUE KEY (user_id, book_category_id)

);
