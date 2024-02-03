# review (*)-(1) member

drop table if exists review;
create table review (
  review_id bigint auto_increment primary key,
  email varchar(100) not null,
  title varchar(100) not null,
  content longtext not null,
  store_name varchar(100),
  store_address varchar(200),
  thumbnail_url varchar(300),
  thumbnail_file_name varchar(200),
  star int,
  good bigint default 0,
  hits bigint default 0,
  reg_date timestamp default current_timestamp,
  mod_date timestamp default current_timestamp on update current_timestamp,
  foreign key (email) references member (email) on update cascade on delete cascade
);

# email (fk)
#reply_id (fk)

