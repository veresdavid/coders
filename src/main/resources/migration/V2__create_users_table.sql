-- USERS TABLE

CREATE TABLE public.users
(
    id bigint NOT NULL DEFAULT nextval('hibernate_sequence'),
    name character varying COLLATE pg_catalog."default",
    email character varying COLLATE pg_catalog."default",
    password character varying COLLATE pg_catalog."default",
    level integer,
    xp integer,
    skills character varying COLLATE pg_catalog."default",
    energy integer,
    last_login timestamp,
    money integer,
    successful_attacks integer,
    unsuccessful_attacks integer,
    skill_points integer,
    last_energy_refresh timestamp,
    CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;