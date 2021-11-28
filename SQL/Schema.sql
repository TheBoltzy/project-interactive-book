create table books(
    book_id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author INT NOT NULL,
    created_at DATE NOT NULL,
    PRIMARY KEY (book_id)
);
create table chapters(
    chapter_id INT NOT NULL AUTO_INCREMENT,
    book_id INT NOT NULL,
    chapter_number INT NOT NULL,
    chapter_body TEXT,
    created_at DATE NOT NULL,
    PRIMARY KEY (chapter_id)
);
create table comments(
    comment_id INT NOT NULL AUTO_INCREMENT,
    chapter_id INT NOT NULL,
    comment_body TEXT NOT NULL,
    created_at DATE NOT NULL,
    PRIMARY KEY (comment_id)
);
create table users(
    user_id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(100) NOT NULL,
    PRIMARY KEY (user_id)
);