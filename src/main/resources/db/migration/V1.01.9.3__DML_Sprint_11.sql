CREATE SEQUENCE detalhes_pagamento_id_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

ALTER TABLE detalhes_pagamento ALTER COLUMN id SET DEFAULT nextval('detalhes_pagamento_id_seq'::regclass);
