CREATE TABLE nodetype
(
    type_id character varying(255)  NOT NULL,
    type_name character varying(255),
    CONSTRAINT nodetype_pkey PRIMARY KEY (type_id)
)
WITH (
    OIDS = FALSE
);