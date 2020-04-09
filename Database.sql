/*Run this code either in SSMS or in your IDE*/
USE master;
DROP DATABASE IF EXISTS [Netflix Statistix Database];
CREATE DATABASE [Netflix Statistix Database];

USE [Netflix Statistix Database]
CREATE TABLE Account
(
    AccountID  int IDENTITY (1,1) PRIMARY KEY,
    Email      NVARCHAR(50) NOT NULL,
    Username   NVARCHAR(25) NOT NULL,
    FullName   NVARCHAR(25) NOT NULL,
    Address    NVARCHAR(50) NOT NULL,
    Postalcode NCHAR(6)     NOT NULL,
    Password   NVARCHAR(32) NOT NULL,
    Admin      bit DEFAULT 0,
)

CREATE TABLE Profile
(
    ProfileID int IDENTITY (1,1) PRIMARY KEY,
    AccountID int FOREIGN KEY REFERENCES Account (AccountID),
    Username  NVARCHAR(25),
    BirthDate DATE,
)

CREATE TABLE Series
(
    SerieID   int IDENTITY (1,1) PRIMARY KEY,
    Title     NVARCHAR(25) NOT NULL,
    Genre    NVARCHAR(20) NOT NULL,
    Language NVARCHAR(20) NOT NULL,
    MinAge   int          NOT NULL,
)

CREATE TABLE Episode
(
    EpisodeID int IDENTITY (1,1) PRIMARY KEY,
    SerieID int FOREIGN KEY REFERENCES Series,
    Season    int          NOT NULL,
    Title    NVARCHAR(50) NOT NULL,
    Duration  TIME,
)

CREATE TABLE Movies
(
    MovieID  int IDENTITY (1,1) PRIMARY KEY,
    Title    NVARCHAR(50) NOT NULL,
    Duration TIME         NOT NULL,
    Genre    NVARCHAR(20) NOT NULL,
    Language NVARCHAR(20) NOT NULL,
    MinAge   int          NOT NULL,
)

CREATE TABLE WatchedMovies
(
    ProfileID   int FOREIGN KEY REFERENCES Profile (ProfileID),
    MovieID     int FOREIGN KEY REFERENCES Movies (MovieID),
    WatchedTime TIME,
)

CREATE TABLE WatchedEpisodes
(
    ProfileID   int FOREIGN KEY REFERENCES Profile (ProfileID),
    EpisodeID   int FOREIGN KEY REFERENCES Episode (EpisodeID),
    WatchedTime TIME,
)

/* This is the default admin login, please change this to something else, for security reasons. */
INSERT INTO Account(Email, Username, Fullname, Address, Postalcode, Password, Admin)
VALUES ('admin@gmail.com', 'admin', 'Cool Name', 'John Doe Lane 01', '1234AB', 'password', 1);

/* Optionally you can add generated users into the system. */
INSERT INTO Account(Email, Username, Fullname, Address, Postalcode, Password, Admin)
VALUES ('user1@gmail.com', 'user1', 'User Name', 'John Doe Lane 01', '1234AB', 'password', 0);
INSERT INTO Account(Email, Username, Fullname, Address, Postalcode, Password, Admin)
VALUES ('user2@gmail.com', 'user2', 'User Name', 'John Doe Lane 02', '1234AB', 'password', 0);
INSERT INTO Account(Email, Username, Fullname, Address, Postalcode, Password, Admin)
VALUES ('user3@gmail.com', 'user3', 'User Name', 'John Doe Lane 03', '1234AB', 'password', 0);
INSERT INTO Profile(AccountID, Username, BirthDate)
VALUES (2, 'user2 profile', '06-15-2000');

/* Here are some sample movies and series mixed. You can add your own to your liking. Follow the syntax. */

