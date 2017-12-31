SELECT c.brand, c.name, e.name, g.name, t.transmission_type FROM car as c
INNER JOIN engine as e ON c.fk_engine_id = e.pk_id
INNER JOIN geatBox as g ON c.fk_geatBox_id = g.pk_id
INNER JOIN transmission as t ON c.fk_transmission_id = t.pk_id;

SELECT t.transmission_type FROM transmission AS t LEFT OUTER JOIN car as c ON t.pk_id = c.fk_transmission_id WHERE c.fk_transmission_id IS NULL;

SELECT e.name, e.brand FROM engine AS e LEFT OUTER JOIN car as c ON e.pk_id = c.fk_engine_id WHERE c.fk_engine_id IS NULL; 

SELECT g.name, g.speed_steps FROM car AS c RIGHT OUTER JOIN geatBox AS g ON g.pk_id = c.fk_geatBox_id WHERE c.fk_geatBox_id IS NULL; 