# member (1)-(*) chatroom (1)-(*) chat

drop table if exists chatroom;
create table chatroom (
  chat_room_id bigint auto_increment primary key,
  sender_receiver varchar(200) unique not null,
  sender_email varchar(100) not null,
  receiver_email varchar(100) not null,
  reg_date timestamp default current_timestamp,
  foreign key (sender_email) references member (email) on update cascade on delete cascade,
  foreign key (receiver_email) references member (email) on update cascade on delete cascade
);

drop table if exists chat;
create table chat (
  chat_id bigint auto_increment primary key,
  sender_receiver varchar(200) not null,
  sender_email varchar(100) not null,
  receiver_email varchar(100) not null,
  message text not null,
  is_read boolean default false,
  reg_date timestamp default current_timestamp,
  foreign key (sender_receiver) references chatroom (sender_receiver) on update cascade on delete cascade,
  foreign key (sender_email) references member (email) on update cascade on delete cascade,
  foreign key (receiver_email) references member (email) on update cascade on delete cascade
);


# nick_name varchar(50)
# image_url varchar(200)