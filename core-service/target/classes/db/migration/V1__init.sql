create table orders
(
    id                  bigserial primary key,
    is_active           boolean not null,

    title               varchar(100) not null,
    description         varchar(2000) not null,

    username            varchar(100) not null,

    status              varchar(16) not null,
    executors           varchar(500),
    executor_commit     varchar(2000),

    completed_at        timestamp,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);
create table processes
(
    id                  bigserial primary key,
    order_id            bigserial not null,
    is_active           boolean not null,

    executor            varchar(100) not null,


    assigned_at         timestamp default current_timestamp,
    accepted_at         timestamp,
    postponed_at        timestamp,
    finished_at         timestamp,

    updated_at          timestamp default current_timestamp,
    FOREIGN KEY (order_id)  REFERENCES orders (id)
);






insert into orders (is_active,title, description, username, status,executors)
values ( true,
        'create app',
        'blabla bla uchet vremeni bla bla blalalalala',
        'admin',
        'expected',
        'admin');

insert into processes (order_id, is_active,executor)
values (1,true,'admin');








