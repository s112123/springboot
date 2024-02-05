# member (1)-(*) subscribe (*)-(1) member

drop table if exists subscribe;
create table subscribe (
  subscribe_id bigint auto_increment primary key,
  subscriber_email varchar(100) not null,
  publisher_email varchar(100) not null,
  foreign key (subscriber_email) references member (email) on update cascade on delete cascade,
  foreign key (publisher_email) references member (email) on update cascade on delete cascade
);



