drop table if exists review;
create table review (
  review_id bigint auto_increment primary key,
  store varchar(200) not null,
  title varchar(100) not null,
  content longtext not null
);