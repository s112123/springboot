-- member table
drop table if exists member;
create table member (
  member_id bigint auto_increment primary key,
  email varchar(100) not null,
  password varchar(350) not null,
  username varchar(50) not null,
  role varchar(20) not null default 'user',
  reg_date timestamp default current_timestamp,
  mod_date timestamp default current_timestamp on update current_timestamp
);