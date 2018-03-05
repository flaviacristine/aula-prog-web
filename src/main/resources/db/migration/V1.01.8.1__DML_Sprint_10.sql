-- [ Dropped objects ] --
ALTER TABLE divida DROP COLUMN IF EXISTS desconto CASCADE;
-- ddl-end --
-- object: status_cobranca | type: COLUMN --
ALTER TABLE cobranca ADD COLUMN status_cobranca smallint NOT NULL;
-- ddl-end --
ALTER TABLE detalhes_pagamento ALTER COLUMN hora_pagamento TYPE TIMESTAMP;
ALTER TABLE detalhes_pagamento ALTER COLUMN hora_pagamento TYPE TIME;
-- ddl-end --