/* Sherlock Dataset */
INSERT INTO Series(Title, Genre, Language, MinAge)
VALUES ('Sherlock', 'Detective', 'Engels', 12);
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (1,1, 'A Study in Pink', '01:28');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (1,1, 'The Blind Banker', '01:28');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (1,1, 'The Great Game', '01:28');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (1,2, 'A Scandal in Belgravia', '01:28');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (1,2, 'The Hounds of Baskerville', '01:28');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (1,2, 'The Reichenbach Fall', '01:28');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (1,3, 'The Empty Hearse', '01:28');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (1,3, 'The Sign of Three', '01:28');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (1,3, 'His Last Vow', '01:28');

/* Sherlock Special Dataset */
INSERT INTO Movies(Title, Duration, Genre, Language, MinAge)
VALUES ('The Abominable Bride', '01:29', 'Detective', 'Engels', 12);

/* Breaking Bad Dataset */
INSERT INTO Series(Title, Genre, Language, MinAge)
VALUES ('Breaking bad', 'Spanning', 'Engels-Amerikaans', 16);
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (2,1, 'Pilot', '00:58');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (2,1, 'Cat is in the bag...', '00:48');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (2,1, '...And the Bag is in the River', '00:48');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (2,1, 'Cancer Man', '00:48');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (2,1, 'Gray matter', '00:48');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (2,1, 'Crazy handful Of Nothin', '00:48');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (2,1, 'A No-Rough-Stuff-Type Deal', '00:48');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (2,2, 'Seven Thirty-Seven', '00:48');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (2,2, 'Grilled', '00:48');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (2,2, 'Bit by a Dead Bee', '00:48');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (2,2, 'Down', '00:48');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (2,2, 'Breakage', '00:48');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (2,2, 'Peekaboo', '00:48');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (2,2, 'Negro Y Azul', '00:48');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (2,2, 'Better Call Saul', '00:48');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (2,2, '4 Days Out', '00:48');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (2,2, 'Over', '00:48');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (2,2, 'Mandala', '00:48');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (2,2, 'Phoenix', '00:48');
INSERT INTO Episode(SerieID, Season, Title, Duration)
VALUES (2,2, 'ABQ', '00:48');

/* Other Movies */
INSERT INTO Movies(Title, Duration, Genre, Language, MinAge)
VALUES ('The Life of Brian', '01:34:00', 'Humor', 'Engels', 12);
INSERT INTO Movies(Title, Duration, Genre, Language, MinAge)
VALUES ('Pulp Fiction', '02:34:00', 'Misdaad', 'Engels-Amerikaans', 16);
INSERT INTO Movies(Title, Duration, Genre, Language, MinAge)
VALUES ('Pruimebloesem', '01:20:00', 'Erotiek', 'Nederlands', 18);
INSERT INTO Movies(Title, Duration, Genre, Language, MinAge)
VALUES ('Reservoir Dogs', '01:39:00', 'Misdaad', 'Engels-Amerikaans', 16);
INSERT INTO Movies(Title, Duration, Genre, Language, MinAge)
VALUES ('The Good, the Bad and the Ugly', '02:41:00', 'Western', 'Engels-Amerikaans', 12);
INSERT INTO Movies(Title, Duration, Genre, Language, MinAge)
VALUES ('Andy Warhols Dracula', '01:43:00', 'Humor', 'Engels-Amerikaans', 12);
INSERT INTO Movies(Title, Duration, Genre, Language, MinAge)
VALUES ('Ober', '01:37:00', 'Humor', 'Nederlands', 6);
INSERT INTO Movies(Title, Duration, Genre, Language, MinAge)
VALUES ('Der Untergang', '02:58:00', 'Oorlog', 'Duits', 6);
INSERT INTO Movies(Title, Duration, Genre, Language, MinAge)
VALUES ('De helaasheid der dingen', '01:48:00', 'Humor', 'Vlaams', 12);
INSERT INTO Movies(Title, Duration, Genre, Language, MinAge)
VALUES ('A Clockwork Orange', '02:16:00', 'SF', 'Engels', 12);

/* Profile has seen this movie */
INSERT INTO WatchedMovies(ProfileID, MovieID, WatchedTime)
VALUES (1, 4, '1:30')
