create table ncuser(
	id int not null AUTO_INCREMENT,
	name varchar(20) not null,
	password varchar(30) not null,
	created_time timestamp,
	primary key(id),
);


create table content(
	id int not null AUTO_INCREMENT,
	content varchar(10000),
	primary key(id),
);

create table post(
	id int not null AUTO_INCREMENT,
	title varchar(30) not null,
	praise int default 0,
	user_id int,
	created_time timestamp,
	updated_time timestamp,
	content_id int,
	FOREIGN KEY (user_id) REFERENCES ncuser(id),
	FOREIGN KEY (content_id) REFERENCES content(id),
	primary key(id),
);

create table authority(
	id int not null AUTO_INCREMENT,
	name varchar(20),
	user_id int,
	primary key(id),
	FOREIGN KEY (user_id) REFERENCES ncuser(id),
);