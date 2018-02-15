CREATE OR REPLACE FUNCTION insertToVacancy() RETURNS TRIGGER AS $$ BEGIN IF dateIsCorrect(NEW.create_date) THEN INSERT INTO vacancy (tittle, author, create_date) VALUES (NEW.tittle, NEW.author, NEW.create_date); END IF;  RETURN NEW; END; $$ LANGUAGE plpgsql; DROP TRIGGER IF EXISTS checkDateBeforeInsertToVacancy ON vacancy;  CREATE TRIGGER checkDateBeforeInsertToVacancy BEFORE INSERT ON vacancy FOR EACH ROW EXECUTE PROCEDURE insertToVacancy();

CREATE OR REPLACE FUNCTION dateIsCorrect(dateToCheck timestamp) RETURNS boolean AS $$

BEGIN
    IF dateToCheck::date <= date_trunc('year', CURRENT_DATE)::date THEN
        return true;
    else
        return false;
    END IF;
END;

$$ LANGUAGE plpgsql;