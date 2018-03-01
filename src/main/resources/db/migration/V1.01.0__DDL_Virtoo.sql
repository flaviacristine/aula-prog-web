CREATE TABLE turno (
  id SERIAL  NOT NULL ,
  codigo VARCHAR(10)   NOT NULL ,
  nome VARCHAR(20)   NOT NULL   ,
PRIMARY KEY(id)    );


CREATE UNIQUE INDEX UQ_TURNO_CODIGO ON turno (lower(codigo));
CREATE UNIQUE INDEX UQ_TURNO_NOME ON turno (lower(nome));



CREATE TABLE arquivo (
  id SERIAL  NOT NULL ,
  nome VARCHAR(250)   NOT NULL ,
  tipo VARCHAR(250)   NOT NULL ,
  dados BYTEA   NOT NULL   ,
PRIMARY KEY(id));



CREATE TABLE colecao_grupo_permissao (
  id SERIAL  NOT NULL ,
  nome VARCHAR(80)   NOT NULL   ,
PRIMARY KEY(id));



CREATE TABLE pais (
  id SERIAL  NOT NULL ,
  nome VARCHAR(50)   NOT NULL ,
  sigla VARCHAR(5)      ,
PRIMARY KEY(id)  );


CREATE UNIQUE INDEX UQ_PAIS_NOME ON pais (lower(nome));



CREATE TABLE periodo_letivo (
  id SERIAL  NOT NULL ,
  regime SMALLINT   NOT NULL ,
  nome VARCHAR(50)   NOT NULL ,
  data_inicial DATE   NOT NULL ,
  data_final DATE   NOT NULL   ,
PRIMARY KEY(id));



CREATE TABLE grupo_permissao (
  id SERIAL  NOT NULL ,
  colecao_grupo_permissao_id INTEGER    ,
  nome VARCHAR(50)      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(colecao_grupo_permissao_id)
    REFERENCES colecao_grupo_permissao(id));


CREATE UNIQUE INDEX UQ_GRUPO_PERMISSAO_NOME ON grupo_permissao (lower(nome));
CREATE INDEX IX_GRUPO_PERMISSAO_COLECAO_GRUPO_PERMISSAO_ID ON grupo_permissao (colecao_grupo_permissao_id);



CREATE TABLE permissao (
  id SERIAL  NOT NULL ,
  grupo_permissao_id INT   NOT NULL ,
  codigo VARCHAR(80)   NOT NULL ,
  nome VARCHAR(80)   NOT NULL   ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(grupo_permissao_id)
    REFERENCES grupo_permissao(id));


CREATE INDEX IX_PERMISSAO_GRUPO_PERMISSAO_ID ON permissao (grupo_permissao_id);
CREATE UNIQUE INDEX UQ_PERMISSAO_CODIGO ON permissao (lower(codigo));



CREATE TABLE provincia (
  id SERIAL  NOT NULL ,
  pais_id INT   NOT NULL ,
  nome VARCHAR(50)   NOT NULL ,
  sigla VARCHAR(5)      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(pais_id)
    REFERENCES pais(id));


CREATE INDEX IX_PROVINCIA_PAIS_ID ON provincia (pais_id);
CREATE UNIQUE INDEX UQ_PROVINCIA_NOME_PAIS_ID ON provincia (lower(nome), pais_id);



CREATE TABLE municipio (
  id SERIAL  NOT NULL ,
  provincia_id INT   NOT NULL ,
  nome VARCHAR(50)   NOT NULL ,
  sigla VARCHAR(5)      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(provincia_id)
    REFERENCES provincia(id));


CREATE INDEX IX_MUNICIPIO_PROVINCIA_ID ON municipio (provincia_id);
CREATE UNIQUE INDEX UQ_MUNICIPIO_NOME_PROVINCIA_ID ON municipio (lower(nome), provincia_id);



CREATE TABLE endereco (
  id SERIAL  NOT NULL ,
  municipio_id INT   NOT NULL ,
  logradouro VARCHAR(80)   NOT NULL ,
  numero VARCHAR(10)   NOT NULL ,
  bairro VARCHAR(50)   NOT NULL ,
  caixa_postal VARCHAR(20)      ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(municipio_id)
    REFERENCES municipio(id));


CREATE INDEX IX_ENDERECO_MUNICIPIO_ID ON endereco (municipio_id);



CREATE TABLE pessoa (
  id SERIAL  NOT NULL ,
  naturalidade INT   NOT NULL ,
  codigo VARCHAR(10)    ,
  nome VARCHAR(70)   NOT NULL ,
  data_nascimento DATE   NOT NULL ,
  sexo SMALLINT   NOT NULL ,
  email VARCHAR(80)   NOT NULL ,
  nome_pai VARCHAR(50)    ,
  estado_civil SMALLINT   NOT NULL ,
  nome_mae VARCHAR(50)   NOT NULL ,
  senha VARCHAR(255)    ,
  imagem VARCHAR(255)    ,
  situacao SMALLINT   NOT NULL   ,
PRIMARY KEY(id)      ,
  FOREIGN KEY(naturalidade)
    REFERENCES municipio(id));


CREATE INDEX IX_PESSOA_NATURALIDADE ON pessoa (naturalidade);
CREATE UNIQUE INDEX UQ_PESSOA_CODIGO ON pessoa (lower(codigo));
CREATE UNIQUE INDEX UQ_PESSOA_EMAIL ON pessoa (lower(email));



CREATE TABLE instituicao_escolaridade (
  id SERIAL  NOT NULL ,
  endereco_id INT   NOT NULL ,
  nome VARCHAR(80)   NOT NULL   ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(endereco_id)
    REFERENCES endereco(id));


