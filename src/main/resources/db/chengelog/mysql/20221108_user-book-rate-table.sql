-- liquibase formatted sql

-- changeset henry:20221108_create_user_book_rate_table

CREATE TABLE IF NOT EXISTS user_book_rate
(
    id                BIGINT UNSIGNED        NOT NULL AUTO_INCREMENT,
    user_id           BIGINT UNSIGNED        NOT NULL,
    book_id           BIGINT UNSIGNED        NOT NULL,
    rate              INT                    NOT NULL,
    comment           TEXT                   NULL,

    created_date      TIMESTAMP              NOT NULL,
    modified_date     TIMESTAMP              NOT NULL,
    created_by        CHARACTER VARYING(100) NOT NULL,
    modified_by       CHARACTER VARYING(100) NOT NULL,
    marked_as_deleted BOOLEAN                NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id),
    CONSTRAINT user_book_rate_book_id_fk FOREIGN KEY (book_id) REFERENCES book (id),
    CONSTRAINT user_book_rate_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT user_book_rate_user_book_unq UNIQUE KEY (book_id, user_id)
);
