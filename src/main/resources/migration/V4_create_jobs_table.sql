-- JOBS TABLE

CREATE TABLE public.jobs
(
    id bigint NOT NULL DEFAULT nextval('hibernate_sequence'),
    name character varying COLLATE pg_catalog."default",
	payment integer,
	xp integer,
	time integer,
	energy integer,
	prerequisites character varying COLLATE pg_catalog."default",
	CONSTRAINT job_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;