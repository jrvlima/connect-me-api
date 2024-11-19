DO $$
    BEGIN
        IF NOT EXISTS (
            SELECT 1
            FROM information_schema.columns
            WHERE table_name = 'skills'
              AND column_name = 'search_vector'
        ) THEN
            ALTER TABLE skills ADD COLUMN search_vector tsvector;
        END IF;
    END $$;

UPDATE skills s
SET search_vector = to_tsvector('english',
                                COALESCE(s.name, '') || ' ' ||
                                COALESCE(s.description, '') || ' ' ||
                                COALESCE(sc.name, '')
                    )
FROM skill_categories sc
WHERE s.category_id = sc.id;


CREATE INDEX skills_search_idx ON skills USING gin(search_vector);


CREATE OR REPLACE FUNCTION update_skill_search_vector()
    RETURNS trigger AS $$
BEGIN
    NEW.search_vector := to_tsvector('english',
                                     COALESCE(NEW.name, '') || ' ' ||
                                     COALESCE(NEW.description, '') || ' ' ||
                                     COALESCE((SELECT name FROM skill_categories WHERE id = NEW.category_id), '')
                         );
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER skill_search_vector_update
    BEFORE INSERT OR UPDATE
    ON skills
    FOR EACH ROW
EXECUTE FUNCTION update_skill_search_vector();