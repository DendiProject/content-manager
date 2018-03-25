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
    CONSTRAINT metainformation_pkey PRIMARY KEY (meta_inf_id)
)
WITH (
    OIDS = FALSE
);

CREATE TABLE tag
(
    tag_id character varying(255) NOT NULL,
    tag_name character varying(255),
    CONSTRAINT tag_pkey PRIMARY KEY (tag_id))
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

CREATE TABLE Node_tagList 
    (Node_node_id varchar(255) not null, 
    tagList_tag_id varchar(255) not null);
alter table Node_tagList add constraint FK9g52vxo4gnsilfckxug56tgt9 foreign key (tagList_tag_id) references Tag;
alter table Node_tagList add constraint FKgfgd00q37nwo8s5opbgjjiv8e foreign key (Node_node_id) references Node;

CREATE TABLE Node_verbList 
    (Node_node_id varchar(255) not null, 
    verbList_verb_id varchar(255) not null);

alter table Node_verbList add constraint FK16w5m2vu8jdy8mm3vrur6y02k foreign key (verbList_verb_id) references Verb;
alter table Node_verbList add constraint FKcawceeqdxd1vtl6vrl0ipuv04 foreign key (Node_node_id) references Node;

CREATE TABLE Node_metaList 
    (Node_node_id varchar(255) not null,
     metaList_meta_inf_id varchar(255) not null);
alter table Node_metaList add constraint FKmvbg65j3c9h0b3x8gdk89bgkm foreign key (metaList_meta_inf_id) references MetaInformation;
alter table Node_metaList add constraint FKcnko6lm5nfif9pk7n85bgupds foreign key (Node_node_id) references Node;