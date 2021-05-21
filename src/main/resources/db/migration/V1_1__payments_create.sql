CREATE TABLE payments (
    id uuid NOT NULL,
    name character varying(255),
    description character varying(255),
    amount bigint NOT NULL,
    created_at timestamp without time zone,
    created_by character varying(255),
    ticket_id uuid
);

ALTER TABLE ONLY payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (id);


ALTER TABLE ONLY payments
    ADD CONSTRAINT payments_ticket_id_fkey FOREIGN KEY (ticket_id) REFERENCES tickets(id);