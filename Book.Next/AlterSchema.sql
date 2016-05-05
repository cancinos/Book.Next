DROP TABLE IF EXISTS  genere;

DROP TABLE IF EXISTS  book_genere;	

ALTER TABLE book add COLUMN ( genere varchar(100));