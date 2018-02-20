-- select * from pessoa

INSERT INTO pessoa VALUES(4, 63712, 'AM', 'Ajax Monteiro', '1996-05-01', 0, 'ajax.monteiro@email.com', 'Jason Firmino', 0, 'Jay Keri da Silva', '$2a$06$Lj0hTAo0rECQSLwJIPpVde6dO21KhRe.HmIIjDZSLpVihEkhXMkkC', null, 1);
INSERT INTO pessoa VALUES(5, 63712, 'OK', 'Obi-Wan Kenobi', '1922-08-26', 0, 'obiwankenobi@starwars.com', 'Ewan McGregor', 1, 'Yoda Fontane', '$2a$06$Lj0hTAo0rECQSLwJIPpVde6dO21KhRe.HmIIjDZSLpVihEkhXMkkC', null, 1);
INSERT INTO pessoa VALUES(6, 63712, 'JP', 'Joana Piora', '1984-05-13', 1, 'joanapiora2009@hotmail.com', 'João Piora', 1, 'Maria Piora', '$2a$06$Lj0hTAo0rECQSLwJIPpVde6dO21KhRe.HmIIjDZSLpVihEkhXMkkC', null, 1);
INSERT INTO pessoa VALUES(7, 63712, 'SS', 'Silvia Silva', '1993-04-10', 1, 'silvia_lindinha93@hotmail.com', 'Mauro Silva', 0, 'Janaina Silva', '$2a$06$Lj0hTAo0rECQSLwJIPpVde6dO21KhRe.HmIIjDZSLpVihEkhXMkkC', null, 1);

-- select * from documento

INSERT INTO documento VALUES(4, null, 4, '0000000', 1);
INSERT INTO documento VALUES(5, null, 5, '0000000', 1);
INSERT INTO documento VALUES(6, null, 6, '0000000', 1);
INSERT INTO documento VALUES(7, null, 7, '0000000', 1);

-- select * from curso

INSERT INTO curso VALUES(1, 1, 2, 'Engenharia de Computação', null, 1);
INSERT INTO curso VALUES(2, 1, 2, 'Engenharia Civil', null, 1);
INSERT INTO curso VALUES(3, 1, 2, 'Engenharia Mecânica', null, 1);
INSERT INTO curso VALUES(4, 1, 2, 'Psicologia', null, 1);
INSERT INTO curso VALUES(5, 1, 2, 'Direito', null, 1);
INSERT INTO curso VALUES(6, 1, 2, 'Medicina', null, 1);
INSERT INTO curso VALUES(7, 1, 2, 'Arquitetura e Urbanismo', null, 1);

-- select * from curso_turno

INSERT INTO curso_turno VALUES(1, 3, 1, 60);
INSERT INTO curso_turno VALUES(2, 3, 2, 60);
INSERT INTO curso_turno VALUES(3, 3, 3, 60);
INSERT INTO curso_turno VALUES(4, 2, 4, 60);
INSERT INTO curso_turno VALUES(5, 3, 5, 60);
INSERT INTO curso_turno VALUES(6, 1, 5, 60);
INSERT INTO curso_turno VALUES(7, 1, 6, 60);
INSERT INTO curso_turno VALUES(8, 3, 6, 60);
INSERT INTO curso_turno VALUES(9, 1, 7, 60);
INSERT INTO curso_turno VALUES(10, 2, 7, 60);

-- select * from grade_curricular

INSERT INTO grade_curricular VALUES(1, 1, 'Grade A', 1, 1500, 5, 0);
INSERT INTO grade_curricular VALUES(2, 5, 'Grade A', 1, 1500, 5, 0);
INSERT INTO grade_curricular VALUES(3, 9, 'Grade A', 1, 1500, 5, 0);
INSERT INTO grade_curricular VALUES(4, 4, 'Grade A', 1, 1100, 4, 0);

-- select * from serie

