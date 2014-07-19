

/**User table*/
CREATE TABLE user(
	uid int(11) primary key auto_increment,
	username varchar(100) not null,
	password varchar(100) not null,
	name varchar(100) not null,
	email varchar(100) not null,
	curcity varchar(100),
	created_at timestamp default now(),
	updated_at timestamp
);

CREATE TABLE container(
	containerID int(11) primary key auto_increment,
	imageDirectory varchar(100),
	location varchar(100) not null,
	destination varchar(100) not null,
	status int(11) not null,
	departureDate varchar(100) not null,
	arrivalDate varchar(100)
);

CREATE TABLE containerimage(
	ciid int(11) primary key auto_increment,
	filename varchar(100) not null,
	containerID int(11) not null 
);
/*insert*/
INSERT INTO `woltonguesthous`.`user` 
(`uid`, `username`, `password`, `name`, `curcity`, `created_at`, `updated_at`) 
VALUES 
(NULL, 'bandam', 'password', 'berim andam', 'goteborg', CURRENT_TIMESTAMP, '0000-00-00 00:00:00');

/*search */
SELECT *  FROM `user` WHERE `username` LIKE 'bandam'