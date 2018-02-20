--COLEÇÃO GRUPO PERMISSÃO
INSERT INTO colecao_grupo_permissao (id, nome) VALUES
(1, 'colecao.grupo.perm.confIniciais'),
(2, 'colecao.grupo.perm.academico'),
(3, 'colecao.grupo.perm.localizacao'),
(4, 'colecao.grupo.perm.pessoal'),
(5, 'colecao.grupo.perm.financeiro'),
(6, 'colecao.grupo.perm.acesso');

--PONTO E VÍRGULA NO ÚLTIMO;


-- GRUPO PERMISSÃO
INSERT INTO grupo_permissao (id, nome, colecao_grupo_permissao_id) VALUES 
(1, 'grupo.perm.pessoa', 4),
(2, 'grupo.perm.provincia', 1),
(3, 'grupo.perm.funcionario', 4),
(4, 'grupo.perm.mantenedora', 1),
(5, 'grupo.perm.instituicao', 1),
(6, 'grupo.perm.unidade', 1),
(7, 'grupo.perm.curso', 2),
(8, 'grupo.perm.horario', 1),
(9, 'grupo.perm.disciplina', 2),
(10, 'grupo.perm.perfilDeAcesso', 6),
(11, 'grupo.perm.periodoLetivo', 2),
(12, 'grupo.perm.aluno', 4),
(13, 'grupo.perm.gradeCurricular', 2),
(14, 'grupo.perm.turma', 2),
(15, 'grupo.perm.bloco', 3),
(16, 'grupo.perm.piso', 3),
(17, 'grupo.perm.sala', 3),
(18, 'grupo.perm.planoDePagamento', 5),
(19, 'grupo.perm.atribuirValorAoCurso', 5),
(20, 'grupo.perm.emolumentos', 5),
(21, 'grupo.perm.divida', 5),
(22, 'grupo.perm.cobranca', 5),
(23, 'grupo.perm.banco', 1),
(24, 'grupo.perm.agenciaConta', 1),
(25, 'grupo.perm.avaliacao', 1),
(26, 'grupo.perm.frequencia', 1),
(27, 'grupo.perm.instituicaoDeEscolaridade', 1);

-- PONTO E VÍRGULA NO ÚLTIMO;


-- PERMISSÃO
INSERT INTO permissao (id, codigo, nome, grupo_permissao_id) VALUES
(1, 'PERM_PESSOA_CADASTRAR', 'perm.cadastrarPessoa', 1),
(2, 'PERM_PESSOA_ALTERAR', 'perm.alterarPessoa', 1),
(3, 'PERM_PESSOA_LISTAR', 'perm.listarPessoa', 1),
(4, 'PERM_PESSOA_VISUALIZAR', 'perm.visualizarPessoa', 1),
(5, 'PERM_PESSOA_EXCLUIR', 'perm.excluirPessoa', 1),

(6, 'PERM_PROVINCIA_CADASTRAR', 'perm.cadastrarProvincia', 2),
(7, 'PERM_PROVINCIA_ALTERAR', 'perm.alterarProvincia', 2),
(8, 'PERM_PROVINCIA_EXCLUIR', 'perm.excluirProvincia', 2),
(9, 'PERM_PROVINCIA_MUNICIPIO_LISTAR', 'perm.listarProvincia', 2),

(10, 'PERM_FUNCIONARIO_CADASTRAR', 'perm.cadastrarFuncionario', 3),
(11, 'PERM_FUNCIONARIO_ALTERAR', 'perm.alterarFuncionario', 3),
(12, 'PERM_FUNCIONARIO_LISTAR', 'perm.listarFuncionario', 3),
(13, 'PERM_FUNCIONARIO_VISUALIZAR', 'perm.visualizarFuncionario', 3),
(14, 'PERM_FUNCIONARIO_ATIVAR_INATIVAR', 'perm.ativarInativarFuncionario', 3),
	
(15, 'PERM_MANTENEDORA_CADASTRAR', 'perm.cadastrarMantenedora', 4),
(16, 'PERM_MANTENEDORA_ALTERAR', 'perm.alterarMantenedora', 4),
(17, 'PERM_MANTENEDORA_LISTAR', 'perm.listarMantenedora', 4),
(18, 'PERM_MANTENEDORA_VISUALIZAR', 'perm.visualizarMantenedora', 4),
(19, 'PERM_MANTENEDORA_ATIVAR_INATIVAR', 'perm.ativarInativarMantenedora', 4),
(20, 'PERM_MANTENEDORA_EXCLUIR', 'perm.excluirMantenedora', 4),
	
