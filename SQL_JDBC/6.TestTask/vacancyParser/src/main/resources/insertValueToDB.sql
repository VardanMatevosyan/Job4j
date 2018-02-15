INSERT INTO vacancy (tittle, author, create_date) VALUES (?, ?, ?);





--
--CREATE OR REPLACE FUNCTION insertToVacancy() RETURNS TRIGGER AS $$
--
--BEGIN
--IF NEW.tittle IS NULL THEN
--    INSERT INTO vacancy (tittle, author, create_date) VALUES (NEW.tittle, NEW.author, NEW.create_date);
--    RETURNS NEW;
--end IF;
--END;
--
--$$ LANGUAGE plpgsql;
--
--
--
--DROP TRIGGER IF EXISTS checkDateBeforeInsert ON vacancy;
--CREATE TRIGGER checkDateBeforeInsertToVacancy BEFORE INSERT ON vacancy
--FOR EACH ROW EXECUTE PROCEDURE insertToVacancy();

