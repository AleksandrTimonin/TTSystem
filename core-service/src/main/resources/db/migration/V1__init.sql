create table orders
(
    id                  bigserial primary key,
    is_active           boolean not null,

    title               varchar(100) not null,
    description         varchar(2000) not null,

    username            varchar(100) not null,

    status              varchar(16) not null,
    executors           varchar(500),


    completed_at        timestamp,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);
create table processes
(
    id                  bigserial primary key,
    order_id            bigserial not null,
    is_active           boolean not null,
    on_confirm          boolean not null,

    executor            varchar(100) not null,


    assigned_at         timestamp default current_timestamp,
    accepted_at         timestamp,
    finished_at         timestamp,

    updated_at          timestamp default current_timestamp,
    FOREIGN KEY (order_id)  REFERENCES orders (id)
);
create table commits (
        id                  bigserial primary key,
        order_id            bigserial not null,
        executor_commit     varchar(2000),
        created_at          timestamp default current_timestamp,
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
values (1,true,false,'admin');
insert into commits (order_id, executor_commit)
values (1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.');