(21, 'PERM_INSTITUICAO_CADASTRAR', 'perm.cadastrarInstituicao', 5),
(22, 'PERM_INSTITUICAO_ALTERAR', 'perm.alterarInstituicao', 5),
(23, 'PERM_INSTITUICAO_LISTAR', 'perm.listarInstituicao', 5),
(24, 'PERM_INSTITUICAO_VISUALIZAR', 'perm.visualizarInstituicao', 5),
(25, 'PERM_INSTITUICAO_ATIVAR_INATIVAR', 'perm.aitvarInativarInstituicao', 5),
(26, 'PERM_INSTITUICAO_EXCLUIR', 'perm.excluirInstituicao', 5),
	
(27, 'PERM_UNIDADE_CADASTRAR', 'perm.cadastrarUnidade', 6),
(28, 'PERM_UNIDADE_ALTERAR', 'perm.alterarUnidade', 6),
(29, 'PERM_UNIDADE_LISTAR', 'perm.listarUnidade', 6),
(30, 'PERM_UNIDADE_VISUALIZAR', 'perm.visualizarUnidade', 6),
(31, 'PERM_UNIDADE_ATIVAR_INATIVAR', 'perm.ativarInativarUnidade', 6),
(32, 'PERM_UNIDADE_EXCLUIR', 'perm.excluirUnidade', 6),
	
(33, 'PERM_CURSO_CADASTRAR', 'perm.cadastrarCurso', 7),
(34, 'PERM_CURSO_ALTERAR', 'perm.alterarCurso', 7),
(35, 'PERM_CURSO_LISTAR', 'perm.listarCurso', 7),
(36, 'PERM_CURSO_VISUALIZAR', 'perm.visualizarCurso', 7),
(37, 'PERM_CURSO_ATIVAR_INATIVAR', 'perm.ativarInativarCurso', 7),
	
(38, 'PERM_HORARIO_AULA_CADASTRAR', 'perm.cadastrarHorario', 8),
(39, 'PERM_HORARIO_AULA_ALTERAR', 'perm.alterarHorario', 8),
(40, 'PERM_HORARIO_AULA_LISTAR', 'perm.listarHorario', 8),
(41, 'PERM_HORARIO_AULA_VISUALIZAR', 'perm.visualizarHorario', 8),
(42, 'PERM_HORARIO_AULA_EXCLUIR', 'perm.excluirHorario', 8),
	
(43, 'PERM_DISCIPLINA_CADASTRAR', 'perm.cadastrarDisciplina', 9),
(44, 'PERM_DISCIPLINA_ALTERAR', 'perm.alterarDisciplina', 9),
(45, 'PERM_DISCIPLINA_LISTAR', 'perm.listarDisciplina', 9),
(46, 'PERM_DISCIPLINA_VISUALIZAR', 'Vperm.visualizarDisciplina', 9),
(47, 'PERM_DISCIPLINA_ATIVAR_INATIVAR', 'perm.ativarInativarDisciplina', 9),
	
(48, 'PERM_PERFIL_ACESSO_CADASTRAR', 'perm.cadastrarPerfilAcesso', 10),
(49, 'PERM_PERFIL_ACESSO_ALTERAR', 'perm.alterarPerfilAcesso', 10),
(50, 'PERM_PERFIL_ACESSO_LISTAR', 'perm.listarPerfilAcesso', 10),
(51, 'PERM_PERFIL_ACESSO_VISUALIZAR', 'perm.visualizarPerfilAcesso', 10),
(52, 'PERM_PERFIL_ACESSO_EXCLUIR', 'perm.excluirPerfilAcesso', 10),
	
(53, 'PERM_PERIODO_LETIVO_CADASTRAR', 'perm.cadastrarPeridoLetivo', 11),
(54, 'PERM_PERIODO_LETIVO_ALTERAR', 'perm.alterarPeridoLetivo', 11),
(55, 'PERM_PERIODO_LETIVO_LISTAR', 'perm.listarPeridoLetivo', 11),
(56, 'PERM_PERIODO_LETIVO_VISUALIZAR', 'perm.visualizarPeridoLetivo', 11),
(57, 'PERM_PERIODO_LETIVO_EXCLUIR', 'perm.excluirPeridoLetivo', 11),
	
