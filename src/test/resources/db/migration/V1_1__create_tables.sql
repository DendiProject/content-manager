CREATE TABLE nodetype
(
    type_id character varying(255)  NOT NULL,
    type_name character varying(255),
    max_size  integer,
    CONSTRAINT nodetype_pkey PRIMARY KEY (type_id)
);

CREATE TABLE node
(
    node_id character varying(255)  NOT NULL,
    node_name character varying(255),
    node_source character varying(255),
    type_id character varying(255) NOT NULL,    
    user_id character varying(255),
    check_sum character varying(255),
    CONSTRAINT node_pkey PRIMARY KEY (node_id)
);
ALTER TABLE node
    ADD FOREIGN KEY (type_id) 
    REFERENCES nodetype (type_id);

CREATE TABLE metainformation
(
    meta_inf_id character varying(255)  NOT NULL,
    meta_value character varying(255),
    meta_inf_type character varying(255),
    CONSTRAINT metainformation_pkey PRIMARY KEY (meta_inf_id)
);


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

CREATE TABLE Node_tagList 
    (Node_node_id varchar(255) not null, 
    tagList_tag_id varchar(255) not null,
     primary key (Node_node_id, tagList_tag_id));

CREATE TABLE Node_verbList 
    (Node_node_id varchar(255) not null, 
    verbList_verb_id varchar(255) not null, 
    primary key (Node_node_id, verbList_verb_id));

CREATE TABLE Node_metaList 
    (Node_node_id varchar(255) not null,
     metaList_meta_inf_id varchar(255) not null, 
    primary key (Node_node_id, metaList_meta_inf_id));