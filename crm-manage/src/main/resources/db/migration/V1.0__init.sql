create table book (
	id bigint,
	douban_id varchar(64) not null,
	title varchar(128) not null,
	url varchar(255),
	description varchar(255),
	owner_id bigint not null,
	onboard_date timestamp,
	status varchar(32) not null,
	borrower_id bigint null,
	borrow_date timestamp,
    primary key (id)
);

create table account (
	id bigint,
	name varchar(64) not null,
	email varchar(128),
	hash_password varchar(255),
	primary key (id)
);


insert into book (id, douban_id, title, url, description, owner_id,status,onboard_date) values(1,'25984046', 'Big Data日知录', 'http://book.douban.com/subject/25984046/','', 1,'idle','2015-01-01');
insert into book (id, douban_id, title, url, description, owner_id,status,onboard_date) values(2,'25900156', 'Redis设计与实现', 'http://book.douban.com/subject/25900156/','', 1,'idle','2015-01-02');
insert into book (id, douban_id, title, url, description, owner_id,status,onboard_date) values(3,'25741352', 'DSL实战', 'http://book.douban.com/subject/25741352/','', 2,'idle','2015-01-03');

insert into account (id,email,name,hash_password) values(1,'calvin.xiao@springside.io','Calvin','+2MunThvGcEfdYIFlT4NQQHt6z4=');
insert into account (id,email,name,hash_password) values(2,'david.wang@springside.io','David','+2MunThvGcEfdYIFlT4NQQHt6z4=');