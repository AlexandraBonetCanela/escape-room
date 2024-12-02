-- Insert into escaperoom
USE escaperoomdb;

INSERT INTO escaperoom (name, status) VALUES
('Mystery Mansion', 'ACTIVE'),
('The Lost Temple', 'ACTIVE'),
('Haunted House', 'INACTIVE');

-- Insert into room
INSERT INTO room (name, theme, difficulty_level, element_quantity, escape_room_id, status) VALUES
('Library of Secrets', 'Mystery', 'Medium', 10, 1, 'ACTIVE'),
('Temple Chamber', 'Adventure', 'Hard', 15, 2, 'ACTIVE'),
('The Curse Room', 'Horror', 'Easy', 8, 3, 'INACTIVE');

-- Insert into element (props)
INSERT INTO element (room_id, type, name, price, status) VALUES
(1, 'PROP', 'Ancient Book', 20.00, 'ACTIVE'),
(1, 'HINT', 'Mysterious Note', 5.00, 'ACTIVE'),
(2, 'PROP', 'Golden Idol', 50.00, 'ACTIVE'),
(2, 'HINT', 'Cryptic Symbol', 10.00, 'ACTIVE'),
(3, 'PROP', 'Cursed Doll', 30.00, 'INACTIVE');

-- Insert into prop
INSERT INTO prop (element_id, material_type) VALUES
(1, 'Leather'),
(3, 'Gold'),
(4, 'Stone');

-- Insert into hint
INSERT INTO hint (element_id, theme) VALUES
(2, 'Clue for Puzzle'),
(4, 'Ancient Riddle');

-- Insert into user
INSERT INTO user (name, email, is_registered, notifications) VALUES
('Barbie', 'barbie@gmail.com', 1, 1),
('Ken', 'ken@gmail.com', 1, 0),
(NULL, 'pitufo@gmail.com', 0, 1);

-- Insert into ticket
INSERT INTO ticket (user_id, unit_price, quantity, total_price) VALUES
(1, 25.00, 2, 50.00),
(2, 30.00, 1, 30.00),
(3, 20.00, 3, 60.00);

-- Insert into certificate
INSERT INTO certificate (name, description, escape_room_id) VALUES
('Escape Master', 'Awarded to those who successfully escape the room.', 1),
('Temple Explorer', 'Awarded for completing The Lost Temple.', 2);

-- Insert into certificate_to_user
INSERT INTO certificate_to_user (certificate_id, user_id) VALUES
(1, 1),
(2, 2);

-- Insert into newsletter
INSERT INTO newsletter (name, description) VALUES
('Escape Room News', 'Latest updates on new escape rooms and challenges.'),
('Monthly Challenges', 'Monthly newsletter featuring new puzzles and tips.');

-- Insert into newsletter_to_user
INSERT INTO newsletter_to_user (newsletter_id, user_id) VALUES
(1, 1),
(2, 2);