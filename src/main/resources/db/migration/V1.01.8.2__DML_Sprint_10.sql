-- [ Dropped objects ] --
ALTER TABLE aluno DROP CONSTRAINT IF EXISTS aluno_grade_curricular_id_fkey CASCADE;
-- ddl-end --
ALTER TABLE aluno DROP CONSTRAINT IF EXISTS aluno_periodo_letivo_id_fkey CASCADE;
-- ddl-end --
DROP INDEX IF EXISTS ix_aluno_grade_curricular_id CASCADE;
-- ddl-end --
DROP INDEX IF EXISTS ix_aluno_periodo_letivo_id CASCADE;
-- ddl-end --
ALTER TABLE aluno DROP COLUMN IF EXISTS grade_curricular_id CASCADE;
-- ddl-end --
ALTER TABLE aluno DROP COLUMN IF EXISTS periodo_letivo_id CASCADE;
-- ddl-end --


-- object: id | type: COLUMN --
-- ALTER TABLE aluno_serie DROP COLUMN IF EXISTS id CASCADE;
ALTER TABLE aluno_serie ADD COLUMN id integer NOT NULL;
-- ddl-end --


-- object: periodo_letivo_id | type: COLUMN --
-- ALTER TABLE aluno_serie DROP COLUMN IF EXISTS periodo_letivo_id CASCADE;
ALTER TABLE aluno_serie ADD COLUMN periodo_letivo_id integer NOT NULL;
-- ddl-end --

-- [ Created constraints ] --
-- object: aluno_serie_pk | type: CONSTRAINT --
-- ALTER TABLE aluno_serie DROP CONSTRAINT IF EXISTS aluno_serie_pk CASCADE;
ALTER TABLE aluno_serie ADD CONSTRAINT aluno_serie_pk PRIMARY KEY (id);
-- ddl-end --

-- [ Created foreign keys ] --
-- object: periodo_letivo_id | type: CONSTRAINT --
-- ALTER TABLE aluno_serie DROP CONSTRAINT IF EXISTS periodo_letivo_id CASCADE;
ALTER TABLE aluno_serie ADD CONSTRAINT periodo_letivo_id FOREIGN KEY (periodo_letivo_id)
REFERENCES periodo_letivo (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

