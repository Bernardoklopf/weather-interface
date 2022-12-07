create table temperature
(
    id                 varchar primary key      not null,
    city_id            integer                  not null,
    date               timestamp with time zone not null,
    temp               decimal                  not null,
    feels_like         decimal                  not null,
    temp_max           decimal                  not null,
    temp_min           decimal                  not null,

    constraint fk_city_id foreign key (city_id) references city (id) on delete cascade on update cascade
);