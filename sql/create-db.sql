DROP TABLE IF EXISTS users, tests, questions, answers;

CREATE TABLE users (
  id        INT AUTO_INCREMENT,
  firstName VARCHAR(45),
  lastName  VARCHAR(45),
  username  VARCHAR(45) UNIQUE NOT NULL,
  password  VARCHAR(45)        NOT NULL,
  avatar    BLOB,
  PRIMARY KEY (id)
);

CREATE TABLE roles (
  id       INT AUTO_INCREMENT,
  rolename VARCHAR(45) UNIQUE NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE users_roles (
  username VARCHAR(45) NOT NULL,
  rolename VARCHAR(45) NOT NULL,
  FOREIGN KEY (username) REFERENCES users (username),
  FOREIGN KEY (rolename) REFERENCES roles (rolename)
);

CREATE TABLE tests (
  id         INT AUTO_INCREMENT,
  name       VARCHAR(45) NOT NULL,
  subject    VARCHAR(45) NOT NULL,
  complexity VARCHAR(45) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE questions (
  id   INT AUTO_INCREMENT,
  text VARCHAR(45) UNIQUE NOT NULL,
  test_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (test_id) REFERENCES tests(id)
);

CREATE TABLE answers (
  id   INT AUTO_INCREMENT,
  text VARCHAR(45) UNIQUE NOT NULL,
  correct BOOLEAN NOT NULL ,
  question_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (question_id) REFERENCES questions(id)
);

INSERT INTO users (username, PASSWORD) VALUES ('admin', 'password'), ('student', 'password');

INSERT INTO roles (rolename) VALUES ('ADMIN'), ('STUDENT');

INSERT INTO users_roles VALUES ('admin', 'ADMIN'), ('student', 'STUDENT');

INSERT INTO tests(name,subject,complexity) VALUES ('Бобры', 'Зоология', 'Легкая'), ('Еноты', 'Зоология', 'Высокая');





