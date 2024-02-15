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

# review_image (*)-(1) review
drop table if exists review_image;
create table review_image (
  review_image_id bigint auto_increment primary key,
  review_id bigint not null,
  upload_file_name varchar(100) not null,
  foreign key (review_id) references review (review_id) on update cascade on delete cascade
);