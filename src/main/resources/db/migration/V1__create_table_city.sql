create table city
(
    id                 integer primary key      not null,
    name               varchar                  not null,
    state              varchar                  not null,
    country            varchar                  not null,
    lat                varchar                  not null,
    lon                varchar                  not null,
    number_of_requests bigint                   not null,
    city_not_found     bool    default false    not null,
    version            bigint,

    created_at         timestamp with time zone not null,
    updated_at         timestamp with time zone
);