CREATE TABLE nodetype
(
    type_id character varying(255)  NOT NULL,
    type_name character varying(255),
    max_size  integer,
    CONSTRAINT nodetype_pkey PRIMARY KEY (type_id)
)
WITH (
    OIDS = FALSE
);

CREATE TABLE node
(
    node_id character varying(255)  NOT NULL,
    node_name character varying(255),
    node_source character varying(255),
    type_id character varying(255) NOT NULL,
    user_id character varying(255),
    check_sum character varying(255),
    CONSTRAINT node_pkey PRIMARY KEY (node_id),
    CONSTRAINT fknatx2lojntnaq6d7e9su7eywl FOREIGN KEY (type_id)
        REFERENCES nodetype (type_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

CREATE TABLE metainformation
(
    meta_inf_id character varying(255)  NOT NULL,
    meta_inf_type character varying(255),
    meta_value character varying(255),    
    node_id character varying(255)  NOT NULL,
    CONSTRAINT metainformation_pkey PRIMARY KEY (meta_inf_id),
    CONSTRAINT node_id FOREIGN KEY (node_id)
            REFERENCES node (node_id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

CREATE TABLE tag
(
    tag_id character varying(255) NOT NULL,
    tag_name character varying(255),
    CONSTRAINT tag_pkey PRIMARY KEY (tag_id),
    CONSTRAINT node_id FOREIGN KEY (node_id)
        REFERENCES node (node_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

CREATE TABLE verb
(
    verb_id character varying(255) NOT NULL,
    verb_name character varying(255) ,
    CONSTRAINT verb_pkey PRIMARY KEY (verb_id),
    CONSTRAINT node_id FOREIGN KEY (node_id)
        REFERENCES node (node_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

CREATE TABLE nodetext
(
    text_id character varying(255) NOT NULL,
    text character varying(255),
    node_id character varying(255),
    CONSTRAINT nodetext_pkey PRIMARY KEY (text_id),
    CONSTRAINT fkg1p29esegmkidfh9rog0hp7rf FOREIGN KEY (node_id)
        REFERENCES node (node_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);