DELETE FROM offers;

alter sequence cars_id_seq RESTART WITH 1;
alter sequence offers_id_seq RESTART WITH 1;

INSERT INTO cars (body_type, brand, engine_capacity, gear_box, model_vehicle, year_of_manufacture)
    VALUES ('bodyType', 'BMW', 4.4, 'auto', 'sedan', '1989-11-11');

INSERT INTO offers (tittle, description, picture, price, address, sold_state, posting_date, fk_user_id, fk_car_id)
VALUES ('tittleSecond', 'descriptionSecond', 'images/BMWsedan/photo.jpeg', 123123, 'addressSecond', FALSE, '2018-11-11', 1, 1);

INSERT INTO cars (body_type, brand, engine_capacity, gear_box, model_vehicle, year_of_manufacture)
VALUES ('fourPass', 'Lexus', 4.4, 'robot', 'sedan', '1980-11-11');

INSERT INTO offers (tittle, description, picture, price, address, sold_state, posting_date, fk_user_id, fk_car_id)
VALUES ('tittleFirst', 'descriptionFirst', 'images/default.jpeg', 456456, 'addressFirst', FALSE, now(), 1, 2);
