INSERT INTO users(username, password) values('admin', '$2a$10$StQDQ9JfplqJE/X6xiRDneGJrIm58bN6n7y5Qxy9Ld/HlEklj9VPy');
INSERT INTO users(username, password) values('comum', '$2a$10$StQDQ9JfplqJE/X6xiRDneGJrIm58bN6n7y5Qxy9Ld/HlEklj9VPy');

INSERT INTO roles(role) values('ADMIN');
INSERT INTO roles(role) values('COMMON_USER');

INSERT INTO users_roles(user_id, role_id) values(1, 1);
INSERT INTO users_roles(user_id, role_id) values(2, 2);
