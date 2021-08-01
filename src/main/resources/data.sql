DROP TABLE IF EXISTS "blog" cascade ;
CREATE TABLE "blog"
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    title VARCHAR(60) NOT NULL,
    content TEXT NOT NULL
);

DROP TABLE IF EXISTS "comment" cascade ;
CREATE TABLE "comment"
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    content VARCHAR(300) NOT NULL,
    blog_id BIGINT REFERENCES blog(id),
    user_id BIGINT REFERENCES "user"(id)
);

DROP TABLE IF EXISTS "user" cascade ;
CREATE TABLE "user"
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS "role" cascade ;
CREATE TABLE role
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) UNIQUE NOT NULL
);


DROP TABLE IF EXISTS "user_role" cascade ;
CREATE TABLE user_role
(
    user_id BIGINT REFERENCES "user"(id),
    role_id BIGINT REFERENCES role(id)
);

INSERT INTO role (name)
VALUES ('USER'),
       ('ADMIN');
