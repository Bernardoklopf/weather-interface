create table temperature
(
    id                 varchar primary key      not null,
    city_id            integer                  not null,
    date               timestamp with time zone not null,
    temp               decimal                  not null,
    feels_like         decimal                  not null,
    temp_max           decimal                  not null,
    temp_min           decimal                  not null,
    version            bigint,

    created_at        timestamp with time zone not null,
    updated_at        timestamp with time zone,

    constraint fk_city_id foreign key (city_id) references city (id) on delete cascade on update cascade
);