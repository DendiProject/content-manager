CREATE TABLE verb
(
    verb_id character varying(255) NOT NULL,
    verb_name character varying(255) ,
    CONSTRAINT verb_pkey PRIMARY KEY (verb_id)
)
WITH (
    OIDS = FALSE
);