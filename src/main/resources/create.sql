create table player_statistic
(
    id       int auto_increment,
    name     varchar(50) not null,
    winCount int         not null,
    constraint player_statistic_id_uindex
        unique (id),
    constraint player_statistic_name_uindex
        unique (name)
);

alter table player_statistic
    add primary key (id);

