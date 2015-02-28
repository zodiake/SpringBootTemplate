create table ncuser(
	id int not null ,
	name varchar(20) not null,
	password varchar(30) not null,
	createdTime timestamp,
	primary key(id),
);


create table content(
	id int not null,
	content varchar(10000),
	primary key(id),
);

create table post(
	id int not null ,
	title varchar(30) not null,
	praise int,
	user_id int,
	createdTime timestamp,
	updatedTime timestamp,
	content_id int,
	FOREIGN KEY (user_id) REFERENCES ncuser(id),
	FOREIGN KEY (content_id) REFERENCES content(id),
	primary key(id),
);

create table authority(
	id int not null,
	name varchar(20),
	user_id int,
	primary key(id),
	FOREIGN KEY (user_id) REFERENCES ncuser(id),
);