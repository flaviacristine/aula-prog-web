-- PESSOA
INSERT INTO pessoa(id, naturalidade, codigo, nome, data_nascimento, sexo, email, senha, situacao, estado_civil, nome_mae) VALUES
(1, 1, 'istel', 'ISTEL', '2017-01-01', 0, 'istel@virtoo.com', '$2a$04$BV5t/mZRSAgHyTDwLlJqFenpt2keDDnl9ElT2EgZA9erdhlrHe0Iu', 1, 0, '');
INSERT INTO pessoa(id, naturalidade, codigo, nome, data_nascimento, sexo, email, senha, situacao, estado_civil, nome_mae) VALUES
(2, 1, 'ispvida', 'ISPVIDA', '2017-01-01', 0, 'ispvida@virtoo.com', '$2a$04$A0uC4gd7wOAuasSPefhZ3OQ3X/32mShzfEzkbB6KMvZlD0wPIH1lu', 1, 0, '');
INSERT INTO pessoa(id, naturalidade, codigo, nome, data_nascimento, sexo, email, senha, situacao, estado_civil, nome_mae) VALUES
(3, 1, 'admin', 'Administrador', '2017-01-01', 0, 'admin@email.com', '$2a$06$Lj0hTAo0rECQSLwJIPpVde6dO21KhRe.HmIIjDZSLpVihEkhXMkkC', 1, 0, '');

-- ENDEREÇO
INSERT INTO endereco VALUES
(5, 145112, 'Zona Industrial II', ' km 12', 'Comuna de Arimba', ''),
(6, 145112, 'Zona Industrial II', ' km 12', 'Comuna de Arimba', '');

-- PESSOA ENDEREÇO
INSERT INTO pessoa_endereco (id, endereco_id, pessoa_id, tipo_endereco, principal) VALUES (1, 5, 1, 1, 't');
INSERT INTO pessoa_endereco (id, endereco_id, pessoa_id, tipo_endereco, principal) VALUES (2, 6, 2, 1, 't');

-- PERFIL
INSERT INTO perfil(id, unidade_id, nome, descricao, tipo_perfil) VALUES (1, 1, 'ADMIN', 'Administrador', 1);

-- CARGO
INSERT INTO cargo(id, nome, tipo_perfil) VALUES(1, 'Docente', 1);
INSERT INTO cargo(id, nome, tipo_perfil) VALUES(2, 'Coordenador', 0);
INSERT INTO cargo(id, nome, tipo_perfil) VALUES(3, 'Diretor', 0);

-- SETOR
INSERT INTO setor(id, nome) VALUES(1, 'Financeiro');
INSERT INTO setor(id, nome) VALUES(2, 'Recursos Humanos');

-- FUNCIONÁRIO
INSERT INTO funcionario(id, unidade_id, pessoa_id, cargo_id, setor_id, matricula, titulacao, situacao) VALUES(1, 1, 3, 1, 1,'123456', 2, 1);

-- FUNCIONARIO PERFIL
INSERT INTO funcionario_perfil(perfil_id, funcionario_id) VALUES (1, 1);

-- DOCUMENTO
INSERT INTO documento(id, arquivo_id, pessoa_id, numero, tipo_documento) VALUES(1, NULL, 1, '0000000', 1);
INSERT INTO documento(id, arquivo_id, pessoa_id, numero, tipo_documento) VALUES(2, NULL, 2, '0000000', 1);
INSERT INTO documento(id, arquivo_id, pessoa_id, numero, tipo_documento) VALUES(3, NULL, 3, '0000000', 1);


-- PERFIL PERMISSÃO
INSERT INTO perfil_permissao(permissao_id, perfil_id) VALUES
(1, 1),
(2, 1), 
(3, 1), 
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 1),
(11, 1),
(12, 1),
(13, 1),
(14, 1),
(15, 1),
(16, 1),
(17, 1),
(18, 1),
(19, 1),
(20, 1),
(21, 1),
(22, 1),
(23, 1),
(24, 1),
(25, 1),
(26, 1),
(27, 1),
(28, 1),
(29, 1),
(30, 1),
(31, 1),
(32, 1),
(33, 1),
(34, 1),
(35, 1),
(36, 1),
(37, 1),
(38, 1),
(39, 1),
(40, 1),
(41, 1),
(42, 1),
(43, 1),
(44, 1),
(45, 1),
(46, 1),
(47, 1),
(48, 1),
(49, 1),
(50, 1),
(51, 1),
(52, 1),
(53, 1),
(54, 1),
(55, 1),
(56, 1),
(57, 1),
(58, 1),
(59, 1),
(60, 1),
(61, 1),
(62, 1),
(63, 1),
(64, 1),
(65, 1),
(66, 1),
(67, 1),
(68, 1),
(69, 1),
(70, 1),
(71, 1),
(72, 1),
(73, 1),
(74, 1),
(75, 1),
(76, 1),
(77, 1),
(78, 1),
(79, 1),
(80, 1),
(81, 1),
(82, 1),
(83, 1),
(84, 1),
(85, 1),
(86, 1),
(87, 1),
(88, 1),
(89, 1),
(90, 1),
(91, 1),
(92, 1),
(93, 1),
(94, 1),
(95, 1),
(96, 1),
(97, 1),
(98, 1),
(99, 1),
(100, 1),
(101, 1),
(102, 1),
(103, 1),
(104, 1),
(105, 1),
(106, 1),
(107, 1),
(108, 1),
(109, 1),
(110, 1),
(111, 1),
(112, 1),
(113, 1),
(114, 1),
(115, 1),
(116, 1),
(117, 1),
(118, 1),
(119, 1),
(120, 1),
(121, 1),
(122, 1),
(123, 1),
(124, 1),
(125, 1),
(126, 1),
(127, 1),
(128, 1);
