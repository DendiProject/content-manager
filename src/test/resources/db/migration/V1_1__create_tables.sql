CREATE TABLE metainformationtype
(
    meta_type_id character varying(255) NOT NULL,
    meta_type_name character varying(255) ,
    CONSTRAINT metainformationtype_pkey PRIMARY KEY (meta_type_id)
);

CREATE TABLE nodetype
(
    type_id character varying(255)  NOT NULL,
    type_name character varying(255),
    CONSTRAINT nodetype_pkey PRIMARY KEY (type_id)
);

CREATE TABLE node
(
    node_id character varying(255)  NOT NULL,
    node_name character varying(255),
    source character varying(255),
    type_id character varying(255) NOT NULL,
    CONSTRAINT node_pkey PRIMARY KEY (node_id)
);
ALTER TABLE node
    ADD FOREIGN KEY (type_id) 
    REFERENCES nodetype (type_id);

CREATE TABLE metainformation
(
    meta_inf_id character varying(255)  NOT NULL,
    value character varying(255),
    meta_type_id character varying(255),
    node_id character varying(255),
    CONSTRAINT metainformation_pkey PRIMARY KEY (meta_inf_id)
);
ALTER TABLE metainformation
    ADD FOREIGN KEY (node_id) 
    REFERENCES node (node_id);
ALTER TABLE metainformation
    ADD FOREIGN KEY (meta_type_id) 
    REFERENCES metainformationtype (meta_type_id);


CREATE TABLE tag
(
    tag_id character varying(255) NOT NULL,
    tag_name character varying(255),
    CONSTRAINT tag_pkey PRIMARY KEY (tag_id)
);

CREATE TABLE verb
(
    verb_id character varying(255) NOT NULL,
    verb_name character varying(255) ,
    CONSTRAINT verb_pkey PRIMARY KEY (verb_id)
);

CREATE TABLE nodeverb
(
    node_verb_id character varying(255) NOT NULL,
    node_id character varying(255) ,
    verb_id character varying(255) ,
    CONSTRAINT nodeverb_pkey PRIMARY KEY (node_verb_id)
);
ALTER TABLE nodeverb
    ADD FOREIGN KEY (verb_id)
        REFERENCES verb (verb_id);
ALTER TABLE nodeverb
    ADD FOREIGN KEY (node_id)
        REFERENCES node (node_id); 

CREATE TABLE nodetag
(
    node_tag_id character varying(255) NOT NULL,
    tag_id character varying(255),
    node_id character varying(255),
    CONSTRAINT nodetag_pkey PRIMARY KEY (node_tag_id)
);
ALTER TABLE nodetag
    ADD FOREIGN KEY (tag_id)
        REFERENCES tag (tag_id);
ALTER TABLE nodeverb
    ADD FOREIGN KEY (node_id)
        REFERENCES node (node_id) ; 
CREATE TABLE nodetext
(
    text_id character varying(255) NOT NULL,
    text character varying(255),
    node_id character varying(255),
    CONSTRAINT nodetext_pkey PRIMARY KEY (text_id)
);
ALTER TABLE nodetext
    ADD FOREIGN KEY (node_id)
        REFERENCES node (node_id);