DROP SCHEMA IF EXISTS BookNext;
DROP USER booknext;

create schema BookNext;
create user 'booknext' identified by 'book';

Grant all privileges on booknext.* to 'booknext';



use BookNext;

DROP TABLE IF EXISTS  book_genere;

DROP TABLE IF EXISTS  genere;

DROP TABLE IF EXISTS  user_book;

DROP TABLE IF EXISTS  book;

DROP TABLE IF EXISTS  users;


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


insert into users(username,fullname,birthday,passwoord,imagen,country) values ('Durini','Juan Carlos Duniri','23/08/1994','123pass','https://scontent-atl3-1.xx.fbcdn.net/v/t1.0-9/10306748_10152843932139579_7297473898267847923_n.jpg?oh=fc106c6a6fa384cb595f8dc75d668e28&oe=57AED28C','Guatemala');

insert into users(username,fullname,birthday,passwoord,imagen,country) values ('Cancinos','Pablo Cancinos','23/08/1994','123pass','https://scontent-atl3-1.xx.fbcdn.net/hphotos-xap1/v/t1.0-9/12801640_10153483713100983_577201434795815826_n.jpg?oh=263141cbcffb5d4a22718a3c5f56b4e4&oe=57A57DC9','Guatemala');

insert into users(username,fullname,birthday,passwoord,imagen,country) values ('Queme','Pablo Queme','23/08/1994','123pass','https://scontent-atl3-1.xx.fbcdn.net/v/t1.0-9/12512703_10153892622892726_5037909035613453449_n.jpg?oh=d0ddcdaa27ee81b3b908478feaa689bd&oe=57A6B22C','Guatemala');

insert into users(username,fullname,birthday,passwoord,imagen,country) values ('Ludwing','Ludwing Cano','23/08/1994','123pass','https://scontent-atl3-1.xx.fbcdn.net/t31.0-8/s960x960/12068740_10153524436238100_3708907581614942786_o.jpg','Guatemala');

