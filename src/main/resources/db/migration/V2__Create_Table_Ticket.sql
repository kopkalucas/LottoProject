CREATE TABLE ticket
(
    ticket_id         BIGSERIAL NOT NULL,
    draw_date         TIMESTAMP WITHOUT TIME ZONE,
    numbers_from_user INTEGER[],
    CONSTRAINT pk_ticket PRIMARY KEY (ticket_id)
);