INSERT INTO serie VALUES(1, 1, 1);
INSERT INTO serie VALUES(2, 1, 2);
INSERT INTO serie VALUES(3, 1, 3);
INSERT INTO serie VALUES(4, 1, 4);
INSERT INTO serie VALUES(5, 1, 5);
INSERT INTO serie VALUES(6, 1, 6);
INSERT INTO serie VALUES(7, 1, 7);
INSERT INTO serie VALUES(8, 1, 8);
INSERT INTO serie VALUES(9, 1, 9);
INSERT INTO serie VALUES(10, 1, 10);

INSERT INTO serie VALUES(11, 2, 1);
INSERT INTO serie VALUES(12, 2, 2);
INSERT INTO serie VALUES(13, 2, 3);
INSERT INTO serie VALUES(14, 2, 4);
INSERT INTO serie VALUES(15, 2, 5);
INSERT INTO serie VALUES(16, 2, 6);
INSERT INTO serie VALUES(17, 2, 7);
INSERT INTO serie VALUES(18, 2, 8);
INSERT INTO serie VALUES(19, 2, 9);
INSERT INTO serie VALUES(20, 2, 10);

INSERT INTO serie VALUES(21, 3, 1);
INSERT INTO serie VALUES(22, 3, 2);
INSERT INTO serie VALUES(23, 3, 3);
INSERT INTO serie VALUES(24, 3, 4);
INSERT INTO serie VALUES(25, 3, 5);
INSERT INTO serie VALUES(26, 3, 6);
INSERT INTO serie VALUES(27, 3, 7);
INSERT INTO serie VALUES(28, 3, 8);
INSERT INTO serie VALUES(29, 3, 9);
INSERT INTO serie VALUES(30, 3, 10);

INSERT INTO serie VALUES(31, 4, 1);
INSERT INTO serie VALUES(32, 4, 2);
INSERT INTO serie VALUES(33, 4, 3);
INSERT INTO serie VALUES(34, 4, 4);
INSERT INTO serie VALUES(35, 4, 5);
INSERT INTO serie VALUES(36, 4, 6);
INSERT INTO serie VALUES(37, 4, 7);
INSERT INTO serie VALUES(38, 4, 8);

-- select * from periodo_letivo

INSERT INTO periodo_letivo VALUES(1, 0, '2014/2', '2014-07-01', '2019-12-31');
INSERT INTO periodo_letivo VALUES(2, 0, '2015/2', '2015-07-01', '2020-12-31');
INSERT INTO periodo_letivo VALUES(3, 0, '2017/2', '2017-07-01', '2022-12-21');

-- select * from aluno

INSERT INTO aluno VALUES(1, 1, 4, 1, '2014-07-27', 1, '2014-06-26', '2014-08-01', 7, '1400001', 1);--1,1 gra_curr, per_let
INSERT INTO aluno VALUES(2, 1, 5, 5, '2014-07-27', 1, '2014-06-26', '2014-08-01', 7, '1400002', 1);--2,1
INSERT INTO aluno VALUES(3, 1, 6, 9, '2015-07-27', 1, '2015-06-26', '2015-08-01', 5, '1500001', 1);--3,2
INSERT INTO aluno VALUES(4, 1, 7, 4, '2017-07-27', 1, '2017-06-26', '2017-08-01', 1, '1700001', 1);--4,3

-- select * from aluno_serie

INSERT INTO aluno_serie VALUES(1, 7, 1, 1);
INSERT INTO aluno_serie VALUES(2, 17, 2, 1);
INSERT INTO aluno_serie VALUES(3, 25, 3, 2);
INSERT INTO aluno_serie VALUES(4, 21, 4, 3);

-- select * from plano_pagamento

INSERT INTO plano_pagamento VALUES(1, 1, '2014/2 mensal', 7, 6, 18, '2014-07-01', '2014-12-31');
INSERT INTO plano_pagamento VALUES(2, 1, '2015/2 mensal', 7, 6, 18, '2015-07-01', '2015-12-31');
INSERT INTO plano_pagamento VALUES(3, 1, '2017/2 mensal', 7, 6, 18, '2017-07-01', '2017-12-31');

-- select * from curso_turno_plano_pagamento

INSERT INTO curso_turno_plano_pagamento VALUES(1, 1);
INSERT INTO curso_turno_plano_pagamento VALUES(5, 1);
INSERT INTO curso_turno_plano_pagamento VALUES(9, 2);
INSERT INTO curso_turno_plano_pagamento VALUES(4, 3);


