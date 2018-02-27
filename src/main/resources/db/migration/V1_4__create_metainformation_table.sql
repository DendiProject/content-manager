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