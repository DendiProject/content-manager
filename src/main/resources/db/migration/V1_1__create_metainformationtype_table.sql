CREATE TABLE metainformationtype
(
    meta_type_id character varying(255) NOT NULL,
    meta_type_name character varying(255) ,
    CONSTRAINT metainformationtype_pkey PRIMARY KEY (meta_type_id)
)
WITH (
    OIDS = FALSE
);