(58, 'PERM_ALUNO_CADASTRAR', 'perm.cadastrarAluno', 12),
(59, 'PERM_ALUNO_ALTERAR', 'perm.alterarAluno', 12),
(60, 'PERM_ALUNO_LISTAR', 'perm.listarAluno', 12),
(61, 'PERM_ALUNO_VISUALIZAR', 'perm.visualizarAluno', 12),
(62, 'PERM_ALUNO_ATIVAR_INATIVAR', 'perm.ativarInativarAluno', 12),
	
(63, 'PERM_GRADE_CURRICULAR_CADASTRAR', 'perm.cadastrarGradeCurricular', 13),
(64, 'PERM_GRADE_CURRICULAR_ALTERAR', 'perm.alterarGradeCurricular', 13),
(65, 'PERM_GRADE_CURRICULAR_LISTAR', 'perm.listarGradeCurricular', 13),
(66, 'PERM_GRADE_CURRICULAR_VISUALIZAR', 'perm.visualizarGradeCurricular', 13),
(67, 'PERM_GRADE_CURRICULAR_ATIVAR_INATIVAR', 'perm.ativarInativarGradeCurricular', 13),
(68, 'PERM_GRADE_CURRICULAR_EXCLUIR', 'perm.excluirGradeCurricular', 13),

(69, 'PERM_TURMA_CADASTRAR', 'perm.cadastrarTurma', 14),
(70, 'PERM_TURMA_ALTERAR', 'perm.alterarTurma', 14),
(71, 'PERM_TURMA_LISTAR', 'perm.listarTurma', 14),
(72, 'PERM_TURMA_VISUALIZAR', 'perm.visualizarTurma', 14),
(73, 'PERM_TURMA_ATIVAR_INATIVAR', 'perm.aitvarInativarTurma', 14),
(74, 'PERM_TURMA_EXCLUIR', 'perm.excluirTurma', 14),
	
(75, 'PERM_BLOCO_CADASTRAR', 'perm.cadastrarBloco', 15),
(76, 'PERM_BLOCO_ALTERAR', 'perm.alterarBloco', 15),
(77, 'PERM_BLOCO_LISTAR', 'perm.listarBloco', 15),
(78, 'PERM_BLOCO_ATIVAR_INATIVAR', 'perm.ativarInativarBloco', 15),
(79, 'PERM_BLOCO_EXCLUIR', 'perm.excluirBloco', 15),
	
(80, 'PERM_PISO_CADASTRAR', 'perm.cadastrarPiso', 16),
(81, 'PERM_PISO_ALTERAR', 'perm.alterarPiso', 16),
(82, 'PERM_PISO_LISTAR', 'perm.listarPiso', 16),
(83, 'PERM_PISO_ATIVAR_INATIVAR', 'perm.ativarInativarPiso', 16),
(84, 'PERM_PISO_EXCLUIR', 'perm.excluirPiso', 16),
	
(85, 'PERM_SALA_CADASTRAR', 'perm.cadastrarSala', 17),
(86, 'PERM_SALA_ALTERAR', 'perm.alterarSala', 17),
(87, 'PERM_SALA_LISTAR', 'perm.listarSala', 17),
(88, 'PERM_SALA_ATIVAR_INATIVAR', 'perm.ativarInativarSala', 17),
(89, 'PERM_SALA_EXCLUIR', 'perm.excluirSala', 17),
	
(90, 'PERM_PLANO_PAGAMENTO_CADASTRAR', 'perm.cadastrarPlanoDePagamento', 18),
(91, 'PERM_PLANO_PAGAMENTO_ALTERAR', 'perm.alterarPlanoDePagamentoo', 18),
(92, 'PERM_PLANO_PAGAMENTO_LISTAR', 'perm.listarPlanoDePagamento', 18),
(93, 'PERM_PLANO_PAGAMENTO_VISUALIZAR', 'perm.visualizarPlanoDePagamento', 18),
(94, 'PERM_PLANO_PAGAMENTO_ATIVAR_INATIVAR', 'perm.ativarInativarPlanoDePagamento', 18),
(95, 'PERM_PLANO_PAGAMENTO_EXCLUIR', 'perm.excluirPlanoDePagamento', 18),
	
