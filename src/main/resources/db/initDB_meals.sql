DROP TABLE IF EXISTS meals;

CREATE TABLE meals (
    id SERIAL PRIMARY KEY,
    date_time TIMESTAMP NOT NULL,
    description TEXT NOT NULL,
    calories INTEGER NOT NULL,
    userId INTEGER NOT NULL,
    CONSTRAINT fk_userId
        FOREIGN KEY (userId)
        REFERENCES users(id),
    CONSTRAINT unique_date_time
        UNIQUE (date_time)
);
 
CREATE INDEX idx_meals_id ON meals (id);
CREATE INDEX idx_meals_date_time ON meals (date_time);
CREATE INDEX idx_meals_calories ON meals (calories);
CREATE INDEX idx_meals_userId ON meals (userId);
CREATE INDEX idx_meals_description ON meals (description);