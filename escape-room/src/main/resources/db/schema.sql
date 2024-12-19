CREATE DATABASE IF NOT EXISTS escaperoomdb;

USE escaperoomdb;

CREATE TABLE IF NOT EXISTS escaperoom (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    price DECIMAL(10,2) CHECK(price >= 0) NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE' NOT NULL
);

CREATE TABLE IF NOT EXISTS room (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    theme VARCHAR(45) NOT NULL,
    difficulty_level ENUM('EASY', 'MEDIUM', 'DIFFICULT') NOT NULL,
    element_quantity INT UNSIGNED NOT NULL,
    escape_room_id INT UNSIGNED NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE' NOT NULL,
    CONSTRAINT fk_room_escape_room_id FOREIGN KEY (escape_room_id) REFERENCES escaperoom(id)
);

CREATE TABLE IF NOT EXISTS element (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    room_id INT UNSIGNED,
    type ENUM('PROP', 'HINT') NOT NULL,
    name VARCHAR(45) NOT NULL,
    price DECIMAL(10,2) CHECK(price >= 0) NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE' NOT NULL,
    date_created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_element_room_id FOREIGN KEY (room_id) REFERENCES room(id)
);

CREATE TRIGGER update_element_last_updated BEFORE UPDATE ON element
FOR EACH ROW
SET NEW.last_updated = CURRENT_TIMESTAMP;

CREATE TABLE IF NOT EXISTS prop (
    element_id INT UNSIGNED PRIMARY KEY,
    material_type VARCHAR(45) NOT NULL,
    CONSTRAINT fk_prop_element_id FOREIGN KEY (element_id) REFERENCES element(id)
);

CREATE TABLE IF NOT EXISTS hint (
    element_id INT UNSIGNED PRIMARY KEY,
    theme VARCHAR(45) NOT NULL,
    CONSTRAINT fk_hint_element_id FOREIGN KEY (element_id) REFERENCES element(id)
);

CREATE TABLE IF NOT EXISTS user (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45),
    email VARCHAR(45) NOT NULL,
    is_registered TINYINT DEFAULT 0 NOT NULL,
    notifications TINYINT DEFAULT 0 NOT NULL,
    date_created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TRIGGER update_user_last_updated BEFORE UPDATE ON user
FOR EACH ROW
SET NEW.last_updated = CURRENT_TIMESTAMP;

CREATE TABLE IF NOT EXISTS ticket (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNSIGNED NOT NULL,
    unit_price DECIMAL(10,2) CHECK(unit_price >= 0) NOT NULL,
    quantity INT UNSIGNED NOT NULL,
    total_price DECIMAL(10,2) CHECK(total_price >= 0) NOT NULL,
    date_created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_reservation DATETIME NOT NULL,
    escape_room_id INT UNSIGNED NOT NULL,
    CONSTRAINT fk_ticket_escape_room_id FOREIGN KEY (escape_room_id) REFERENCES escaperoom(id),
    CONSTRAINT fk_ticket_user_id FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE IF NOT EXISTS certificate (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    description VARCHAR(300),
    escape_room_id INT UNSIGNED NOT NULL,
    date_created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_certificate_escape_room_id FOREIGN KEY (escape_room_id) REFERENCES escaperoom(id)
);

CREATE TABLE IF NOT EXISTS certificate_to_user (
    certificate_id INT UNSIGNED NOT NULL,
    user_id INT UNSIGNED NOT NULL,
    date_issued DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_ctu_certificate_id FOREIGN KEY (certificate_id) REFERENCES certificate(id),
    CONSTRAINT fk_ctu_user_id FOREIGN KEY (user_id) REFERENCES user(id),
    PRIMARY KEY (user_id, certificate_id)
);

CREATE TABLE IF NOT EXISTS newsletter (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    description VARCHAR(300),
    date_created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS newsletter_to_user (
    newsletter_id INT UNSIGNED NOT NULL,
    user_id INT UNSIGNED NOT NULL,
    date_sent DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_ntu_newsletter_id FOREIGN KEY (newsletter_id) REFERENCES newsletter(id),
    CONSTRAINT fk_ntu_user_id FOREIGN KEY (user_id) REFERENCES user(id),
    PRIMARY KEY (user_id, newsletter_id)
);

CREATE OR REPLACE VIEW inventory AS
SELECT
    'Escaperoom' AS type,
    CONCAT('ID: ', e.id) AS reference,
    e.name AS name,
    e.price AS price,
    NULL AS details
FROM
    escaperoom e
WHERE
    e.status = 'ACTIVE'

UNION ALL

SELECT
    'Room' AS type,
    CONCAT('ID: ', r.id, ', ESCAPEROOM ID: ' , r.escape_room_id) AS reference,
    r.name AS name,
    NULL AS price,
    CONCAT ('Theme: ', r.theme, ', Difficulty level:', r.difficulty_level) AS details
FROM
    room r
WHERE
    r.status = 'ACTIVE'

UNION ALL

SELECT
    'Element' AS type,
     CONCAT('ID: ', el.id, ', ROOM ID: ' , el.room_id) AS reference,
    el.name AS name,
    el.price AS price,
    CASE
        WHEN el.type = 'PROP' THEN CONCAT('TYPE: ', el.type, ', Material Type: ' , pr.material_type)
        WHEN el.type = 'HINT' THEN CONCAT('TYPE: ', el.type, ', Theme: ', h.theme)
        ELSE NULL
    END AS details
FROM
    element el
LEFT JOIN
    prop pr ON el.id = pr.element_id
LEFT JOIN
    hint h ON el.id = h.element_id
WHERE
    el.status = 'ACTIVE';