-- select * from grade_curricular_plano_pagamento

INSERT INTO grade_curricular_plano_pagamento VALUES(1, 1);
INSERT INTO grade_curricular_plano_pagamento VALUES(2, 1);
INSERT INTO grade_curricular_plano_pagamento VALUES(3, 2);
INSERT INTO grade_curricular_plano_pagamento VALUES(4, 3);

-- select * from emolumentos

INSERT INTO emolumentos VALUES(1, 1, 'Matrícula', 254631, 1020.00, 1);

-- select * from divida

INSERT INTO divida VALUES(1, 1, 1, 1, 'Matrícula Série', 0, 6120.00);-- 150,00 desconto
INSERT INTO divida VALUES(2, 1, 2, 1, 'Matrícula Série', 0, 6120.00);-- 150,00 desconto
INSERT INTO divida VALUES(3, 1, 3, 2, 'Matrícula Série', 0, 6120.00);-- 0 desconto
INSERT INTO divida VALUES(4, 1, 4, 3, 'Matrícula Série', 0, 6120.00);-- 0 desconto

-- select * from banco

INSERT INTO banco VALUES(1, 1, 'Banco do Brasil', 'BB', 1);
INSERT INTO banco VALUES(2, 1, 'Banco Angolano de Investimentos', 'BAI', 1);
INSERT INTO banco VALUES(3, 1, 'Banco Angola de Negócios e Comércio', 'BANC', 1);
INSERT INTO banco VALUES(4, 1, 'Banco BAI Microfinanças', 'BMF', 1);
INSERT INTO banco VALUES(5, 1, 'Banco BIC', 'BIC', 1);
INSERT INTO banco VALUES(6, 1, 'Caixa Angola', 'BCGA', 1);
INSERT INTO banco VALUES(7, 1, 'Caixa Econômica Federal', 'CEF', 1);
INSERT INTO banco VALUES(8, 1, 'Banco Nacional de Angola', 'BNA', 1);

-- select * from agencia_conta

INSERT INTO agencia_conta VALUES(1, 6, 3513, 324384, 1, 1);
INSERT INTO agencia_conta VALUES(2, 6, 3558, 682716, 1, 1);
INSERT INTO agencia_conta VALUES(3, 8, 1464, 639876, 1, 1);
INSERT INTO agencia_conta VALUES(4, 8, 1855, 246884, 1, 1);

-- select * from cobranca

INSERT INTO cobranca VALUES(1, 1, '2014-07-18', '14/07', 995.00, 995.00, 1);
INSERT INTO cobranca VALUES(2, 1, '2014-08-18', '14/08', 995.00, 995.00, 1);
INSERT INTO cobranca VALUES(3, 1, '2014-09-18', '14/09', 995.00, 995.00, 1);
INSERT INTO cobranca VALUES(4, 1, '2014-10-18', '14/10', 995.00, 995.00, 1);
INSERT INTO cobranca VALUES(5, 1, '2014-11-18', '14/11', 995.00, 995.00, 1);
INSERT INTO cobranca VALUES(6, 1, '2014-12-18', '14/12', 995.00, 995.00, 1);

INSERT INTO cobranca VALUES(7, 2, '2014-07-18', '14/07', 995.00, 995.00, 1);
INSERT INTO cobranca VALUES(8, 2, '2014-08-18', '14/08', 995.00, 995.00, 1);
INSERT INTO cobranca VALUES(9, 2, '2014-09-18', '14/09', 995.00, 995.00, 1);
INSERT INTO cobranca VALUES(10, 2, '2014-10-18', '14/10', 995.00, 995.00, 1);
INSERT INTO cobranca VALUES(11, 2, '2014-11-18', '14/11', 995.00, 995.00, 1);
INSERT INTO cobranca VALUES(12, 2, '2014-12-18', '14/12', 995.00, 995.00, 1);

