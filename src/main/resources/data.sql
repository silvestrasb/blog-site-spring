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
    blog_id BIGINT REFERENCES blog(id)
);