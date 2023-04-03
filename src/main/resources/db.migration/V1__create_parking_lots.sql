create table if not exists parking_lots
(
    id        uuid              not null primary key,
    latitude  float8            not null,
    longitude float8            not null,
    name      varchar           not null,
    year      integer           not null,
    type      character varying not null
);
