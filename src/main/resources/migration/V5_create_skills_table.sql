-- SKILLS TABLE

CREATE TYPE skill_types AS ENUM ('OFFENSIVE', 'DEFENSIVE', 'ADAPTIVE', 'BASIC');

CREATE TABLE public.skills
(
    id bigint NOT NULL DEFAULT nextval('hibernate_sequence'),
    name character varying COLLATE pg_catalog."default",
	type skill_types,
	prerequisites character varying COLLATE pg_catalog."default",
	CONSTRAINT skill_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;