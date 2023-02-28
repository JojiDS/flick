create sequence task_seq start with 1 increment by 50;
create sequence users_seq start with 1 increment by 50;
create table task(
    id bigint not null,
    responsible varchar(255),
    status varchar(255),
    text varchar(2048),
    primary key (id));
create table users(id bigint not null,
 password varchar(255) not null,
  username varchar(255) not null,
   primary key (id));
alter table if exists users add constraint unik_key unique (username)
