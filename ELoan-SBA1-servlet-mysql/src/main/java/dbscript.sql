DROP DATABASE IF EXISTS loanmanagement;

CREATE DATABASE loanmanagement;

USE loanmanagement;

CREATE TABLE loaninfo(
applno INT PRIMARY KEY AUTO_INCREMENT,
user_name varchar(20) NOT NULL,
purpose VARCHAR(40) NOT NULL,
amtrequest INT(40) NOT NULL,
doa VARCHAR(20),
bstructure VARCHAR(20),
bindicator VARCHAR(20),
tindicator VARCHAR(20),
address VARCHAR(40),
email VARCHAR(20),
mobile CHAR(10) NOT NULL UNIQUE,
status VARCHAR(20)
);


 
 CREATE TABLE users(
 	user_name varchar(20) primary key,
 	password varchar(20) not null,
 	role varchar(10) not null
 ); 
 
 INSERT INTO users VALUES
 ('admin','admin','ADMIN');
 	

 COMMIT;
