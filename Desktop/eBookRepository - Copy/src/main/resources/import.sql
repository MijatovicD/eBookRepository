INSERT INTO categories (category_id, name) VALUES (1, 'komedija');
INSERT INTO categories (category_id, name) VALUES (2, 'romantika');

INSERT INTO users (user_id, firstname, lastname, username, password, type) VALUES (1, 'petar', 'petrovic', 'pera', '$2a$04$zUzf/oVgOCvnm4FJK0TnxOLkIxjaFKiPhmFbRsIrZZ2c12tVed/DC', 'admin');
INSERT INTO users (user_id, firstname, lastname, username, password, type) VALUES (2, 'miroslav', 'mikic', 'mika', '$2a$04$rnOGjkptmqtfe4XaIfoWrO1B8d07fM7Fohz59.WMG.pCEX2cX9bfm', 'pretplanik');

INSERT INTO languages (language_id, name) VALUES (1, 'srpski');
INSERT INTO languages (language_id, name) VALUES (2, 'engleski');

INSERT INTO user_categories (user_id, category_id) VALUES (1,1);
INSERT INTO user_categories (user_id, category_id) VALUES (2,2);

INSERT INTO authority(name)VALUES('ROLE_ADMIN');
INSERT INTO authority(name)VALUES('ROLE_USER');

INSERT INTO user_authority(user_id, authority_id) VALUES (1,1);
INSERT INTO user_authority(user_id, authority_id) VALUES (2,2);
