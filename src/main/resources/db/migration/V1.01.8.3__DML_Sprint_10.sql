CREATE SEQUENCE aluno_serie_id_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --

-- object: public.detalhes_pagamento_id_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.detalhes_pagamento_id_seq CASCADE;
CREATE SEQUENCE detalhes_pagamento_id_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;