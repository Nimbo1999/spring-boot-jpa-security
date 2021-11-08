INSERT INTO users(username, password) values('admin', '$2a$10$StQDQ9JfplqJE/X6xiRDneGJrIm58bN6n7y5Qxy9Ld/HlEklj9VPy');
INSERT INTO users(username, password) values('comum', '$2a$10$StQDQ9JfplqJE/X6xiRDneGJrIm58bN6n7y5Qxy9Ld/HlEklj9VPy');

INSERT INTO authorities(authority) values('ALL');
INSERT INTO authorities(authority) values('READ');

INSERT INTO users_authorities(user_id, authority_id) values(1, 1);
INSERT INTO users_authorities(user_id, authority_id) values(2, 2);
