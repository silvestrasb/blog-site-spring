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

INSERT INTO "user" (username, password)
VALUES ('admin', '{bcrypt}$2a$10$WJvAKW5R1VM2SSaAWf0WYO/FBcovz6X3BpulRoS2FWdUbcCZPo8V2');


INSERT INTO user_role (user_id, role_id)
VALUES (1, 1),
       (1, 2);
