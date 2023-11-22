CREATE TABLE player
(
    hash        VARCHAR(255) NOT NULL PRIMARY KEY,
    numbers     INTEGER[],
    hit_numbers INTEGER[],
    draw_date   TIMESTAMP WITHOUT TIME ZONE,
    is_winner   BOOLEAN      NOT NULL,
    won_numbers INTEGER[]
);