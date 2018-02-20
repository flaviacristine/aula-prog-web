UPDATE permissao set nome = 'perm.visualizarDisciplina' WHERE id = 46;

UPDATE permissao set nome = 'perm.alterarPlanoDePagamento' WHERE id = 91;

ALTER TABLE disciplina_equivalencia DROP CONSTRAINT IF EXISTS disciplina_equivalencia_equivalencia_id_fkey CASCADE;

ALTER TABLE disciplina_equivalencia DROP CONSTRAINT IF EXISTS disciplina_equivalencia_serie_disciplina_id_fkey CASCADE;

ALTER TABLE disciplina_equivalencia DROP CONSTRAINT IF EXISTS disciplina_equivalencia_pkey CASCADE;

DROP INDEX IF EXISTS ix_disc_equivalencia_equivalencia_id CASCADE;

DROP INDEX IF EXISTS ix_disc_equivalencia_serie_disc_id CASCADE;

ALTER TABLE pessoa DROP COLUMN IF EXISTS imagem CASCADE;

ALTER TABLE mantenedora DROP COLUMN IF EXISTS imagem CASCADE;

ALTER TABLE instituicao DROP COLUMN IF EXISTS imagem CASCADE;

ALTER TABLE unidade DROP COLUMN IF EXISTS imagem CASCADE;

ALTER TABLE padrao_frequencia DROP COLUMN IF EXISTS situacao CASCADE;

ALTER TABLE padrao_avaliacao DROP COLUMN IF EXISTS situacao CASCADE;

ALTER TABLE aluno DROP COLUMN IF EXISTS situacao CASCADE;

ALTER TABLE disciplina_equivalencia DROP COLUMN IF EXISTS equivalencia_id CASCADE;

ALTER TABLE disciplina_equivalencia ADD COLUMN disciplina_id integer NOT NULL;

ALTER TABLE pessoa ADD COLUMN id_arquivo integer;

ALTER TABLE instituicao ADD COLUMN id_arquivo integer;

ALTER TABLE mantenedora ADD COLUMN id_arquivo integer;

ALTER TABLE unidade ADD COLUMN id_arquivo integer;

ALTER TABLE plano_pagamento ALTER COLUMN quantidade_dias_vencimento TYPE smallint;

ALTER TABLE plano_pagamento ALTER COLUMN quantidade_dias_vencimento DROP NOT NULL;

ALTER SEQUENCE aluno_serie_id_seq START WITH 1;

ALTER TABLE disciplina_equivalencia ADD CONSTRAINT disciplina_equivalencia_pk PRIMARY KEY (serie_disciplina_id,disciplina_id);

ALTER TABLE pessoa ADD CONSTRAINT pessoa_uq UNIQUE (id_arquivo);

ALTER TABLE instituicao ADD CONSTRAINT instituicao_uq UNIQUE (id_arquivo);

ALTER TABLE mantenedora ADD CONSTRAINT mantenedora_uq UNIQUE (id_arquivo);

ALTER TABLE unidade ADD CONSTRAINT unidade_uq UNIQUE (id_arquivo);

ALTER TABLE disciplina_equivalencia ADD CONSTRAINT serie_disciplina_id FOREIGN KEY (serie_disciplina_id)
REFERENCES serie_disciplina (id);

ALTER TABLE disciplina_equivalencia ADD CONSTRAINT disciplina_id FOREIGN KEY (disciplina_id)
REFERENCES disciplina (id);

ALTER TABLE pessoa ADD CONSTRAINT arquivo_fk FOREIGN KEY (id_arquivo)
REFERENCES arquivo (id);

ALTER TABLE instituicao ADD CONSTRAINT arquivo_fk FOREIGN KEY (id_arquivo)
REFERENCES arquivo (id);

ALTER TABLE mantenedora ADD CONSTRAINT arquivo_fk FOREIGN KEY (id_arquivo)
REFERENCES arquivo (id);

ALTER TABLE unidade ADD CONSTRAINT arquivo_fk FOREIGN KEY (id_arquivo)
REFERENCES arquivo (id);