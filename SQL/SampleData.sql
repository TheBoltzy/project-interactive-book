/*
 Inser sample books
 */
INSERT INTO books (title, author, created_at)
VALUES ('The great Book', 1, NOW());
INSERT INTO books (title, author, created_at)
VALUES ('The Bird is Green', 1, NOW());
INSERT INTO books (title, author, created_at)
VALUES ('This is Java', 2, NOW());
INSERT INTO books (title, author, created_at)
VALUES ('How not to code', 3, NOW());
/*
 Inser sample chapter
 */
INSERT INTO chapters (book_id, chapter_number, chapter_body, created_at)
VALUES (1, 1, 'This is a great chapter 1', NOW());
INSERT INTO chapters (book_id, chapter_number, chapter_body, created_at)
VALUES (1, 2, 'This is a great chapter 2', NOW());
INSERT INTO chapters (book_id, chapter_number, chapter_body, created_at)
VALUES (1, 3, 'This is a great chapter 3', NOW());
INSERT INTO chapters (book_id, chapter_number, chapter_body, created_at)
VALUES (1, 4, 'This is a great chapter 4', NOW());
INSERT INTO chapters (book_id, chapter_number, chapter_body, created_at)
VALUES (2, 1, 'This is a great chapter 1', NOW());
INSERT INTO chapters (book_id, chapter_number, chapter_body, created_at)
VALUES (2, 2, 'This is a great chapter 2', NOW());
INSERT INTO chapters (book_id, chapter_number, chapter_body, created_at)
VALUES (3, 1, 'This is a great chapter 3', NOW());
INSERT INTO chapters (book_id, chapter_number, chapter_body, created_at)
VALUES (4, 1, 'This is a great chapter 4', NOW());
/*
 Inser sample chapter
 */
INSERT INTO comments (chapter_id, comment_body, created_at)
VALUES (1, 'some positive comment', NOW());
INSERT INTO comments (chapter_id, comment_body, created_at)
VALUES (2, 'wow', NOW());
INSERT INTO comments (chapter_id, comment_body, created_at)
VALUES (3, 'nice!', NOW());
INSERT INTO comments (chapter_id, comment_body, created_at)
VALUES (4, 'ok', NOW());
INSERT INTO comments (chapter_id, comment_body, created_at)
VALUES (5, 'change something', NOW());
INSERT INTO comments (chapter_id, comment_body, created_at)
VALUES (6, 'very good', NOW());
INSERT INTO comments (chapter_id, comment_body, created_at)
VALUES (7, 'great', NOW());
INSERT INTO comments (chapter_id, comment_body, created_at)
VALUES (8, 'rice and beans', NOW());
/*
 Inser sample users
 */
INSERT INTO users (username, password, role)
VALUES ('camila98', 'password', 'EDITOR');
INSERT INTO users (username, password, role)
VALUES ('user', 'password', 'AUTHOR');
INSERT INTO users (username, password, role)
VALUES ('123', '456', 'AUTHOR');