drop table if exists "Users";

create table "Users" (
    id bigserial primary key,
    email text unique not null,
    password text not null
)