INSERT INTO cobranca VALUES(13, 3, '2015-07-18', '15/07', 1020.00, 1020.00, 1);
INSERT INTO cobranca VALUES(14, 3, '2015-08-18', '15/08', 1020.00, 1020.00, 1);
INSERT INTO cobranca VALUES(15, 3, '2015-09-18', '15/09', 1020.00, 1020.00, 1);
INSERT INTO cobranca VALUES(16, 3, '2015-10-18', '15/10', 1020.00, 1020.00, 1);
INSERT INTO cobranca VALUES(17, 3, '2015-11-18', '15/11', 1020.00, 1020.00, 1);
INSERT INTO cobranca VALUES(18, 3, '2015-12-18', '15/12', 1020.00, 1020.00, 1);

INSERT INTO cobranca VALUES(19, 4, '2017-07-18', '17/07', 1020.00, 1020.00, 1);
INSERT INTO cobranca VALUES(20, 4, '2017-08-18', '17/08', 1020.00, 1020.00, 1);
INSERT INTO cobranca VALUES(21, 4, '2017-09-18', '17/09', 1020.00, 1020.00, 1);
INSERT INTO cobranca VALUES(22, 4, '2017-10-18', '17/10', 1020.00, 1020.00, 1);
INSERT INTO cobranca VALUES(23, 4, '2017-11-18', '17/11', 1020.00, 1020.00, 1);
INSERT INTO cobranca VALUES(24, 4, '2017-12-18', '17/12', 1020.00, 1020.00, 1);

-- select * from pagamento

INSERT INTO pagamento VALUES(1, 5970.00, 'Pagamento Semestre');

INSERT INTO pagamento VALUES(2, 5970.00, 'Pagamento Semestre');

INSERT INTO pagamento VALUES(3, 1020.00, 'Mensalidade Julho');
INSERT INTO pagamento VALUES(4, 1020.00, 'Mensalidade Agosto');
INSERT INTO pagamento VALUES(5, 1020.00, 'Mensalidade Setembro');
INSERT INTO pagamento VALUES(6, 1020.00, 'Mensalidade Outubro');
INSERT INTO pagamento VALUES(7, 1020.00, 'Mensalidade Novembro');
INSERT INTO pagamento VALUES(8, 1020.00, 'Mensalidade Dezembro');

INSERT INTO pagamento VALUES(9, 1020.00, 'Mensalidade Julho');
INSERT INTO pagamento VALUES(10, 1020.00, 'Mensalidade Agosto');
INSERT INTO pagamento VALUES(11, 1020.00, 'Mensalidade Setembro');
INSERT INTO pagamento VALUES(12, 1020.00, 'Mensalidade Outubro');
INSERT INTO pagamento VALUES(13, 1020.00, 'Mensalidade Novembro');
INSERT INTO pagamento VALUES(14, 1020.00, 'Mensalidade Dezembro');

-- select * from cobranca_pagamento

INSERT INTO cobranca_pagamento VALUES(1, 1);
INSERT INTO cobranca_pagamento VALUES(2, 1);
INSERT INTO cobranca_pagamento VALUES(3, 1);
INSERT INTO cobranca_pagamento VALUES(4, 1);
INSERT INTO cobranca_pagamento VALUES(5, 1);
INSERT INTO cobranca_pagamento VALUES(6, 1);

INSERT INTO cobranca_pagamento VALUES(7, 2);
INSERT INTO cobranca_pagamento VALUES(8, 2);
INSERT INTO cobranca_pagamento VALUES(9, 2);
INSERT INTO cobranca_pagamento VALUES(10, 2);
INSERT INTO cobranca_pagamento VALUES(11, 2);
INSERT INTO cobranca_pagamento VALUES(12, 2);

INSERT INTO cobranca_pagamento VALUES(13, 3);
INSERT INTO cobranca_pagamento VALUES(14, 4);
INSERT INTO cobranca_pagamento VALUES(15, 5);
INSERT INTO cobranca_pagamento VALUES(16, 6);
INSERT INTO cobranca_pagamento VALUES(17, 7);
INSERT INTO cobranca_pagamento VALUES(18, 8);

INSERT INTO cobranca_pagamento VALUES(19, 9);
INSERT INTO cobranca_pagamento VALUES(20, 10);
INSERT INTO cobranca_pagamento VALUES(21, 11);
INSERT INTO cobranca_pagamento VALUES(22, 12);
INSERT INTO cobranca_pagamento VALUES(23, 13);
INSERT INTO cobranca_pagamento VALUES(24, 14);

