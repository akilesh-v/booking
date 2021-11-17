CREATE TABLE IF NOT EXISTS public.rooms_master
(
    room_id             SERIAL                            NOT NULL,
    user_room_id        text COLLATE pg_catalog."default" NOT NULL,
    max_adult           numeric                           NOT NULL,
    max_child           numeric                           NOT NULL,
    max_child_age_limit numeric                           NOT NULL,
    CONSTRAINT rooms_master_pkey PRIMARY KEY (room_id, user_room_id),
    CONSTRAINT rooms_master_id_key UNIQUE (room_id)
);


CREATE TABLE IF NOT EXISTS public.room_price
(
    room_price_id     SERIAL                      NOT NULL PRIMARY KEY,
    room_id           bigint                      NOT NULL,
    price_range_from  timestamp without time zone NOT NULL,
    price_range_to    timestamp without time zone NOT NULL,
    base_price        double precision            NOT NULL,
    extra_adult_price double precision            NOT NULL,
    extra_child_price double precision            NOT NULL,
    base_adult_count  numeric default 2,
    CONSTRAINT room_price_room_id_fkey FOREIGN KEY (room_id)
        REFERENCES public.rooms_master (room_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);


CREATE TABLE IF NOT EXISTS public.booking
(
    booking_id          SERIAL                      NOT NULL,
    room_id             bigint                      NOT NULL,
    booking_from_date   timestamp without time zone NOT NULL,
    booking_to_date     timestamp without time zone NOT NULL,
    adult_count         numeric                     NOT NULL,
    child_count         numeric                     NOT NULL,
    child_age           numeric[],
    max_child_age_limit numeric                     NOT NULL,
    total_amount        double precision            NOT NULL,
    CONSTRAINT booking_pkey PRIMARY KEY (booking_id),
    CONSTRAINT booking_room_id_fkey FOREIGN KEY (room_id)
        REFERENCES public.rooms_master (room_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE table if not exists public.booking_detail
(
    booking_dtl_id    SERIAL                      NOT NULL PRIMARY KEY,
    booking_id        bigint                      NOT NULL,
    room_id           bigint                      NOT NULL,
    price_from_date   timestamp without time zone NOT NULL,
    price_to_date     timestamp without time zone NOT NULL,
    base_price        double precision            NOT NULL,
    extra_adult_price double precision            NOT NULL,
    extra_child_price double precision            NOT NULL,
    total_period_cost double precision            NOT NULL
);

Create table room_booking_entity_child_age
(
    room_booking_entity_booking_id bigint,
    child_age                      integer
);