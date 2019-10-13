create table genres(id int, name varchar(20));

create table languages(id int, name varchar(20));

create table movies(id int IDENTITY(1,1), name varchar(40), releaseYear int, genreId int, languageId int, 
story varchar(200), 
createdBy int, 
createdTimestamp datetime, lastUpdtTimestamp datetime, 
active varchar(1), base64Img varchar(MAX));

create table reviews(ID int IDENTITY(1,1), movieId int, createdUserId int, createdUserName varchar(30),
likeMovie varChar(1), comments varchar(200), rating float, createTimestamp datetime);

create table ratings(ID int IDENTITY(1,1), movieId int, likes int, 
dislike int, rating float, totalRatings int, createTimestamp datetime);

create table users(id int IDENTITY(1,1), firstName varchar(30), lastName varchar(30), emailId varchar(30), password varchar(150), admin varchar(1), 
createdTimestamp datetime, active varchar(1));