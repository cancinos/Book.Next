user_bookselect * from users;

insert into users(username,fullname,birthday,passwoord,imagen,country) values ('test1','eltest','23/08/1994','123pass','google','guate');
delete from users where username = 'test';
UPDATE `booknext`.`users` SET `birthday`= DATE('2003-12-31 01:02:03') WHERE `id`='1';



CREATE TABLE users (

id int auto_increment primary key,
username varchar(20),
fullname varchar(20),
birthday varchar(20),
passwoord varchar(20),
imagen varchar(200),
country varchar(20)

);


CREATE TABLE book (

isbn int primary key,
book_name varchar(100),
author varchar (80),
imagen varchar (200),
publish_date varchar(20),
publisher varchar(40),
rating_average float (100,1),
description varchar (200)

);

CREATE TABLE user_book (

   
isbn int,
id int,	
user_rating int(1),
user_liked int(1),
user_view int(1),
user_saved int(1),

FOREIGN KEY (isbn)
      REFERENCES book(isbn),

FOREIGN KEY (id)
      REFERENCES users(id)   

);

CREATE TABLE genere (

id_genere int primary key,
genere_name varchar(50)

);

CREATE TABLE book_genere (

 isbn int,
 id int, 

FOREIGN KEY (isbn)
      REFERENCES book(isbn),
      
FOREIGN KEY (id)
      REFERENCES users(id)
      
);


drop table book_genere;
drop table genere;
drop table user_book;
drop table book;
drop table users;