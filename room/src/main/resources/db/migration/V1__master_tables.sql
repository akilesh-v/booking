CREATE TABLE IF NOT EXISTS public.rooms_master
(
    id bigint NOT NULL GENERATED ALWAYS AS (1) STORED,
    room_id text COLLATE pg_catalog."default" NOT NULL,
    max_adult numeric NOT NULL,
    max_child numeric NOT NULL,
    max_child_age_limit numeric NOT NULL,
    CONSTRAINT rooms_master_pkey PRIMARY KEY (id, room_id),
    CONSTRAINT rooms_master_id_key UNIQUE (id)
    );


CREATE TABLE IF NOT EXISTS public.room_price
(
    room_id bigint NOT NULL,
    price_range_from timestamp without time zone NOT NULL,
    price_range_to timestamp without time zone NOT NULL,
    base_price double precision NOT NULL,
    extra_adult_price double precision NOT NULL,
    extra_child_price double precision NOT NULL,
    CONSTRAINT room_price_room_id_fkey FOREIGN KEY (room_id)
    REFERENCES public.rooms_master (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID
    );


CREATE TABLE IF NOT EXISTS public.booking
(
    booking_id bigint NOT NULL GENERATED ALWAYS AS (1) STORED,
    room_id bigint NOT NULL,
    booking_from_date timestamp without time zone NOT NULL,
    booking_to_date timestamp without time zone NOT NULL,
    adult_count numeric NOT NULL,
    child_age numeric[],
    CONSTRAINT booking_pkey PRIMARY KEY (booking_id),
    CONSTRAINT booking_room_id_fkey FOREIGN KEY (room_id)
    REFERENCES public.rooms_master (id) MATCH SIMPLE
                                ON UPDATE NO ACTION
                                ON DELETE NO ACTION
    );

