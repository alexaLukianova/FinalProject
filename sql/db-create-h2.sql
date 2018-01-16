DROP TABLE IF EXISTS users, roles, tests, questions, answers;

CREATE TABLE roles (
  id   INT,
  name VARCHAR(45) UNIQUE NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE users (
  id         INT AUTO_INCREMENT,
  first_name VARCHAR(45),
  last_name  VARCHAR(45),
  username   VARCHAR(45) UNIQUE NOT NULL,
  password   VARCHAR(45)        NOT NULL,
  avatar     BLOB,
  role_id    INT,
  PRIMARY KEY (id),
  FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE tests (
  id         INT AUTO_INCREMENT,
  name       VARCHAR(45) NOT NULL,
  subject    VARCHAR(45) NOT NULL,
  complexity VARCHAR(45) NOT NULL,
  --   time INT NOT NULL,
  time       INT,
  PRIMARY KEY (id)
);

CREATE TABLE questions (
  id      INT AUTO_INCREMENT,
  text    VARCHAR(45) UNIQUE NOT NULL,
  test_id INT                NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (test_id) REFERENCES tests (id)
);

CREATE TABLE answers (
  id          INT AUTO_INCREMENT,
  text        VARCHAR(45) UNIQUE NOT NULL,
  correct     BOOLEAN            NOT NULL,
  question_id INT                NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (question_id) REFERENCES questions (id)
);

INSERT INTO roles VALUES (0, 'admin'), (1, 'student');

INSERT INTO users (username, password, role_id) VALUES ('admin', 'password', 0), ('student', 'password', 1);

INSERT INTO tests (name, subject, complexity) VALUES ('Бобры', 'Зоология', 'Легкая'), ('Еноты', 'Зоология', 'Высокая');





