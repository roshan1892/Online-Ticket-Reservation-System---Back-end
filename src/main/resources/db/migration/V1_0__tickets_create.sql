CREATE TABLE tickets (
    id uuid NOT NULL,
    name character varying(255),
    description character varying(255),
    price bigint NOT NULL,
    created_at timestamp without time zone,
    created_by character varying(255),
    payment_status boolean NOT NULL
);

ALTER TABLE ONLY tickets
    ADD CONSTRAINT tickets_pkey PRIMARY KEY (id);