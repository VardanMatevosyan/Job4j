
/* Display all the records from the table */ 

/*1 query ---->> Get all product with dairy type.*/
SELECT * FROM Product WHERE fk_type_id = (select type_id from ProductType where name = 'dairy');

/*2 query ---->> Get all product which conains "ice_cream" subset.*/
SELECT * FROM Product WHERE name LIKE '%ice_cream%';

/*3 query ---->> Get all the products, the expiration date of which ends in the next month.*/
SELECT name, expired_date FROM Product
WHERE (date_trunc('month', CURRENT_DATE + INTERVAL '1 month'), date_trunc('month', CURRENT_DATE + INTERVAL '2 month'))
OVERLAPS (expired_date, expired_date);

/*4 query  ---->> get the most expensive product*/
SELECT max(price) AS "the most expensive product" FROM Product;

/*5 query ---->> get count of every type of product in the database.*/
SELECT t.name AS "product type", count(t.name) FROM ProductType AS t
INNER JOIN Product AS p ON t.type_id = p.fk_type_id
GROUP BY t.name; 

/*6 query ---->> get the all product with type of sweets and dairy*/
/*first select is heavy and use three select from database wich is unnecessary becose of second select do*/
SELECT p.name, t.name AS "product_type" FROM Product AS p
INNER JOIN ProductType AS t ON p.fk_type_id = t.type_id
WHERE p.fk_type_id = (select type_id from ProductType where name = 'sweets')
OR p.fk_type_id = (select type_id from ProductType where name = 'dairy');

/*second select is light weight AND FASTER, becose of using only one select from database instead of first select above*/
SELECT p.name, t.name AS "product_type" FROM Product AS p 
INNER JOIN ProductType AS t ON p.fk_type_id = t.type_id
WHERE t.name = 'dairy' OR t.name = 'sweets'
ORDER BY t.name DESC; 

/*7 query ---->> get the name of products which amount is less than 10.*/
SELECT t.name, count(t.name) < 10 FROM ProductType AS t 
INNER JOIN Product AS p ON p.fk_type_id = t.type_id
GROUP BY t.name;

/*8 query ---->> get all the products name and their type.*/
SELECT p.name, t.name FROM Product AS p INNER JOIN ProductType AS t ON p.fk_type_id = t.type_id;


