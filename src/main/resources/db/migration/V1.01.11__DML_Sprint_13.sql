
ALTER TABLE unidade DROP CONSTRAINT IF EXISTS arquivo_fk CASCADE;

ALTER TABLE mantenedora DROP CONSTRAINT IF EXISTS arquivo_fk CASCADE;

ALTER TABLE instituicao DROP CONSTRAINT IF EXISTS arquivo_fk CASCADE;

ALTER TABLE pessoa DROP CONSTRAINT IF EXISTS arquivo_fk CASCADE;

ALTER TABLE disciplina_equivalencia DROP CONSTRAINT IF EXISTS disciplina_id CASCADE;

ALTER TABLE disciplina_equivalencia DROP CONSTRAINT IF EXISTS serie_disciplina_id CASCADE;

ALTER TABLE unidade DROP CONSTRAINT IF EXISTS unidade_uq CASCADE;

ALTER TABLE mantenedora DROP CONSTRAINT IF EXISTS mantenedora_uq CASCADE;

ALTER TABLE instituicao DROP CONSTRAINT IF EXISTS instituicao_uq CASCADE;

ALTER TABLE pessoa DROP CONSTRAINT IF EXISTS pessoa_uq CASCADE;

ALTER TABLE aluno_serie_disciplina_turma DROP CONSTRAINT IF EXISTS aluno_serie_disciplina_turma_serie_disciplina_turma_id_fkey CASCADE;

ALTER TABLE aluno_serie_disciplina_turma DROP CONSTRAINT IF EXISTS aluno_serie_disciplina_turma_aluno_id_fkey CASCADE;

ALTER TABLE aluno_serie_disciplina_turma DROP CONSTRAINT IF EXISTS aluno_serie_disciplina_turma_pkey CASCADE;

DROP TABLE IF EXISTS disciplina_equivalencia CASCADE;

ALTER TABLE pessoa DROP COLUMN IF EXISTS id_arquivo CASCADE;

ALTER TABLE mantenedora DROP COLUMN IF EXISTS id_arquivo CASCADE;

ALTER TABLE instituicao DROP COLUMN IF EXISTS id_arquivo CASCADE;

ALTER TABLE unidade DROP COLUMN IF EXISTS id_arquivo CASCADE;

CREATE SEQUENCE aluno_serie_disciplina_turma_id_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;


ALTER TABLE aluno_serie_disciplina_turma ADD COLUMN id smallint NOT NULL DEFAULT nextval('aluno_serie_disciplina_turma_id_seq'::regclass);

CREATE SEQUENCE falta_id_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;



CREATE SEQUENCE nota_id_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;



CREATE TABLE falta(
	id integer NOT NULL DEFAULT nextval('falta_id_seq'::regclass),
	valor integer NOT NULL,
	frequencia_id integer,
	aluno_serie_disciplina_turma_id smallint,
	CONSTRAINT falta_pk PRIMARY KEY (id)

);

CREATE TABLE nota(
	id integer NOT NULL DEFAULT nextval('nota_id_seq'::regclass),
	valor float NOT NULL,
	avaliacao_id integer,
	aluno_serie_disciplina_turma_id smallint,
	CONSTRAINT nota_pk PRIMARY KEY (id)

);

CREATE SEQUENCE resultado_disciplina_id_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

CREATE TABLE resultado_disciplina(
	id integer NOT NULL DEFAULT nextval('resultado_disciplina_id_seq'::regclass),
	situacao_aluno smallint NOT NULL,
	media float NOT NULL,
	frequencia integer NOT NULL,
	aluno_serie_disciplina_turma_id smallint,
	CONSTRAINT resultado_disciplina_pk PRIMARY KEY (id)
);

ALTER TABLE pessoa ADD COLUMN arquivo_id integer;

ALTER TABLE mantenedora ADD COLUMN arquivo_id integer;

ALTER TABLE instituicao ADD COLUMN arquivo_id integer;

ALTER TABLE unidade ADD COLUMN arquivo_id integer;

ALTER TABLE aluno_serie_disciplina_turma ALTER COLUMN aluno_id DROP NOT NULL;

