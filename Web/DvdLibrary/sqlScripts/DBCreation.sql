drop database if exists DVDLibrary;
create database DVDLibrary;

use DVDLibrary;

create table DVDs (
	id int not null auto_increment,
    `title` varchar(50) not null,
    `rating` varchar(10) not null,
    `director` varchar(50) not null,
    `studio` varchar(50) not null,
    `note` varchar(200),
    `date` varchar(20) not null,
    primary key(id)
);