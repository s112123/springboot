drop table if exists review;
create table review (
  review_id bigint auto_increment primary key,
  title varchar(100) not null,
  content longtext not null,
  store_name varchar(100),
  store_address varchar(200),
  thumbnail_url varchar(300),
  thumbnail_file_name varchar(200),
  star int,
  good int,
  hits int,
  reg_date timestamp,
  mod_date timestamp
);


#member_id (fk)
#reply_id (fk)

