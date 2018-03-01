-- [ Dropped objects ] --
ALTER TABLE pagamento DROP CONSTRAINT IF EXISTS pagamento_agencia_conta_id_fkey CASCADE;
-- ddl-end --
DROP INDEX IF EXISTS uq_pagamento_numero_transacao_agencia_conta_id CASCADE;
-- ddl-end --
DROP INDEX IF EXISTS ix_pagamento_conta_id CASCADE;
-- ddl-end --
ALTER TABLE pagamento DROP COLUMN IF EXISTS agencia_conta_id CASCADE;
-- ddl-end --
ALTER TABLE pagamento DROP COLUMN IF EXISTS data_deposito CASCADE;
-- ddl-end --
ALTER TABLE pagamento DROP COLUMN IF EXISTS hora_deposito CASCADE;
-- ddl-end --
ALTER TABLE pagamento DROP COLUMN IF EXISTS numero_transacao CASCADE;
-- ddl-end --
ALTER TABLE pagamento DROP COLUMN IF EXISTS valor CASCADE;
-- ddl-end --
ALTER TABLE pagamento DROP COLUMN IF EXISTS tipo_pagamento CASCADE;
-- ddl-end --
ALTER TABLE pagamento DROP COLUMN IF EXISTS tipo_venda CASCADE;
-- ddl-end --


-- [ Created objects ] --
-- object: situacao | type: COLUMN --
-- ALTER TABLE turma DROP COLUMN IF EXISTS situacao CASCADE;
ALTER TABLE turma ADD COLUMN situacao smallint DEFAULT 1;
-- ddl-end --


-- object: aluno_serie | type: TABLE --
-- DROP TABLE IF EXISTS aluno_serie CASCADE;
CREATE TABLE aluno_serie(
	aluno_id integer NOT NULL,
	serie_id integer NOT NULL
);
-- ddl-end --
-- ddl-end --

-- object: valor_pago | type: COLUMN --
-- ALTER TABLE cobranca DROP COLUMN IF EXISTS valor_pago CASCADE;
ALTER TABLE cobranca ADD COLUMN valor_pago decimal(14,4);
-- ddl-end --


-- object: valor_pago | type: COLUMN --
-- ALTER TABLE pagamento DROP COLUMN IF EXISTS valor_pago CASCADE;
ALTER TABLE pagamento ADD COLUMN valor_pago decimal(14,4) NOT NULL;
-- ddl-end --


-- object: informacoes | type: COLUMN --
-- ALTER TABLE pagamento DROP COLUMN IF EXISTS informacoes CASCADE;
ALTER TABLE pagamento ADD COLUMN informacoes text;
-- ddl-end --


-- object: detalhes_pagamento | type: TABLE --
-- DROP TABLE IF EXISTS detalhes_pagamento CASCADE;
CREATE TABLE detalhes_pagamento(
	id integer NOT NULL,
	data_pagamento date NOT NULL,
	hora_pagamento date NOT NULL,
	terminal varchar(10),
	numero_transacao varchar(20),
	valor decimal(14,4) NOT NULL,
	tipo_debito smallint,
	tipo_pagamento smallint NOT NULL,
	pagamento_id integer NOT NULL,
	agencia_conta_id integer,
	CONSTRAINT detalhes_pagamento_pk PRIMARY KEY (id)

);

-- ddl-end --



-- [ Created foreign keys ] --
-- object: pagamento_id | type: CONSTRAINT --
-- ALTER TABLE detalhes_pagamento DROP CONSTRAINT IF EXISTS pagamento_id CASCADE;
ALTER TABLE detalhes_pagamento ADD CONSTRAINT pagamento_id FOREIGN KEY (pagamento_id)
REFERENCES pagamento (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: serie_id | type: CONSTRAINT --
-- ALTER TABLE aluno_serie DROP CONSTRAINT IF EXISTS serie_id CASCADE;
ALTER TABLE aluno_serie ADD CONSTRAINT serie_id FOREIGN KEY (serie_id)
REFERENCES serie (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: aluno_id | type: CONSTRAINT --
-- ALTER TABLE aluno_serie DROP CONSTRAINT IF EXISTS aluno_id CASCADE;
ALTER TABLE aluno_serie ADD CONSTRAINT aluno_id FOREIGN KEY (aluno_id)
REFERENCES aluno (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: agencia_conta_id | type: CONSTRAINT --
-- ALTER TABLE detalhes_pagamento DROP CONSTRAINT IF EXISTS agencia_conta_id CASCADE;
ALTER TABLE detalhes_pagamento ADD CONSTRAINT agencia_conta_id FOREIGN KEY (agencia_conta_id)
REFERENCES agencia_conta (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

CREATE UNIQUE INDEX uq_banco_nome_unidade_id ON banco
	USING btree
	(
	  (lower((nome)::text)),
	  unidade_id
	);
-- ddl-end --

-- object: uq_banco_codigo_unidade_id | type: INDEX --
-- DROP INDEX IF EXISTS uq_banco_codigo_unidade_id CASCADE;
CREATE UNIQUE INDEX uq_banco_codigo_unidade_id ON banco
	USING btree
	(
	  (lower((codigo)::text)),
	  unidade_id ASC NULLS LAST
	);

ALTER TABLE frequencia ALTER COLUMN nome TYPE character varying(10);
-- ddl-end --
ALTER TABLE frequencia ALTER COLUMN descricao TYPE character varying(45);
-- ddl-end --
ALTER TABLE frequencia ALTER COLUMN ordem TYPE integer USING ordem::integer;

ALTER TABLE avaliacao ALTER COLUMN nome TYPE character varying(10);
-- ddl-end --
ALTER TABLE avaliacao ALTER COLUMN descricao TYPE character varying(45);
-- ddl-end --
ALTER TABLE avaliacao ALTER COLUMN ordem TYPE integer USING ordem::integer;