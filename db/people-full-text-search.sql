DO $$
    BEGIN
        IF NOT EXISTS (
            SELECT 1
            FROM information_schema.columns
            WHERE table_name = 'persons'
              AND column_name = 'search_vector'
        ) THEN
            ALTER TABLE persons ADD COLUMN search_vector tsvector;
    END IF;
END $$;


UPDATE persons
SET search_vector = to_tsvector('english', COALESCE(firstname, '') || ' ' || COALESCE(middlename, '') || ' ' || COALESCE(lastname, '') || ' ' || COALESCE(email, ''));


CREATE INDEX persons_search_idx ON persons USING gin(search_vector);


CREATE OR REPLACE FUNCTION update_search_vector()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.search_vector := to_tsvector('english', COALESCE(NEW.firstname, '') || ' ' || COALESCE(NEW.middlename, '') || ' ' || COALESCE(NEW.lastname, '') || ' ' || COALESCE(NEW.email, ''));
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_update_search_vector
    BEFORE INSERT OR UPDATE ON persons
    FOR EACH ROW EXECUTE FUNCTION update_search_vector();


SELECT * FROM persons WHERE persons.firstname ILIKE '%John%';

SELECT * FROM persons WHERE search_vector @@ plainto_tsquery('english', 'John');

EXPLAIN ANALYZE SELECT * FROM persons WHERE search_vector @@ plainto_tsquery('english', 'John');

EXPLAIN ANALYZE SELECT * FROM persons WHERE persons.firstname ILIKE '%John%';