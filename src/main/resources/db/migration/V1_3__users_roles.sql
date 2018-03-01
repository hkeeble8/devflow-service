CREATE TABLE users_roles (
	id bigserial primary key,
	role_id bigint REFERENCES roles (id),
	user_id bigint REFERENCES users(id)
);

INSERT INTO users_roles (role_id, user_id) VALUES
	((SELECT id FROM roles WHERE label = 'SUPER_ADMIN'), (SELECT id FROM users WHERE email = 'henrikeeble@googlemail.com'));
