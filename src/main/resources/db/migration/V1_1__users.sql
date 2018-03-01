CREATE TABLE users (
	id bigserial primary key,
	email text not null,
	first_name text not null,
	last_name text not null,
	password text not null
);

INSERT INTO users (email, first_name, last_name, password) VALUES 
	('henrikeeble@googlemail.com', 'Henri', 'Keeble', 'bc9be2125b5564c5c90f4a705e4534d688bfa133ec09372d329ae285505383c14bd8e5c6704d4056'); 