(96, 'PERM_VALOR_CURSO_CADASTRAR', 'perm.cadastrarAtribuirValorCurso', 19),
(97, 'PERM_VALOR_CURSO_LISTAR', 'perm.listarAtribuirValorCurso', 19),
(98, 'PERM_VALOR_CURSO_EXCLUIR', 'perm.excluirAtribuirValorCurso', 19),
	
(99, 'PERM_EMOLUMENTOS_CADASTRAR', 'perm.cadastrarEmolumentos', 20),
(100, 'PERM_EMOLUMENTOS_ALTERAR', 'perm.alterarEmolumentos', 20),
(101, 'PERM_EMOLUMENTOS_LISTAR', 'perm.listarEmolumentos', 20),
(102, 'PERM_EMOLUMENTOS_ATIVAR_INATIVAR', 'perm.ativarInativarEmolumentos', 20),
(103, 'PERM_EMOLUMENTOS_EXCLUIR', 'perm.excluirEmolumentos', 20),
	
(104, 'PERM_DIVIDA_GERAR', 'perm.gerarDivida', 21),
(105, 'PERM_DIVIDA_EXCLUIR', 'perm.excluirDivida', 21),
(106, 'PERM_DIVIDA_LISTAR', 'perm.listarDivida', 21),
	
(107, 'PERM_COBRANCA_GERAR', 'perm.gerarCobranca', 22),
(108, 'PERM_COBRANCA_EXCLUIR', 'perm.excluirCobranca', 22),
(109, 'PERM_COBRANCA_LISTAR', 'perm.listarCobranca', 22),
	
(110, 'PERM_BANCO_CADASTRAR', 'perm.cadastrarBanco', 23),
(111, 'PERM_BANCO_EXCLUIR', 'perm.excluirBanco', 23),
(112, 'PERM_BANCO_ATIVAR_INATIVAR', 'perm.ativarBanco', 23),
(113, 'PERM_BANCO_LISTAR', 'perm.listarBanco', 23),
	
(114, 'PERM_AGENCIA_CONTA_CADASTRAR', 'perm.cadastrarAgenciaConta', 24),
(115, 'PERM_AGENCIA_CONTA_EXCLUIR', 'perm.excluirAgenciaConta', 24),
(116, 'PERM_AGENCIA_CONTA_ATIVAR_INATIVAR', 'perm.ativarInativarAgenciaConta', 24),
(117, 'PERM_AGENCIA_CONTA_LISTAR', 'perm.listarAgenciaConta', 24),
	
(118, 'PERM_AVALIACAO_PADRAO_CADASTRAR', 'perm.cadastrarAvaliacao', 25),
(119, 'PERM_AVALIACAO_PADRAO_EXCLUIR', 'perm.excluirAvaliacao', 25),
(120, 'PERM_AVALIACAO_PADRAO_LISTAR', 'perm.listarAvaliacao', 25),
	
(121, 'PERM_FREQUENCIA_PADRAO_CADASTRAR', 'perm.cadastrarFrequencia', 26),
(122, 'PERM_FREQUENCIA_PADRAO_EXCLUIR', 'perm.excluirFrequencia', 26),
(123, 'PERM_FREQUENCIA_PADRAO_LISTAR', 'perm.listarFrequencia', 26),
	
(124, 'PERM_INSTITUICAO_ESCOLARIDADE_CADASTRAR', 'perm.cadastrarInstituicaoDeEscolaridade', 27),
(125, 'PERM_INSTITUICAO_ESCOLARIDADE_ALTERAR', 'perm.alterarInstituicaoDeEscolaridade', 27),
(126, 'PERM_INSTITUICAO_ESCOLARIDADE_LISTAR', 'perm.listarInstituicaoDeEscolaridade', 27),
(127, 'PERM_INSTITUICAO_ESCOLARIDADE_VISUALIZAR', 'perm.visualizarInstituicaoDeEscolaridade', 27),
(128, 'PERM_INSTITUICAO_ESCOLARIDADE_EXCLUIR', 'perm.excluirInstituicaoDeEscolaridade', 27);

-- PONTO E VÍRGULA NO ÚLTIMO;