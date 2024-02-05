# review (1)-(*) good (*)-(1) member

drop table if exists good;
create table good (
  good_id bigint auto_increment primary key,
  email varchar(100) not null,
  review_id bigint  not null,
  reg_date timestamp default current_timestamp,
  foreign key (email) references member (email) on delete cascade,
  foreign key (review_id) references review (review_id) on delete cascade
);