CREATE INDEX IX_INSTITUICAO_ESCOLARIDADE_ENDERECO_ID ON instituicao_escolaridade (endereco_id);



CREATE TABLE mantenedora (
  id SERIAL  NOT NULL ,
  codigo VARCHAR(10)   NOT NULL ,
  nome VARCHAR(80)   NOT NULL ,
  endereco_id INT   NOT NULL ,
  numero_fiscal VARCHAR(20)   NOT NULL ,
  imagem VARCHAR(255)    ,
  situacao SMALLINT  DEFAULT 1    ,
PRIMARY KEY(id)      ,
  FOREIGN KEY(endereco_id)
    REFERENCES endereco(id));


CREATE INDEX IX_MANTENEDORA_ENDERECO_ID ON mantenedora (endereco_id);
CREATE UNIQUE INDEX UQ_MANTENEDORA_CODIGO ON mantenedora (lower(codigo));
CREATE UNIQUE INDEX UQ_MANTENEDORA_NOME ON mantenedora (lower(nome));



CREATE TABLE telefone (
  id SERIAL  NOT NULL ,
  pessoa_id INT   NOT NULL ,
  numero VARCHAR(25)   NOT NULL ,
  principal BOOL   NOT NULL ,
  tipo_telefone SMALLINT   NOT NULL   ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(pessoa_id)
    REFERENCES pessoa(id));


CREATE INDEX IX_TELEFONE_PESSOA_ID ON telefone (pessoa_id);



CREATE TABLE pessoa_endereco (
  id SERIAL  NOT NULL ,
  endereco_id INT   NOT NULL ,
  pessoa_id INT   NOT NULL ,
  tipo_endereco INT   NOT NULL ,
  principal BOOL      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(pessoa_id)
    REFERENCES pessoa(id),
  FOREIGN KEY(endereco_id)
    REFERENCES endereco(id));


CREATE INDEX IX_PESSOA_ENDERECO_PESSOA_ID ON pessoa_endereco (pessoa_id);
CREATE INDEX IX_PESSOA_ENDERECO_ENDERECO_ID ON pessoa_endereco (endereco_id);



CREATE TABLE documento (
  id SERIAL  NOT NULL ,
  arquivo_id INT    ,
  pessoa_id INT   NOT NULL ,
  numero VARCHAR(50)   NOT NULL ,
  tipo_documento SMALLINT   NOT NULL   ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(pessoa_id)
    REFERENCES pessoa(id),
  FOREIGN KEY(arquivo_id)
    REFERENCES arquivo(id));


CREATE INDEX IX_DOCUMENTO_PESSOA_ID ON documento (pessoa_id);
CREATE INDEX IX_DOCUMENTO_ARQUIVO_ID ON documento (arquivo_id);



CREATE TABLE instituicao (
  id SERIAL  NOT NULL ,
  codigo VARCHAR(10)   NOT NULL ,
  nome VARCHAR(80)   NOT NULL ,
  mantenedora_id INT   NOT NULL ,
  endereco_id INT   NOT NULL ,
  numero_fiscal VARCHAR(20)   NOT NULL ,
  imagem VARCHAR(255)    ,
  situacao SMALLINT  DEFAULT 1    ,
PRIMARY KEY(id)        ,
  FOREIGN KEY(mantenedora_id)
    REFERENCES mantenedora(id),
  FOREIGN KEY(endereco_id)
    REFERENCES endereco(id));


CREATE INDEX IX_INSTITUICAO_MANTENEDORA_ID ON instituicao (mantenedora_id);
CREATE INDEX IX_INSTITUICAO_ENDERECO_ID ON instituicao (endereco_id);
CREATE UNIQUE INDEX UQ_INSTITUICAO_CODIGO_MANTENEDORA_ID ON instituicao (lower(codigo), mantenedora_id);
CREATE UNIQUE INDEX UQ_INSITUICAO_NOME_MANTENEDORA_ID ON instituicao (lower(nome), mantenedora_id);



CREATE TABLE formacao (
  id SERIAL  NOT NULL ,
  instituicao_escolaridade_id INT   NOT NULL ,
  pessoa_id INT   NOT NULL ,
  formacao_media INT   NOT NULL ,
  ano_formacao VARCHAR(4)   NOT NULL ,
  curso_especialidade VARCHAR(50)      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(pessoa_id)
    REFERENCES pessoa(id),
  FOREIGN KEY(instituicao_escolaridade_id)
    REFERENCES instituicao_escolaridade(id));


CREATE INDEX IX_FORMACAO_PESSOA_ID ON formacao (pessoa_id);
CREATE INDEX IX_FORMACAO_INSTITUICAO_ESCOLARIDADE_ID ON formacao (instituicao_escolaridade_id);



CREATE TABLE unidade (
  id SERIAL  NOT NULL ,
  codigo VARCHAR(10)   NOT NULL ,
  nome VARCHAR(80)   NOT NULL ,
  endereco_id INT   NOT NULL ,
  instituicao_id INT   NOT NULL ,
  imagem VARCHAR(255)    ,
  situacao SMALLINT  DEFAULT 1    ,
PRIMARY KEY(id)        ,
  FOREIGN KEY(instituicao_id)
    REFERENCES instituicao(id),
  FOREIGN KEY(endereco_id)
    REFERENCES endereco(id));


CREATE INDEX IX_UNIDADE_INSTITUICAO_ID ON unidade (instituicao_id);
CREATE INDEX IX_UNIDADE_ENDERECO_ID ON unidade (endereco_id);
CREATE UNIQUE INDEX UQ_UNIDADE_CODIGO_INSTITUICAO_ID ON unidade (lower(codigo), instituicao_id);
CREATE UNIQUE INDEX UQ_UNIDADE_NOME_INSTITUICAO_ID ON unidade (lower(nome), instituicao_id);



