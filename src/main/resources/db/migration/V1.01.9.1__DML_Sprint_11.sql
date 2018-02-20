
ALTER TABLE plano_pagamento ADD COLUMN quantidade_dias_vencimento integer NOT NULL;
CREATE SEQUENCE aluno_serie_id_seq
	MINVALUE 0
	MAXVALUE 2147483647
;
ALTER TABLE aluno_serie ALTER COLUMN id SET DEFAULT nextval('aluno_serie_id_seq'::regclass);
