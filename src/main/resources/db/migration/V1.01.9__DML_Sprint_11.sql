-- CREATE EXTENSION unaccent;

-- [ Dropped objects ] --
DROP SEQUENCE IF EXISTS detalhes_pagamento_id_seq CASCADE;
-- ddl-end --
DROP SEQUENCE IF EXISTS aluno_serie_id_seq CASCADE;
-- ddl-end --
ALTER TABLE turma DROP COLUMN IF EXISTS situacao CASCADE;
-- ddl-end --
ALTER TABLE cobranca DROP COLUMN IF EXISTS ano_mes CASCADE;
-- ddl-end --

-- object: parcela | type: COLUMN --
-- ALTER TABLE cobranca DROP COLUMN IF EXISTS parcela CASCADE;
ALTER TABLE cobranca ADD COLUMN parcela character varying(5) NOT NULL;
-- ddl-end --


-- object: ultimo_dia | type: COLUMN --
-- ALTER TABLE plano_pagamento DROP COLUMN IF EXISTS ultimo_dia CASCADE;
ALTER TABLE plano_pagamento ADD COLUMN ultimo_dia smallint DEFAULT 0;
-- ddl-end --


-- ddl-end --
ALTER TABLE endereco ALTER COLUMN logradouro DROP NOT NULL;
-- ddl-end --
ALTER TABLE endereco ALTER COLUMN numero DROP NOT NULL;
-- ddl-end --
ALTER TABLE plano_pagamento ALTER COLUMN dia_vencimento DROP NOT NULL;

UPDATE permissao SET codigo = 'PERM_COBRANCA_REALIZAR_PAGAMENTO', nome = 'perm.realizarPagamentoCobranca' WHERE id = 108;