-- select * from detalhes_pagamento

INSERT INTO detalhes_pagamento VALUES(1, '2014-07-18', '00:00:00', 'caixa', 'VX34C8NM3', 5970.00, 0, 0, 1, 1);
INSERT INTO detalhes_pagamento VALUES(2, '2014-07-18', '00:00:00', 'caixa', 'VX34C8NM5', 5970.00, 0, 0, 2, 2);
INSERT INTO detalhes_pagamento VALUES(3, '2015-07-14', '00:00:00', 'caixa', 'VX34C8NM9', 1020.00, 0, 0, 3, 3);
INSERT INTO detalhes_pagamento VALUES(4, '2015-08-16', '00:00:00', 'caixa', 'VX35C8NM1', 1020.00, 0, 0, 4, 3);
INSERT INTO detalhes_pagamento VALUES(5, '2015-09-15', '00:00:00', 'caixa', 'VX35C8NO3', 1020.00, 0, 0, 5, 3);
INSERT INTO detalhes_pagamento VALUES(6, '2015-10-15', '00:00:00', 'caixa', 'VX35C8NO5', 1020.00, 0, 0, 6, 3);
INSERT INTO detalhes_pagamento VALUES(7, '2015-11-18', '00:00:00', 'caixa', 'VX35C8NO9', 1020.00, 0, 0, 7, 3);
INSERT INTO detalhes_pagamento VALUES(8, '2015-12-10', '00:00:00', 'caixa', 'VX36C8NO1', 1020.00, 0, 0, 8, 3);
INSERT INTO detalhes_pagamento VALUES(9, '2017-07-18', '00:00:00', 'caixa', 'VX36C8NP3', 1020.00, 0, 0, 9, 4);
INSERT INTO detalhes_pagamento VALUES(10, '2017-08-18', '00:00:00', 'caixa', 'VX36C8NP5', 1020.00, 0, 0, 10, 4);
INSERT INTO detalhes_pagamento VALUES(11, '2017-09-17', '00:00:00', 'caixa', 'VX36C8NP9', 1020.00, 0, 0, 11, 4);
INSERT INTO detalhes_pagamento VALUES(12, '2017-10-18', '00:00:00', 'caixa', 'VX37C8NP1', 1020.00, 0, 0, 12, 4);
INSERT INTO detalhes_pagamento VALUES(13, '2017-11-18', '00:00:00', 'caixa', 'VX37C8NQ3', 1020.00, 0, 0, 13, 4);
INSERT INTO detalhes_pagamento VALUES(14, '2017-12-18', '00:00:00', 'caixa', 'VX37C8NQ5', 1020.00, 0, 0, 14, 4);



-- ARRUMAR IDs
-- ARRUMAR SEQs

ALTER SEQUENCE pessoa_id_seq RESTART 8;
ALTER SEQUENCE documento_id_seq RESTART 8;
ALTER SEQUENCE curso_id_seq RESTART 8;
ALTER SEQUENCE curso_turno_id_seq RESTART 11;
ALTER SEQUENCE grade_curricular_id_seq RESTART 5;
ALTER SEQUENCE serie_id_seq RESTART 39;
ALTER SEQUENCE periodo_letivo_id_seq RESTART 4;
ALTER SEQUENCE aluno_id_seq RESTART 5;
ALTER SEQUENCE aluno_serie_id_seq RESTART 5;
ALTER SEQUENCE plano_pagamento_id_seq RESTART 4;
ALTER SEQUENCE emolumentos_id_seq RESTART 2;
ALTER SEQUENCE divida_id_seq RESTART 5;
ALTER SEQUENCE banco_id_seq RESTART 9;
ALTER SEQUENCE agencia_conta_id_seq RESTART 5;
ALTER SEQUENCE cobranca_id_seq RESTART 25;
ALTER SEQUENCE pagamento_id_seq RESTART 15;
ALTER SEQUENCE detalhes_pagamento_id_seq RESTART 15;