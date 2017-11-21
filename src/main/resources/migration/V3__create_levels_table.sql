-- LEVELS TABLE

CREATE TABLE public.levels
(
	level integer NOT NULL,
	xp integer NOT NULL,
	CONSTRAINT level_pkey PRIMARY KEY (level)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;