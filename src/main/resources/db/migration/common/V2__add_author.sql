drop table if exists author;
drop table if exists author_seq;

create table author (
                      id bigint not null,
                      first_name varchar(100),
                      last_name varchar(100),
                      primary key (id)
) engine=InnoDB;

create table author_seq (
    next_val bigint
) engine=InnoDB;

insert into author_seq values ( 0 );