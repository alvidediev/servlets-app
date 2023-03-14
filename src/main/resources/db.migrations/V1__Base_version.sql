CREATE TABLE IF NOT EXISTS proselyte.user
(
    user_id        INTEGER      NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS proselyte.event
(
    event_id      INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fk_file_id INTEGER NOT NULL,
    fk_user_id INTEGER NOT NULL,
    CONSTRAINT fk_file_id
        FOREIGN KEY (fk_file_id) REFERENCES proselyte.file (file_id),
    CONSTRAINT fk_user_id
        FOREIGN KEY (fk_user_id) REFERENCES proselyte.user (user_id)
);

CREATE TABLE IF NOT EXISTS proselyte.file (
    file_id INTEGER NOT NULL AUTO_INCREMENT,
    file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    PRIMARY KEY (file_id)
);

