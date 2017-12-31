INSERT INTO engine (name, brand) VALUES ('X5xDrive35', 'BMW');
INSERT INTO engine (name, brand) VALUES ('2AZ-FE', 'TOYOTA');
INSERT INTO engine (name, brand) VALUES ('W220', 'Mersedes-Benz');
INSERT INTO engine (name, brand) VALUES ('l200', 'Lada');

INSERT INTO geatBox (name, speed_steps) VALUES ('steptonic', 5);
INSERT INTO geatBox (name, speed_steps) VALUES ('drive1', 5);
INSERT INTO geatBox (name, speed_steps) VALUES ('s600', 5);
INSERT INTO geatBox (name, speed_steps) VALUES ('L10', 5);

INSERT INTO transmission (transmission_type) VALUES ('automatic');
INSERT INTO transmission (transmission_type) VALUES ('manual');
INSERT INTO transmission (transmission_type) VALUES ('hybrid');


INSERT INTO car (brand, name, fk_engine_id, fk_transmission_id, fk_geatBox_id) VALUES ('BMW', 'x5', 1, 1, 1);
INSERT INTO car (brand, name, fk_engine_id, fk_transmission_id, fk_geatBox_id) VALUES ('TOYOTA', 'Camry 6', 2, 2, 2);
INSERT INTO car (brand, name, fk_engine_id, fk_transmission_id, fk_geatBox_id) VALUES ('Mersedes-Benz', 's600', 3, 1, 3);