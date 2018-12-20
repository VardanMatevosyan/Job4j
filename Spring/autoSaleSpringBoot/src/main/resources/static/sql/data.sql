INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

INSERT INTO users (name, password, enabled, city, phone_number, fk_role_id)
VALUES ('root', '$2a$10$ew8LKpjckgDSFWzbzuIUBOq1rYCFGe2ZDzsUisWuTQxbJ596WURGi', TRUE, 'Poltava', 89261234567, 1);
