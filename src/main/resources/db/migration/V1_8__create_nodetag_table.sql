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