ALTER TABLE aluno_serie_disciplina_turma ALTER COLUMN serie_disciplina_turma_id DROP NOT NULL;

ALTER TABLE aluno_serie_disciplina_turma ADD CONSTRAINT aluno_serie_disciplina_turma_pkey PRIMARY KEY (id);

ALTER TABLE falta ADD CONSTRAINT uq_falta_frequencia_aluno_serie_disciplina_turma_id UNIQUE (frequencia_id,aluno_serie_disciplina_turma_id);

ALTER TABLE nota ADD CONSTRAINT uq_nota_avaliacao_aluno_serie_disciplina_turma_id UNIQUE (avaliacao_id,aluno_serie_disciplina_turma_id);

ALTER TABLE resultado_disciplina ADD CONSTRAINT uq_resultado_disciplina_aluno_serie_disciplina_turma_id UNIQUE (aluno_serie_disciplina_turma_id);

ALTER TABLE nota ADD CONSTRAINT nota_uq UNIQUE (avaliacao_id);

ALTER TABLE falta ADD CONSTRAINT falta_uq UNIQUE (frequencia_id);

ALTER TABLE falta ADD CONSTRAINT falta_uq1 UNIQUE (aluno_serie_disciplina_turma_id);

ALTER TABLE nota ADD CONSTRAINT nota_uq1 UNIQUE (aluno_serie_disciplina_turma_id);

ALTER TABLE resultado_disciplina ADD CONSTRAINT resultado_disciplina_uq UNIQUE (aluno_serie_disciplina_turma_id);

ALTER TABLE pessoa ADD CONSTRAINT pessoa_uq UNIQUE (arquivo_id);

ALTER TABLE mantenedora ADD CONSTRAINT mantenedora_uq UNIQUE (arquivo_id);

ALTER TABLE instituicao ADD CONSTRAINT instituicao_uq UNIQUE (arquivo_id);

ALTER TABLE unidade ADD CONSTRAINT unidade_uq UNIQUE (arquivo_id);

ALTER TABLE nota ADD CONSTRAINT avaliacao_fk FOREIGN KEY (avaliacao_id)
REFERENCES avaliacao (id);

ALTER TABLE falta ADD CONSTRAINT frequencia_fk FOREIGN KEY (frequencia_id)
REFERENCES frequencia (id);

ALTER TABLE falta ADD CONSTRAINT aluno_serie_disciplina_turma_fk FOREIGN KEY (aluno_serie_disciplina_turma_id)
REFERENCES aluno_serie_disciplina_turma (id);

ALTER TABLE nota ADD CONSTRAINT aluno_serie_disciplina_turma_fk FOREIGN KEY (aluno_serie_disciplina_turma_id)
REFERENCES aluno_serie_disciplina_turma (id);

ALTER TABLE resultado_disciplina ADD CONSTRAINT aluno_serie_disciplina_turma_fk FOREIGN KEY (aluno_serie_disciplina_turma_id)
REFERENCES aluno_serie_disciplina_turma (id);

ALTER TABLE pessoa ADD CONSTRAINT arquivo_fk FOREIGN KEY (arquivo_id)
REFERENCES arquivo (id);

ALTER TABLE mantenedora ADD CONSTRAINT arquivo_fk FOREIGN KEY (arquivo_id)
REFERENCES arquivo (id);

ALTER TABLE instituicao ADD CONSTRAINT arquivo_fk FOREIGN KEY (arquivo_id)
REFERENCES arquivo (id);

ALTER TABLE unidade ADD CONSTRAINT arquivo_fk FOREIGN KEY (arquivo_id)
REFERENCES arquivo (id);

ALTER TABLE aluno_serie_disciplina_turma ADD CONSTRAINT aluno_serie_disciplina_turma_aluno_id_fkey FOREIGN KEY (aluno_id)
REFERENCES aluno (id);

ALTER TABLE aluno_serie_disciplina_turma ADD CONSTRAINT aluno_serie_disciplina_turma_serie_disciplina_turma_id_fkey FOREIGN KEY (serie_disciplina_turma_id)
REFERENCES serie_disciplina_turma (id);