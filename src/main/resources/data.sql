DROP TABLE IF EXISTS "blog";

CREATE TABLE "blog"
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    title VARCHAR(60) NOT NULL,
    content TEXT NOT NULL
);