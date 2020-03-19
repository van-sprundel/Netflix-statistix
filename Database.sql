/*Run this code either in SSMS or in your IDE*/
USE master;
DROP DATABASE IF EXISTS [Netflix Statistix Database];
CREATE DATABASE [Netflix Statistix Database];

USE [Netflix Statistix Database]
CREATE TABLE Account (
AccountID int IDENTITY(1,1) PRIMARY KEY,
Email NVARCHAR(50) NOT NULL,
Name NVARCHAR(25) NOT NULL,
Address NVARCHAR(50) NOT NULL,
Postalcode NCHAR(6) NOT NULL,
Password NVARCHAR(32) NOT NULL,
Admin bit DEFAULT 0,
)

CREATE TABLE Profile (
ProfileID int IDENTITY(1,1) PRIMARY KEY,
AccountID int FOREIGN KEY REFERENCES Account(AccountID),
Username NVARCHAR(25),
BirthDate DATE,
)

CREATE TABLE Series (
ShowID int IDENTITY(1,1) PRIMARY KEY,
Name NVARCHAR(25) NOT NULL,
Genre NVARCHAR(20) NOT NULL,
Language NVARCHAR(20) NOT NULL,
MinAge int NOT NULL,
)


CREATE TABLE Episode (
Season int NOT NULL,
EpisodeID int IDENTITY(1,1) PRIMARY KEY,
Title NVARCHAR(25) NOT NULL,
Duration TIME,
)

CREATE TABLE Movies (
MovieID int IDENTITY(1,1) PRIMARY KEY,
Title NVARCHAR(25) NOT NULL,
Duration TIME NOT NULL,
Genre NVARCHAR(20) NOT NULL,
Language NVARCHAR(20) NOT NULL,
MinAge int NOT NULL,
)

CREATE TABLE WatchedMovies (
ProfileID int FOREIGN KEY REFERENCES Profile(ProfileID),
MovieID int FOREIGN KEY REFERENCES Movies(MovieID),
WatchedTime TIME,
)

CREATE TABLE WatchedEpisodes (
ProfileID int FOREIGN KEY REFERENCES Profile(ProfileID),
EpisodeID int FOREIGN KEY REFERENCES Episode(EpisodeID),
WatchedTime TIME,
)
 

/* This is the default admin login, please change this to something else, for security reasons. */
INSERT INTO Account(Email,Name,Address,Postalcode,Password,Admin)
VALUES('admin@gmail.com','admin','John Doe Lane 01','1234AB','password',1);

/* Optionally you can add generated users into the system. */
INSERT INTO Account(Email,Name,Address,Postalcode,Password,Admin)
VALUES('user1@gmail.com','user1','John Doe Lane 01','1234AB','password',0);
INSERT INTO Account(Email,Name,Address,Postalcode,Password,Admin)
VALUES('user2@gmail.com','user2','John Doe Lane 02','1234AB','password',0);
INSERT INTO Account(Email,Name,Address,Postalcode,Password,Admin)
VALUES('user3@gmail.com','user3','John Doe Lane 03','1234AB','password',0);

/* Here are some sample movies and series mixed. You can add your own to your liking. Follow the syntax. */
INSERT INTO Series(Name,Genre,Language,MinAge)
VALUES('Sherlock','Detective','Engels',12);

INSERT INTO Episode(Season,Title,Duration) VALUES(1,'A Study in Pink','01:28');
INSERT INTO Episode(Season,Title,Duration) VALUES(1,'The Blind Banker','01:28');
INSERT INTO Episode(Season,Title,Duration) VALUES(1,'The Great Game','01:28');
INSERT INTO Episode(Season,Title,Duration) VALUES(2,'A Scandal in Belgravia','01:28');
INSERT INTO Episode(Season,Title,Duration) VALUES(2,'The Hounds of Baskerville','01:28');
INSERT INTO Movies(Title,Duration,Genre,Language,MinAge) VALUES('The Life of Brian','01:34','Humor','Engels',0);