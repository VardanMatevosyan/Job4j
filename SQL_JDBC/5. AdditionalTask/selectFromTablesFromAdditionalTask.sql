SELECT p.name FROM person AS p LEFT OUTER JOIN  company AS c ON c.id = p.company_id WHERE p.id = 5;
SELECT c.name, p.name FROM company as c INNER JOIN person AS p ON c.id = p.company_id;

SELECT c.name, MAX(p.company_id) AS "Maximum numbers of person" FROM company as c
INNER JOIN person AS p ON c.id = p.company_id
GROUP BY c.name;