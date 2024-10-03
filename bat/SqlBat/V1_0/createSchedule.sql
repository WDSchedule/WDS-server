use wds;
create table schedule(
    id bigint primary key auto_increment,
    user_id int not null,
    title varchar(50) not null,
    description varchar(500),
    schedule_group varchar(10),
    start_time datetime,
    end_time datetime,
    is_reminder bool,
    status int not null ,
    create_time datetime not null ,
    update_time datetime not null,
    index index_user_id(user_id),
    foreign key (user_id) references user(id) on delete cascade
);