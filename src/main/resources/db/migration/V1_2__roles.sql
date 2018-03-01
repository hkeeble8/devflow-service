CREATE TABLE roles (
	id bigserial primary key,
	label text not null
);

INSERT INTO roles (label) VALUES ('SUPER_ADMIN');