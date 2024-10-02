use wds;
create table schedule_summary(
    id bigint primary key auto_increment,
    user_id int not null,
    title varchar(50) not null,
    schedule_group varchar(10),
    start_time datetime,
    end_time datetime,
    create_time datetime not null ,
    update_time datetime not null,
    index index_user_id(user_id),
    foreign key (user_id) references user(id) on delete cascade
);