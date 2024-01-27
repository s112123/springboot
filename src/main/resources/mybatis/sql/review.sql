drop table if exists review;
create table review (
  review_id bigint auto_increment primary key,
  title varchar(100) not null,
  content longtext not null
);


#member_id (fk)
#reply_id (fk)
#store_name
#store_address
#star
#hits
#goods
#ret_date
#mod_date

