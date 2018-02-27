CREATE TABLE node
(
    node_id character varying(255)  NOT NULL,
    node_name character varying(255),
    source character varying(255),
    type_id character varying(255) NOT NULL,
    CONSTRAINT node_pkey PRIMARY KEY (node_id),
    CONSTRAINT fknatx2lojntnaq6d7e9su7eywl FOREIGN KEY (type_id)
        REFERENCES nodetype (type_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);