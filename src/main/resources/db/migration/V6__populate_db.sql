INSERT INTO users(username, password) values('admin', '$2a$10$StQDQ9JfplqJE/X6xiRDneGJrIm58bN6n7y5Qxy9Ld/HlEklj9VPy');
INSERT INTO users(username, password) values('comum', '$2a$10$StQDQ9JfplqJE/X6xiRDneGJrIm58bN6n7y5Qxy9Ld/HlEklj9VPy');

INSERT INTO user_roles(role, user_id) values('ADMIN', 1);
INSERT INTO user_roles(role, user_id) values('COMMON_USER', 2);