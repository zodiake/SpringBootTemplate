--test user
insert into ncuser (id,name,password) values (1,'tom','1');

--test post content
insert into content(id,content) values(1,'test content');

--test post
insert into post (id,title,user_id,content_id) values(1,'test title',1,1);
