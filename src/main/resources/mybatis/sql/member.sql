drop table if exists member;
create table member (
  member_id bigint auto_increment primary key,
  email varchar(100),
  password varchar(500),
  nick_name varchar(50),
  image_url varchar(200),
  file_name varchar(300),
  reg_date timestamp,
  mod_date timestamp
);

# status (online, offline)