-- SKILLS TABLE

CREATE TABLE public.skills
(
    id bigint NOT NULL DEFAULT nextval('hibernate_sequence'),
    name character varying COLLATE pg_catalog."default",
	type character varying COLLATE pg_catalog."default",
	prerequisites character varying COLLATE pg_catalog."default",
	CONSTRAINT skill_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;