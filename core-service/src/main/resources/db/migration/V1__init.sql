create table orders
(
    id                  bigserial primary key,

    title               varchar(100) not null,
    description         varchar(2000) not null,
    username            varchar(100) not null,

    executor            varchar(100) not null,
    executor_commit     varchar(2000) not null,

    status              varchar(16) not null,

    assignment          timestamp,
    start_progress      timestamp,
    executed            timestamp,

    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);






insert into orders (title, description, username, executor, executor_commit, status)
values ('create app',
        'blabla bla uchet vremeni bla bla blalalalala',
        'admin',
        'admin',
        'blalbalbla',
        'expected');











