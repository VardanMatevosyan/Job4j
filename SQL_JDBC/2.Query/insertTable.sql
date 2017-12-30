/* Create few records in this table */ 

INSERT INTO ProductType (name) VALUES('alcohol'); 

INSERT INTO ProductType (name) VALUES('sweets'); 

INSERT INTO ProductType (name) VALUES('vegetables'); 

INSERT INTO ProductType (name) VALUES('dairy'); 

 
INSERT INTO Product (name, fk_type_id, expired_date, price) VALUES('berezka', (select type_id from ProductType where name = 'alcohol'), '2017-12-18', 500); 

INSERT INTO Product (name, fk_type_id, expired_date, price) VALUES('korovka', (select type_id from ProductType where name = 'sweets'), '2017-12-19', 200); 

INSERT INTO Product (name, fk_type_id, expired_date, price) VALUES('potato', (select type_id from ProductType where name = 'vegetables'), '2018-01-11', 100); 

INSERT INTO Product (name, fk_type_id, expired_date, price) VALUES('carrot', (select type_id from ProductType where name = 'vegetables'), '2018-01-11', 150); 

INSERT INTO Product (name, fk_type_id, expired_date, price) VALUES('cheese', (select type_id from ProductType where name = 'dairy'), '2017-12-28', 550); 

INSERT INTO Product (name, fk_type_id, expired_date, price) VALUES('milk', (select type_id from ProductType where name = 'dairy'), '2017-12-28', 650); 

INSERT INTO Product (name, fk_type_id, expired_date, price) VALUES('ice_cream_cake', (select type_id from ProductType where name = 'sweets'), '2017-12-28', 50);

INSERT INTO Product (name, fk_type_id, expired_date, price) VALUES('cake', (select type_id from ProductType where name = 'sweets'), '2018-01-31', 50);