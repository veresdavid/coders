-- USER JOBS TABLE

CREATE TABLE public.user_jobs
(
    id bigint NOT NULL DEFAULT nextval('hibernate_sequence'),
    user_id bigint,
    job_id bigint,
    start timestamp,
    finish timestamp,
    gained_rewards boolean,
    CONSTRAINT user_jobs_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;