CREATE TABLE horario_aula (
  id SERIAL  NOT NULL ,
  turno_id INT   NOT NULL ,
  unidade_id INT   NOT NULL   ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(unidade_id)
    REFERENCES unidade(id),
  FOREIGN KEY(turno_id)
    REFERENCES turno(id));


CREATE INDEX IX_HORARIO_UNIDADE_ID ON horario_aula (unidade_id);
CREATE INDEX IX_HORARIO_TURNO_ID ON horario_aula (turno_id);

CREATE TABLE setor (
  id SERIAL NOT NULL,
  nome VARCHAR(200) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE cargo (
  id SERIAL NOT NULL,
  nome VARCHAR(200) NOT NULL,
  tipo_perfil SMALLINT,
PRIMARY KEY(id)
);

CREATE TABLE funcionario (
  id SERIAL  NOT NULL ,
  unidade_id INT   NOT NULL ,
  pessoa_id INT   NOT NULL ,
  cargo_id INT NOT NULL  ,
  setor_id INT NOT NULL ,
  matricula VARCHAR(10)   NOT NULL ,
  titulacao SMALLINT   NOT NULL ,
  situacao SMALLINT   NOT NULL   ,
PRIMARY KEY(id)      ,
  FOREIGN KEY(pessoa_id)
    REFERENCES pessoa(id),
  FOREIGN KEY(unidade_id)
    REFERENCES unidade(id),
  FOREIGN KEY(cargo_id)
    REFERENCES cargo(id),
  FOREIGN KEY(setor_id)
    REFERENCES setor(id));


CREATE INDEX IX_FUNCIONARIO_CARGO_ID ON funcionario (cargo_id);
CREATE INDEX IX_FUNCIONARIO_PESSOA_ID ON funcionario (pessoa_id);
CREATE INDEX IX_FUNCIONARIO_UNIDADE_ID ON funcionario (unidade_id);
CREATE UNIQUE INDEX UQ_FUNCIONARIO_MATRICULA_UNIDADE_ID ON funcionario (lower(matricula), unidade_id);



CREATE TABLE horario (
  id SERIAL  NOT NULL ,
  horario_aula_id INT    ,
  duracao TIME   NOT NULL ,
  hora_inicial TIME   NOT NULL ,
  hora_final TIME   NOT NULL   ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(horario_aula_id)
    REFERENCES horario_aula(id));


CREATE INDEX IX_HORARIO_HORARIO_AULA_ID ON horario (horario_aula_id);



CREATE TABLE plano_pagamento (
  id SERIAL  NOT NULL ,
  unidade_id INT   NOT NULL ,
  nome VARCHAR(20)   NOT NULL ,
  mes_inicio SMALLINT   NOT NULL ,
  numero_parcelas SMALLINT   NOT NULL ,
  dia_vencimento SMALLINT   NOT NULL ,
  inicio_vigencia DATE    ,
  fim_vigencia DATE      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(unidade_id)
    REFERENCES unidade(id));


CREATE INDEX IX_PLANO_PAGAMENTO_UNIDADE_ID ON plano_pagamento (unidade_id);
CREATE UNIQUE INDEX UQ_PLANO_PAGAMENTO_NOME_UNIDADE_ID ON plano_pagamento (lower(nome), unidade_id);



CREATE TABLE padrao_frequencia (
  id SERIAL  NOT NULL ,
  unidade_id INT   NOT NULL ,
  nome VARCHAR(10)   NOT NULL ,
  descricao VARCHAR(45)   NOT NULL ,
  ordem INT   NOT NULL ,
  situacao SMALLINT  DEFAULT 1    ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(unidade_id)
    REFERENCES unidade(id));


CREATE INDEX IX_PADRAO_FREQUENCIA_UNIDADE_ID ON padrao_frequencia (unidade_id);
CREATE UNIQUE INDEX UQ_PADRAO_FREQUENCIA_NOME_UNIDADE_ID ON padrao_frequencia (lower(nome), unidade_id);
CREATE UNIQUE INDEX UQ_PADRAO_FREQUENCIA_ORDEM_UNIDADE_ID ON padrao_frequencia (ordem, unidade_id);



CREATE TABLE perfil (
  id SERIAL  NOT NULL ,
  unidade_id INT    ,
  nome VARCHAR(30)   NOT NULL ,
  descricao VARCHAR(200)    ,
  tipo_perfil SMALLINT   NOT NULL   ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(unidade_id)
    REFERENCES unidade(id));


CREATE INDEX IX_PERFIL_UNIDADE_ID ON perfil (unidade_id);
CREATE UNIQUE INDEX UQ_PERFIL_UNIDADE_ID_NOME ON perfil (unidade_id, lower(nome));



CREATE TABLE padrao_avaliacao (
  id SERIAL  NOT NULL ,
  unidade_id INT   NOT NULL ,
  nome VARCHAR(10)   NOT NULL ,
  descricao VARCHAR(45)   NOT NULL ,
  ordem INT  NOT NULL ,
  situacao SMALLINT  DEFAULT 1    ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(unidade_id)
    REFERENCES unidade(id));


CREATE INDEX IX_PADRAO_AVALIACAO_UNIDADE_ID ON padrao_avaliacao (unidade_id);
CREATE UNIQUE INDEX UQ_PADRAO_AVALIACAO_NOME_UNIDADE_ID ON padrao_avaliacao (lower(nome), unidade_id);
CREATE UNIQUE INDEX UQ_PADRAO_AVALIACAO_ORDEM_UNIDADE_ID ON padrao_avaliacao (ordem, unidade_id);



CREATE TABLE padrao_criterio_avaliacao (
  id SERIAL  NOT NULL ,
  unidade_id INT   NOT NULL ,
  aulas_previstas INT   NOT NULL ,
  faltas_permitidas DECIMAL(6,3)   NOT NULL ,
  nota_minima DECIMAL(6,2)   NOT NULL ,
  nota_maxima DECIMAL(6,2)   NOT NULL   ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(unidade_id)
    REFERENCES unidade(id));


CREATE INDEX IX_PADRAO_CRITERIO_AVALIACAO_UNIDADE_ID ON padrao_criterio_avaliacao (unidade_id);



CREATE TABLE bloco (
  id SERIAL  NOT NULL ,
  unidade_id INT   NOT NULL ,
  nome VARCHAR(5)   NOT NULL ,
  situacao SMALLINT   NOT NULL   ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(unidade_id)
    REFERENCES unidade(id));


CREATE INDEX IX_BLOCO_UNIDADE_ID ON bloco (unidade_id);
CREATE UNIQUE INDEX UQ_BLOCO_NOME_UNIDADE_ID ON bloco (lower(nome), unidade_id);



CREATE TABLE curso (
  id SERIAL  NOT NULL ,
  unidade_id INT   NOT NULL ,
  tipo_curso SMALLINT   NOT NULL ,
  nome VARCHAR(50)   NOT NULL ,
  imagem VARCHAR(255)    ,
  situacao SMALLINT  DEFAULT 1    ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(unidade_id)
    REFERENCES unidade(id));


CREATE INDEX IX_CURSO_UNIDADE_ID ON curso (unidade_id);
CREATE UNIQUE INDEX UQ_CURSO_NOME_TIPO_CURSO_UNIDADE_ID ON curso (lower(nome), unidade_id, tipo_curso);



CREATE TABLE banco (
  id SERIAL  NOT NULL ,
  unidade_id INT   NOT NULL ,
  nome VARCHAR(50)   NOT NULL ,
  codigo VARCHAR(5) NOT NULL  ,
  situacao SMALLINT  DEFAULT 1    ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(unidade_id)
    REFERENCES unidade(id));


CREATE INDEX IX_BANCO_UNIDADE_ID ON banco (unidade_id);



CREATE TABLE agencia_conta (
  id SERIAL  NOT NULL ,
  banco_id INT   NOT NULL ,
  agencia VARCHAR(10)   NOT NULL ,
  conta VARCHAR(16)   NOT NULL ,
  tipo_conta VARCHAR(35)   NOT NULL ,
  situacao SMALLINT  DEFAULT 1    ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(banco_id)
    REFERENCES banco(id));


CREATE INDEX IX_AGENCIA_CONTA_BANCO_ID ON agencia_conta (banco_id);
CREATE UNIQUE INDEX UQ_AGENCIA_CONTA_CONTA_BANCO_ID ON agencia_conta (conta, banco_id);



CREATE TABLE emolumentos (
  id SERIAL  NOT NULL ,
  unidade_id INT   NOT NULL ,
  nome VARCHAR(50)   NOT NULL ,
  codigo VARCHAR(10)   NOT NULL ,
  valor DECIMAL(14,4)   NOT NULL ,
  situacao SMALLINT   NOT NULL   ,
PRIMARY KEY(id)      ,
  FOREIGN KEY(unidade_id)
    REFERENCES unidade(id));


CREATE INDEX IX_EMOLUMENTOS_UNIDADE_ID ON emolumentos (unidade_id);
CREATE UNIQUE INDEX UQ_EMOLUMENTOS_CODIGO_UNIDADE_ID ON emolumentos (lower(codigo), unidade_id);
CREATE UNIQUE INDEX UQ_EMOLUMENTOS_NOME_UNIDADE_ID ON emolumentos (lower(nome), unidade_id);



CREATE TABLE disciplina (
  id SERIAL  NOT NULL ,
  unidade_id INT   NOT NULL ,
  codigo VARCHAR(10)   NOT NULL ,
  nome VARCHAR(100)   NOT NULL ,
  situacao SMALLINT  DEFAULT 1    ,
PRIMARY KEY(id)      ,
  FOREIGN KEY(unidade_id)
    REFERENCES unidade(id));


CREATE UNIQUE INDEX UQ_DISCIPLINA_CODIGO_UNIDADE_ID ON disciplina (lower(codigo), unidade_id);
CREATE INDEX IX_DISCIPLINA_UNIDADE_ID ON disciplina (unidade_id);
CREATE UNIQUE INDEX UQ_DISCIPLINA_NOME_UNIDADE_ID ON disciplina (lower(nome), unidade_id);



CREATE TABLE plano_pagamento_turno (
  plano_pagamento_id INT   NOT NULL ,
  turno_id INT   NOT NULL   ,
PRIMARY KEY(plano_pagamento_id, turno_id)    ,
  FOREIGN KEY(plano_pagamento_id)
    REFERENCES plano_pagamento(id),
  FOREIGN KEY(turno_id)
    REFERENCES turno(id));


CREATE INDEX IX_PLANO_PAGAMENTO_TURNO_PLANO_PAGAMENTO_ID ON plano_pagamento_turno (plano_pagamento_id);
CREATE INDEX IX_PLANO_PAGAMENTO_TURNO_TURNO_ID ON plano_pagamento_turno (turno_id);



CREATE TABLE curso_turno (
  id SERIAL  NOT NULL ,
  turno_id INT   NOT NULL ,
  curso_id INT   NOT NULL ,
  quantidade_vagas INT   NOT NULL   ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(curso_id)
    REFERENCES curso(id),
  FOREIGN KEY(turno_id)
    REFERENCES turno(id));


CREATE INDEX IX_CURSO_TURNO_CURSO_ID ON curso_turno (curso_id);
CREATE INDEX IX_CURSO_TURNO_TURNO_ID ON curso_turno (turno_id);



CREATE TABLE funcionario_perfil (
  funcionario_id INT   NOT NULL ,
  perfil_id INT   NOT NULL   ,
PRIMARY KEY(funcionario_id, perfil_id)    ,
  FOREIGN KEY(funcionario_id)
    REFERENCES funcionario(id),
  FOREIGN KEY(perfil_id)
    REFERENCES perfil(id));


CREATE INDEX IX_FUNCIONARIO_PERFIL_FUNCIONARIO_ID ON funcionario_perfil (funcionario_id);
CREATE INDEX IX_FUNCIONARIO_PERFIL_PERFIL_ID ON funcionario_perfil (perfil_id);



CREATE TABLE perfil_permissao (
  permissao_id INT   NOT NULL ,
  perfil_id INT   NOT NULL   ,
PRIMARY KEY(permissao_id, perfil_id)    ,
  FOREIGN KEY(perfil_id)
    REFERENCES perfil(id),
  FOREIGN KEY(permissao_id)
    REFERENCES permissao(id));


CREATE INDEX IX_PERFIL_PERMISSAO_PERFIL_ID ON perfil_permissao (perfil_id);
CREATE INDEX IX_PERFIL_PERMISSAO_PERMISSAO_ID ON perfil_permissao (permissao_id);



CREATE TABLE grade_curricular (
  id SERIAL  NOT NULL ,
  curso_turno_id INT   NOT NULL ,
  nome VARCHAR(50)   NOT NULL ,
  situacao SMALLINT    ,
  carga_horaria_total_grade SMALLINT    ,
  duracao FLOAT   NOT NULL ,
  regime SMALLINT   NOT NULL   ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(curso_turno_id)
    REFERENCES curso_turno(id));


CREATE INDEX IX_GRADE_CURRICULAR_CURSO_TURNO_ID ON grade_curricular (curso_turno_id);



CREATE TABLE piso (
  id SERIAL  NOT NULL ,
  bloco_id INT   NOT NULL ,
  nome VARCHAR(5)   NOT NULL ,
  situacao SMALLINT   NOT NULL   ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(bloco_id)
    REFERENCES bloco(id));


CREATE INDEX IX_PISO_BLOCO_ID ON piso (bloco_id);
CREATE UNIQUE INDEX UQ_PISO_NOME_BLOCO_ID ON piso (lower(nome), bloco_id);



CREATE TABLE pagamento (
  id SERIAL  NOT NULL ,
  agencia_conta_id INT    ,
  data_deposito DATE    ,
  hora_deposito TIME    ,
  numero_transacao BIGINT    ,
  valor DECIMAL(14,4)   NOT NULL ,
  tipo_pagamento SMALLINT   NOT NULL ,
  tipo_venda SMALLINT      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(agencia_conta_id)
    REFERENCES agencia_conta(id));


CREATE INDEX IX_PAGAMENTO_CONTA_ID ON pagamento (agencia_conta_id);
CREATE UNIQUE INDEX UQ_PAGAMENTO_NUMERO_TRANSACAO_AGENCIA_CONTA_ID ON pagamento (numero_transacao, tipo_pagamento);



CREATE TABLE padrao_formula_criterio (
  id SERIAL  NOT NULL ,
  padrao_criterio_avaliacao_id INT   NOT NULL ,
  formula_criterio_aprovacao VARCHAR(250)   NOT NULL ,
  conceito_minimo INT   NOT NULL   ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(padrao_criterio_avaliacao_id)
    REFERENCES padrao_criterio_avaliacao(id));


CREATE INDEX IX_PADRAO_FORMULA_CRITERIO_PADRAO_CRITERIO_AVALIACAO_ID ON padrao_formula_criterio (padrao_criterio_avaliacao_id);



CREATE TABLE sala (
  id SERIAL  NOT NULL ,
  piso_id INT   NOT NULL ,
  nome VARCHAR(5)    ,
  situacao SMALLINT   NOT NULL   ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(piso_id)
    REFERENCES piso(id));


CREATE INDEX IX_SALA_PISO_ID ON sala (piso_id);
CREATE UNIQUE INDEX UQ_SALA_NOME_PISO_ID ON sala (lower(nome), piso_id);



CREATE TABLE serie (
  id SERIAL  NOT NULL ,
  grade_curricular_id INT   NOT NULL ,
  nome SMALLINT   NOT NULL   ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(grade_curricular_id)
    REFERENCES grade_curricular(id));


CREATE INDEX IX_SERIE_GRADE_CURRICULAR_ID ON serie (grade_curricular_id);



CREATE TABLE serie_disciplina (
  id SERIAL  NOT NULL ,
  serie_id INT   NOT NULL ,
  prerequisito INT    ,
  disciplina_id INT   NOT NULL ,
  carga_horaria_total INT   NOT NULL ,
  carga_horaria_semanal INT   NOT NULL ,
  carga_horaria_teorica SMALLINT   NOT NULL ,
  carga_horaria_pratica SMALLINT      ,
PRIMARY KEY(id)        ,
  FOREIGN KEY(disciplina_id)
    REFERENCES disciplina(id),
  FOREIGN KEY(prerequisito)
    REFERENCES serie_disciplina(id),
  FOREIGN KEY(serie_id)
    REFERENCES serie(id));


CREATE INDEX IX_SEIRE_DISCIPLINA_DISCIPLINA_ID ON serie_disciplina (disciplina_id);
CREATE INDEX IX_SERIE_DISCIPLINA_ID ON serie_disciplina (prerequisito);
CREATE INDEX IX_SERIE_DISCIPLINA_SERIE_ID ON serie_disciplina (serie_id);
CREATE UNIQUE INDEX UQ_SERIE_DISCIPLINA_ID ON serie_disciplina (serie_id, disciplina_id);



CREATE TABLE valor_curso (
  id SERIAL  NOT NULL ,
  periodo_letivo_id INT   NOT NULL ,
  serie_id INT   NOT NULL ,
  custo DOUBLE PRECISION   NOT NULL   ,
PRIMARY KEY(id)      ,
  FOREIGN KEY(serie_id)
    REFERENCES serie(id),
  FOREIGN KEY(periodo_letivo_id)
    REFERENCES periodo_letivo(id));


CREATE INDEX IX_VALOR_CURSO_SERIE_ID ON valor_curso (serie_id);
CREATE INDEX IX_VALOR_CURSO_PERIODO_LETIVO_ID ON valor_curso (periodo_letivo_id);
CREATE UNIQUE INDEX UQ_VALOR_CURSO_SERIE_PERIODO_LETIVO_ID ON valor_curso (serie_id, periodo_letivo_id);



CREATE TABLE curso_turno_plano_pagamento (
  curso_turno_id INT   NOT NULL ,
  plano_pagamento_id INT   NOT NULL   ,
PRIMARY KEY(curso_turno_id, plano_pagamento_id)    ,
  FOREIGN KEY(curso_turno_id)
    REFERENCES curso_turno(id),
  FOREIGN KEY(plano_pagamento_id)
    REFERENCES plano_pagamento(id));


CREATE INDEX IX_CURSO_TURNO_PLANO_PAGAMENTO_CURSO_TURNO_ID ON curso_turno_plano_pagamento (curso_turno_id);
CREATE INDEX IX_CURSO_TURNO_PLANO_PAGAMENTO_PLANO_PAGAMENTO_ID ON curso_turno_plano_pagamento (plano_pagamento_id);



CREATE TABLE disciplina_equivalencia (
  serie_disciplina_id INT   NOT NULL ,
  equivalencia_id INT   NOT NULL   ,
PRIMARY KEY(serie_disciplina_id, equivalencia_id)    ,
  FOREIGN KEY(serie_disciplina_id)
    REFERENCES serie_disciplina(id),
  FOREIGN KEY(equivalencia_id)
    REFERENCES serie_disciplina(id));


CREATE INDEX IX_DISC_EQUIVALENCIA_SERIE_DISC_ID ON disciplina_equivalencia (serie_disciplina_id);
CREATE INDEX IX_DISC_EQUIVALENCIA_EQUIVALENCIA_ID ON disciplina_equivalencia (equivalencia_id);



CREATE TABLE grade_curricular_plano_pagamento (
  grade_curricular_id INT   NOT NULL ,
  plano_pagamento_id INT   NOT NULL   ,
PRIMARY KEY(grade_curricular_id, plano_pagamento_id)    ,
  FOREIGN KEY(grade_curricular_id)
    REFERENCES grade_curricular(id),
  FOREIGN KEY(plano_pagamento_id)
    REFERENCES plano_pagamento(id));


CREATE INDEX IX_GRADE_CURRICULAR_PLANO_PAGAMENTO_GRADE_CURRICULAR_ID ON grade_curricular_plano_pagamento (grade_curricular_id);
CREATE INDEX IX_GRADE_CURRICULAR_PLANO_PAGAMENTO_PLANO_PAGAMENTO_ID ON grade_curricular_plano_pagamento (plano_pagamento_id);



CREATE TABLE turma (
  id SERIAL  NOT NULL ,
  serie_id INT   NOT NULL ,
  periodo_letivo_id INT   NOT NULL ,
  unidade_id INT   NOT NULL ,
  nome VARCHAR(100)   NOT NULL ,
  status_turma SMALLINT      ,
PRIMARY KEY(id)      ,
  FOREIGN KEY(unidade_id)
    REFERENCES unidade(id),
  FOREIGN KEY(periodo_letivo_id)
    REFERENCES periodo_letivo(id),
  FOREIGN KEY(serie_id)
    REFERENCES serie(id));


CREATE INDEX IX_TURMA_UNIDADE_ID ON turma (unidade_id);
CREATE INDEX IX_TURMA_PERIODO_LETIVO_ID ON turma (periodo_letivo_id);
CREATE INDEX IX_TURMA_SERIE_ID ON turma (serie_id);



CREATE TABLE serie_disciplina_turma (
  id SERIAL  NOT NULL ,
  funcionario_id INT   NOT NULL ,
  serie_disciplina_id INT   NOT NULL ,
  turma_id INT   NOT NULL ,
  quantidade_max_aluno INT   NOT NULL ,
  didatica SMALLINT   NOT NULL   ,
PRIMARY KEY(id)      ,
  FOREIGN KEY(serie_disciplina_id)
    REFERENCES serie_disciplina(id),
  FOREIGN KEY(turma_id)
    REFERENCES turma(id),
  FOREIGN KEY(funcionario_id)
    REFERENCES funcionario(id));


CREATE INDEX IX_SERIE_DISC_TURMA_GRADE_CURRICULAR_DISC_ID ON serie_disciplina_turma (serie_disciplina_id);
CREATE INDEX IX_SERIE_DISC_TURMA_TURMA_ID ON serie_disciplina_turma (turma_id);
CREATE INDEX IX_SERIE_DISCIPLINA_TURMA_FUNCIONARIO_ID ON serie_disciplina_turma (funcionario_id);



CREATE TABLE serie_disciplina_turma_sala_horario (
  id SERIAL  NOT NULL ,
  serie_disciplina_turma_id INT   NOT NULL ,
  sala_id INT   NOT NULL ,
  horario_aula_id INT   NOT NULL ,
  dia_semana SMALLINT   NOT NULL   ,
PRIMARY KEY(id)          ,
  FOREIGN KEY(serie_disciplina_turma_id)
    REFERENCES serie_disciplina_turma(id),
  FOREIGN KEY(horario_aula_id)
    REFERENCES horario_aula(id),
  FOREIGN KEY(sala_id)
    REFERENCES sala(id));


CREATE INDEX IX_SERIE_DISCIPLINA_TURMA_SALA_HORARIO_SERIE_DISC_TURMA_ID ON serie_disciplina_turma_sala_horario (serie_disciplina_turma_id);
CREATE INDEX IX_SERIE_DISCIPLINA_TURMA_SALA_HORARIO_HORARIO_ID ON serie_disciplina_turma_sala_horario (horario_aula_id);
CREATE INDEX IX_SERIE_DISCIPLINA_TURMA_SALA_HORARIO_SALA_ID ON serie_disciplina_turma_sala_horario (sala_id);
CREATE UNIQUE INDEX UQ_SERIE_DISCIPLINA_TURMA_SALA_HORARIO_DIA_SEMANA_ID ON serie_disciplina_turma_sala_horario (serie_disciplina_turma_id, sala_id, horario_aula_id, dia_semana);
CREATE UNIQUE INDEX UQ_SALA_HORARIO_DIA_SEMANA_ID ON serie_disciplina_turma_sala_horario (sala_id, horario_aula_id, dia_semana);



CREATE TABLE aluno (
  id SERIAL  NOT NULL ,
  unidade_id INT   NOT NULL ,
  grade_curricular_id INT   NOT NULL ,
  periodo_letivo_id INT   NOT NULL ,
  pessoa_id INT   NOT NULL ,
  curso_turno_id INT   NOT NULL ,
  data_matricula DATE    ,
  status_matricula SMALLINT   NOT NULL ,
  data_candidatura DATE   NOT NULL ,
  periodo_ingresso DATE   NOT NULL ,
  serie_atual SMALLINT   NOT NULL ,
  matricula VARCHAR(15)    ,
  situacao SMALLINT      ,
PRIMARY KEY(id)            ,
  FOREIGN KEY(curso_turno_id)
    REFERENCES curso_turno(id),
  FOREIGN KEY(pessoa_id)
    REFERENCES pessoa(id),
  FOREIGN KEY(periodo_letivo_id)
    REFERENCES periodo_letivo(id),
  FOREIGN KEY(grade_curricular_id)
    REFERENCES grade_curricular(id),
  FOREIGN KEY(unidade_id)
    REFERENCES unidade(id));


CREATE INDEX IX_ALUNO_CURSO_TURNO_ID ON aluno (curso_turno_id);
CREATE INDEX IX_ALUNO_PESSOA_ID ON aluno (pessoa_id);
CREATE INDEX IX_ALUNO_PERIODO_LETIVO_ID ON aluno (periodo_letivo_id);
CREATE INDEX IX_ALUNO_GRADE_CURRICULAR_ID ON aluno (grade_curricular_id);
CREATE INDEX IX_ALUNO_UNIDADE_ID ON aluno (unidade_id);
CREATE UNIQUE INDEX UQ_ALUNO_MATRICULA_UNIDADE_ID ON aluno (lower(matricula), unidade_id);



CREATE TABLE frequencia (
  id SERIAL  NOT NULL ,
  serie_disciplina_turma_id INT   NOT NULL ,
  nome VARCHAR(5)   NOT NULL ,
  descricao VARCHAR(25)   NOT NULL ,
  ordem VARCHAR(2)   NOT NULL ,
  situacao SMALLINT  DEFAULT 1    ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(serie_disciplina_turma_id)
    REFERENCES serie_disciplina_turma(id));


CREATE INDEX IX_FREQUENCIA_SERIE_DISCIPLINA_TURMA_ID ON frequencia (serie_disciplina_turma_id);
CREATE UNIQUE INDEX UQ_FREQUENCIA_NOME_SERIE_DISCIPLINA_TURMA_ID ON frequencia (lower(nome), serie_disciplina_turma_id);



CREATE TABLE  criterio_avaliacao (
  id SERIAL  NOT NULL ,
  serie_disciplina_turma_id INT   NOT NULL ,
  aulas_previstas INT   NOT NULL ,
  faltas_permitidas DECIMAL(6,3)   NOT NULL ,
  nota_minima DECIMAL(6,2)   NOT NULL ,
  nota_maxima DECIMAL(6,2)   NOT NULL   ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(serie_disciplina_turma_id)
    REFERENCES serie_disciplina_turma(id));


CREATE INDEX IX_CRITERIO_AVALIACAO_SERIE_DISCIPLINA_TURMA_ID ON  criterio_avaliacao (serie_disciplina_turma_id);



CREATE TABLE   formula_criterio (
  id SERIAL  NOT NULL ,
   criterio_avaliacao_id INT   NOT NULL ,
  formula_criterio_aprovacao VARCHAR(250)   NOT NULL ,
  conceito_minimo INT   NOT NULL   ,
PRIMARY KEY(id)  ,
  FOREIGN KEY( criterio_avaliacao_id)
    REFERENCES  criterio_avaliacao(id));


CREATE INDEX IX_FORMULA_CRITERIO_CRITERIO_AVALIACAO_ID ON   formula_criterio ( criterio_avaliacao_id);



CREATE TABLE avaliacao (
  id SERIAL  NOT NULL ,
  serie_disciplina_turma_id INT   NOT NULL ,
  nome VARCHAR(5)   NOT NULL ,
  descricao VARCHAR(25)   NOT NULL ,
  ordem VARCHAR(2)   NOT NULL ,
  situacao SMALLINT  DEFAULT 1    ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(serie_disciplina_turma_id)
    REFERENCES serie_disciplina_turma(id));


CREATE INDEX IX_AVALIACAO_SERIE_DISCIPLINA_TURMA_ID ON avaliacao (serie_disciplina_turma_id);
CREATE UNIQUE INDEX UQ_AVALIACAO_NOME_SERIE_DISCIPLINA_TURMA_ID ON avaliacao (lower(nome), serie_disciplina_turma_id);



CREATE TABLE responsavel (
  id SERIAL  NOT NULL ,
  pessoa_id INT   NOT NULL ,
  aluno_id INT   NOT NULL   ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(aluno_id)
    REFERENCES aluno(id),
  FOREIGN KEY(pessoa_id)
    REFERENCES pessoa(id));


CREATE INDEX IX_RESPONSAVEL_ALUNO_ID ON responsavel (aluno_id);
CREATE INDEX IX_RESPONSAVEL_PESSOA_ID ON responsavel (pessoa_id);



CREATE TABLE aluno_perfil (
  aluno_id INT   NOT NULL ,
  perfil_id INT   NOT NULL   ,
PRIMARY KEY(aluno_id, perfil_id)    ,
  FOREIGN KEY(aluno_id)
    REFERENCES aluno(id),
  FOREIGN KEY(perfil_id)
    REFERENCES perfil(id));


CREATE INDEX IX_ALUNO_PERFIL_ALUNO_ID ON aluno_perfil (aluno_id);
CREATE INDEX IX_ALUNO_PERFIL_PERFIL_ID ON aluno_perfil (perfil_id);



CREATE TABLE aluno_serie_disciplina_turma (
  aluno_id INT   NOT NULL ,
  serie_disciplina_turma_id INT   NOT NULL   ,
PRIMARY KEY(aluno_id, serie_disciplina_turma_id)      ,
  FOREIGN KEY(aluno_id)
    REFERENCES aluno(id),
  FOREIGN KEY(serie_disciplina_turma_id)
    REFERENCES serie_disciplina_turma(id));


CREATE INDEX IX_ALUNO_SERIE_DISCIPLINA_TURMA_ALUNO_ID ON aluno_serie_disciplina_turma (aluno_id);
CREATE INDEX IX_ALUNO_SERIE_DISCIPLINA_TURMA_SERIE_DISCIPLINA_TURMA_ID ON aluno_serie_disciplina_turma (serie_disciplina_turma_id);
CREATE UNIQUE INDEX UQ_ALUNO_SERIE_DISCIPLINA_TURMA_ID ON aluno_serie_disciplina_turma (aluno_id, serie_disciplina_turma_id);



CREATE TABLE divida (
  id SERIAL  NOT NULL ,
  emolumentos_id INT    ,
  aluno_id INT   NOT NULL ,
  plano_pagamento_id INT   NOT NULL ,
  descricao VARCHAR(70)   NOT NULL ,
  tipo_divida SMALLINT   NOT NULL ,
  valor_total DECIMAL(14,4)   NOT NULL ,
  desconto DECIMAL(14,4)      ,
PRIMARY KEY(id)      ,
  FOREIGN KEY(plano_pagamento_id)
    REFERENCES plano_pagamento(id),
  FOREIGN KEY(aluno_id)
    REFERENCES aluno(id),
  FOREIGN KEY(emolumentos_id)
    REFERENCES emolumentos(id));


CREATE INDEX IX_DIVIDA_PLANO_PAGAMENTO_ID ON divida (plano_pagamento_id);
CREATE INDEX IX_DIVIDA_MATRICULA_ID ON divida (aluno_id);
CREATE INDEX IX_DIVIDA_EMOLUMENTOS_ID ON divida (emolumentos_id);



CREATE TABLE cobranca (
  id SERIAL  NOT NULL ,
  divida_id INT   NOT NULL ,
  data_vencimento DATE   NOT NULL ,
  ano_mes VARCHAR(7)   NOT NULL ,
  valor_cobranca DECIMAL(14,4)   NOT NULL   ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(divida_id)
    REFERENCES divida(id));


CREATE INDEX IX_COBRANCA_DIVIDA_ID ON cobranca (divida_id);



CREATE TABLE responsavel_financeiro (
  id SERIAL  NOT NULL ,
  divida_id INT   NOT NULL ,
  nome VARCHAR(50)      ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(divida_id)
    REFERENCES divida(id));


CREATE INDEX IX_RESPONSAVEL_FINANCEIRO_DIVIDA_ID ON responsavel_financeiro (divida_id);



CREATE TABLE cobranca_pagamento (
  cobranca_id INT   NOT NULL ,
  pagamento_id INT   NOT NULL   ,
PRIMARY KEY(cobranca_id, pagamento_id)    ,
  FOREIGN KEY(cobranca_id)
    REFERENCES cobranca(id),
  FOREIGN KEY(pagamento_id)
    REFERENCES pagamento(id));


CREATE INDEX IX_COBRANCA_PAGAMENTO_COBRANCA_ID ON cobranca_pagamento (cobranca_id);
CREATE INDEX IX_COBRANCA_PAGAMENTO_PAGAMENTO_ID ON cobranca_pagamento (pagamento_id);




