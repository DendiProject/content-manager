CREATE TABLE nodeverb
(
    node_verb_id character varying(255) NOT NULL,
    node_id character varying(255) ,
    verb_id character varying(255) ,
    CONSTRAINT nodeverb_pkey PRIMARY KEY (node_verb_id),
    CONSTRAINT fk6sek9rlyyge0et9wviu5v88q8 FOREIGN KEY (verb_id)
        REFERENCES verb (verb_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkjr10vfkia139jdydapq4wx4rc FOREIGN KEY (node_id)
        REFERENCES node (node_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);