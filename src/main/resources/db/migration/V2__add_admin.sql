INSERT INTO users (id, archive, email, name, password, role, bucket_id)
VALUES (1, false, 'mail@mail.ru', 'admin', '1234', 'ADMIN', null);

ALTER SEQUENCE user_seq RESTART WITH 2