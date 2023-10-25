CREATE SCHEMA public
CREATE TABLE winning_numbers
(
    date TIMESTAMP WITHOUT TIME ZONE NOT NULL PRIMARY KEY,
    winning_numbers INTEGER[]
);

