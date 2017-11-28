-- MESSAGES TABLE

CREATE TYPE message_types AS ENUM ('SYSTEM', 'USER');

CREATE TABLE public.messages
(
    id bigint NOT NULL DEFAULT nextval('hibernate_sequence'),
    sender_id bigint,
    receiver_id bigint,
    type message_types,
    subject character varying COLLATE pg_catalog."default",
    message character varying COLLATE pg_catalog."default",
    date timestamp,
    read boolean,
    CONSTRAINT messages_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;