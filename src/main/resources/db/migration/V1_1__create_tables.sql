CREATE TABLE metainformationtype
(
    meta_type_id character varying(255) NOT NULL,
    meta_type_name character varying(255) ,
    CONSTRAINT metainformationtype_pkey PRIMARY KEY (meta_type_id)
)
WITH (
    OIDS = FALSE
);

CREATE TABLE nodetype
(
    type_id character varying(255)  NOT NULL,
    type_name character varying(255),
    CONSTRAINT nodetype_pkey PRIMARY KEY (type_id)
)
WITH (
    OIDS = FALSE
);

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

CREATE TABLE metainformation
(
    meta_inf_id character varying(255)  NOT NULL,
    value character varying(255),
    meta_type_id character varying(255),
    node_id character varying(255),
    CONSTRAINT metainformation_pkey PRIMARY KEY (meta_inf_id),
    CONSTRAINT fkhn9j53o0vjmcvu9893rorile0 FOREIGN KEY (meta_type_id)
        REFERENCES metainformationtype (meta_type_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fktorl0uow0elf9kkn081nnumja FOREIGN KEY (node_id)
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
    CONSTRAINT tag_pkey PRIMARY KEY (tag_id)
)
WITH (
    OIDS = FALSE
);

CREATE TABLE verb
(
    verb_id character varying(255) NOT NULL,
    verb_name character varying(255) ,
    CONSTRAINT verb_pkey PRIMARY KEY (verb_id)
)
WITH (
    OIDS = FALSE
);

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

CREATE TABLE nodetag
(
    node_tag_id character varying(255) NOT NULL,
    tag_id character varying(255),
    node_id character varying(255),
    CONSTRAINT nodetag_pkey PRIMARY KEY (node_tag_id),
    CONSTRAINT node_id FOREIGN KEY (node_id)
        REFERENCES node (node_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT tag_id FOREIGN KEY (tag_id)
        REFERENCES tag (tag_id) MATCH SIMPLE
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