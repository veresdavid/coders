-- USER ATTACKS TABLE

CREATE TABLE public.user_attacks
(
    id bigint NOT NULL DEFAULT nextval('hibernate_sequence'),
    attacker_id bigint,
    defender_id bigint,
    start timestamp,
    finish timestamp,
    gained_rewards boolean,
    CONSTRAINT user_attacks_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;