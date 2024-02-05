# member (1)-(*) notification

drop table if exists notification;
create table notification (
  notification_id bigint auto_increment primary key,
  from_email varchar(100) not null,
  to_email varchar(100) not null,
  category varchar(100) not null,
  content varchar(500) not null,
  url varchar(300),
  is_read boolean default 0,
  reg_date timestamp default current_timestamp,
  foreign key (from_email) references member (email) on update cascade on delete cascade,
  foreign key (to_email) references member (email) on update cascade on delete cascade
);