CREATE TABLE ticket
(
    ticket_id         BIGSERIAL NOT NULL PRIMARY KEY,
    draw_date         TIMESTAMP WITHOUT TIME ZONE,
    numbers_from_user INTEGER[]
);

