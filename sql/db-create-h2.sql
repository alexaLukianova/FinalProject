DROP TABLE IF EXISTS users, roles, tests, questions, answers, results, complexity;

CREATE TABLE roles (
  id   INT,
  name VARCHAR(45) UNIQUE NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE complexity (
  id   INT,
  name VARCHAR(15) UNIQUE NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE users (
  id         INT     AUTO_INCREMENT,
  first_name VARCHAR(255),
  last_name  VARCHAR(255),
  username   VARCHAR(255) UNIQUE NOT NULL,
  password   VARCHAR(45)        NOT NULL,
  role_id    INT                NOT NULL,
  locked     BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (id),
  FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE tests (
  id            INT AUTO_INCREMENT,
  name          VARCHAR(255) NOT NULL,
  subject       VARCHAR(255) NOT NULL,
  complexity_id INT          NOT NULL,
  duration      INT,
  PRIMARY KEY (id),
  FOREIGN KEY (complexity_id) REFERENCES complexity (id)
);

CREATE TABLE questions (
  id      INT AUTO_INCREMENT,
  text    VARCHAR(255) NOT NULL,
  test_id INT          NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (test_id) REFERENCES tests (id)
  ON DELETE CASCADE
  ON UPDATE RESTRICT
);

CREATE TABLE answers (
  id          INT AUTO_INCREMENT,
  text        VARCHAR(255) NOT NULL,
  correct     BOOLEAN      NOT NULL,
  question_id INT          NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (question_id) REFERENCES questions (id)
  ON DELETE CASCADE
  ON UPDATE RESTRICT
);

CREATE TABLE results (
  id         INT AUTO_INCREMENT,
  test_id    INT NOT NULL,
  result     INT,
  start_time BIGINT,
  user_id    INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE RESTRICT,
  FOREIGN KEY (test_id) REFERENCES tests (id)
  ON DELETE CASCADE
  ON UPDATE RESTRICT
);

INSERT INTO roles VALUES (0, 'admin'), (1, 'student');

INSERT INTO complexity VALUES (0, 'easy'), (1, 'medium'), (2, 'hard');

INSERT INTO users (first_name, last_name, username, password, role_id)
VALUES ('Alexandra', 'Lukianova', 'admin', 'password', 0),
  ('Peter', 'Petrov', 'student', 'password', 1),
  ('Sidor', 'Sidorov', 'student1', 'password1', 1);

INSERT INTO tests (name, subject, complexity_id, duration)
VALUES ('Бобры', 'Зоология', 0, 10),
  ('Variable types', 'Java', 1, 20),
  ('HTML', 'Programming', 2, 20),
  ('SQL', 'Programming', 0, 20),
  ('Servlet', 'Java', 1, 20),
  ('Котики', 'Зоология', 2, 10);

INSERT INTO questions (text, test_id) VALUES
  ('Сколько лап у бобра обыкновенного?', 1),
  ('Сколько глаз у бобра обыкновенного?', 1),
  ('К какому отряду относится бобер обыкновенный?', 1),
  ('Где живут бобры?', 1),
  ('Сколько памятников бобру установлено в Бобруйске?', 1);

INSERT INTO answers (text, correct, question_id)
VALUES ('1', FALSE, 1),
  ('2', FALSE, 1),
  ('3', FALSE, 1),
  ('4', TRUE, 1),

  ('1', FALSE, 2),
  ('2', TRUE, 2),
  ('3', FALSE, 2),
  ('4', FALSE, 2),

  ('Грызуны', TRUE, 3),
  ('Черви', FALSE, 3),
  ('Ракообразные', FALSE, 3),
  ('Неполнозубые', FALSE, 3),

  ('Хатках', TRUE, 4),
  ('Норах', TRUE, 4),
  ('Коттеджах', FALSE, 4),
  ('Виллах', FALSE, 4),

  ('1', FALSE, 5),
  ('2', TRUE, 5),
  ('3', FALSE, 5),
  ('4', FALSE, 5);

INSERT INTO questions (text, test_id) VALUES
  ('What is the size of byte variable?', 2),
  ('What is the size of short variable?', 2),
  ('What is the size of long variable?', 2),
  ('What is the size of int variable?', 2),
  ('What is the default values of int data type?', 2),
  ('What is the default values of double data type?', 2);

INSERT INTO answers (text, correct, question_id)
VALUES ('8 bit', TRUE, 6),
  ('16 bit', FALSE, 6),
  ('32 bit', FALSE, 6),
  ('64 bit', FALSE, 6),

  ('1 byte', FALSE, 7),
  ('2 bytes', TRUE, 7),
  ('4 bytes', FALSE, 7),
  ('8 bytes', FALSE, 7),

  ('1 byte', FALSE, 8),
  ('2 bytes', FALSE, 8),
  ('4 bytes', FALSE, 8),
  ('8 bytes', TRUE, 8),

  ('1 byte', FALSE, 9),
  ('2 bytes', FALSE, 9),
  ('4 bytes', TRUE, 9),
  ('8 bytes', FALSE, 9),

  ('0', TRUE, 10),
  ('OL', FALSE, 10),
  ('0.0d', FALSE, 10),
  ('null', FALSE, 10),

  ('0', FALSE, 11),
  ('OL', FALSE, 11),
  ('0.0d', TRUE, 11),
  ('null', FALSE, 11);

INSERT INTO questions (text, test_id) VALUES
  ('What does HTML stand for?', 3),
  ('Who is making the Web standards?', 3),
  ('Choose the correct HTML element for the largest heading:', 3),
  ('What is the correct HTML element for inserting a line break?', 3);

INSERT INTO answers (text, correct, question_id)
VALUES ('Home Tool Markup Language', FALSE, 12),
  ('Hyperlinks and Text Markup Language', FALSE, 12),
  ('Hyper Text Markup Language', TRUE, 12),
  ('Hyper Test Markup Language', FALSE, 12),

  ('Mozilla', FALSE, 13),
  ('Microsoft', FALSE, 13),
  ('The World Wide Web Consortium', TRUE, 13),
  ('Google', FALSE, 13),

  ('heading', FALSE, 14),
  ('h1', TRUE, 14),
  ('h6', FALSE, 14),
  ('head', FALSE, 14),

  ('br', TRUE, 15),
  ('break', FALSE, 15),
  ('lb', FALSE, 15),
  ('line', FALSE, 15);

INSERT INTO questions (text, test_id) VALUES
  ('What does SQL stand for?', 4),
  ('Which SQL statement is used to extract data from a database?', 4),
  ('Which SQL statement is used to update data in a database?', 4),
  ('Which SQL statement is used to delete data from a database?', 4),
  ('With SQL, how do you select a column named "FirstName" from a table named "Persons"?', 4),
  ('With SQL, how do you select all the columns from a table named "Persons"?', 4);

INSERT INTO answers (text, correct, question_id)
VALUES ('Strong Question Language', FALSE, 16),
  ('Structured Query Language', TRUE, 16),
  ('Structured Question Language', FALSE, 16),
  ('Super Query Language', FALSE, 16),

  ('GET', FALSE, 17),
  ('EXTRACT', FALSE, 17),
  ('OPEN', FALSE, 17),
  ('SELECT', TRUE, 17),

  ('UPDATE', TRUE, 18),
  ('SAVE AS', FALSE, 18),
  ('SAVE', FALSE, 18),
  ('MODIFY', FALSE, 18),

  ('REMOVE', FALSE, 19),
  ('DELETE', TRUE, 19),
  ('COLLAPSE', FALSE, 19),
  ('SELECT', FALSE, 19),

  ('EXTRACT FirstName FROM Persons', FALSE, 20),
  ('SELECT Persons.FirstName', FALSE, 20),
  ('SELECT FirstName FROM Persons', TRUE, 20),
  ('SELECT * FROM Persons', FALSE, 20),

  ('SELECT * FROM Persons', TRUE, 21),
  ('SELECT [all] FROM Persons', FALSE, 21),
  ('SELECT *.Persons', FALSE, 21),
  ('SELECT Persons', FALSE, 21);

INSERT INTO questions (text, test_id) VALUES
  ('What is true about MIME/type?', 5),
  ('Which HTTP method send by browser that gives the server what user data typed in the form?', 5),
  ('Which of these are MIME types?', 5),
  ('How to send data in get method?', 5),
  ('Which HTTP method send by browser that asks the server to get the page only?', 5),
  ('When we are sending data in URL in get method, how to separate parameters?', 5);

INSERT INTO answers (text, correct, question_id)
VALUES ('It tells the browser what type of data browser will send', FALSE, 22),
  ('It tells the browser what type of data browser will receive', TRUE, 22),
  ('It tells the server what type of data server will receive', FALSE, 22),
  ('It tells the server what type of data server will send', FALSE, 22),

  ('put', TRUE, 23),
  ('post', FALSE, 23),
  ('head', FALSE, 23),
  ('trace', FALSE, 23),

  ('application/java', FALSE, 24),
  ('image/bmp', FALSE, 24),
  ('text/html', FALSE, 24),
  ('all of the above', TRUE, 24),

  ('we cannot', FALSE, 25),
  ('through URL', TRUE, 25),
  ('through payload', FALSE, 25),
  ('non of these', FALSE, 25),

  ('get', TRUE, 26),
  ('post', FALSE, 26),
  ('option', FALSE, 26),
  ('put', FALSE, 26),

  ('by comma', TRUE, 27),
  ('by semicolon', FALSE, 27),
  ('by question mark', FALSE, 27),
  ('by ampersand', FALSE, 27);

INSERT INTO questions (text, test_id) VALUES
  ('Сколько различных звуков производят кошки?', 6),
  ('Сколько существует пород домашних кошек?', 6),
  ('Кто является самой большой дикой кошкой?', 6),
  ('Сколько примерно усов у кошки?', 6);


INSERT INTO answers (text, correct, question_id)
VALUES ('10', FALSE, 28),
  ('50', FALSE, 28),
  ('100', TRUE, 28),
  ('1', FALSE, 28),

  ('127', FALSE, 29),
  ('256', TRUE, 29),
  ('1к', FALSE, 29),
  ('2к', FALSE, 29),

  ('амурский тигр', TRUE, 30),
  ('пума', FALSE, 30),
  ('рысь', FALSE, 30),
  ('пантера', FALSE, 30),

  ('24', TRUE, 31),
  ('100', FALSE, 31),
  ('10', FALSE, 31),
  ('80', FALSE, 31);