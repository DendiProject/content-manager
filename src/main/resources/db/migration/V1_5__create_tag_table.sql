CREATE TABLE tag
(
    tag_id character varying(255) NOT NULL,
    tag_name character varying(255),
    CONSTRAINT tag_pkey PRIMARY KEY (tag_id)
)
WITH (
    OIDS = FALSE
);