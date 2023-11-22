CREATE TABLE result_response
(
    hash         VARCHAR(255) NOT NULL PRIMARY KEY,
    numbers      INTEGER[],
    won_numbers  INTEGER[],
    hit_numbers  INTEGER[],
    draw_date    TIMESTAMP WITHOUT TIME ZONE,
    is_winner    BOOLEAN      NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE
);
