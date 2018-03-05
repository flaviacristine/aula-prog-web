
ALTER TABLE aluno_serie ADD COLUMN divida_id integer;
-- ddl-end --
ALTER TABLE aluno_serie ADD CONSTRAINT divida_id FOREIGN KEY (divida_id)
REFERENCES divida (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

