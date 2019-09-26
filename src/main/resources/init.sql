--PostgreSQL 9.6
--'\\' is a delimiter
SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;
SET default_tablespace = '';
SET default_with_oids = false;
SET search_path TO public;

SET TIME ZONE 'GMT';

DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS schedules CASCADE;
DROP TABLE IF EXISTS tasks CASCADE;
DROP TABLE IF EXISTS scheduleTasks CASCADE;



CREATE TABLE users(
  user_id smallserial NOT NULL,
  name varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  user_role varchar(20) NOT NULL,
  password varchar(50) NOT NULL,
  PRIMARY KEY(user_id)
);

CREATE TABLE tasks(
          task_id smallserial NOT NULL,
          user_id smallint  REFERENCES users(user_id) ON DELETE CASCADE,
          task_title varchar(255) NOT NULL,
          content text NOT NULL,
          PRIMARY KEY (task_id)
);

CREATE TABLE schedules(
          schedule_id smallserial NOT NULL,
          user_id smallint REFERENCES users(user_id) ON DELETE CASCADE,
          schedule_title varchar(255) NOT NULL,
          day_number smallint NOT NULL,
          is_published boolean DEFAULT false NOT NULL,
          PRIMARY KEY (schedule_id)
);

CREATE TABLE scheduleTasks(
          sch_task_id smallserial NOT NULL,
          task_id smallint NOT NULL REFERENCES tasks (task_id),
          schedule_id smallint NOT NULL REFERENCES schedules(schedule_id) ON DELETE CASCADE,
          start smallint NOT NULL,
          length smallint NOT NULL,
          PRIMARY KEY(sch_task_id)
);

INSERT INTO users(name, email, user_role, password) VALUES ('Rory', 'rory@gmail.com', 'mentor', 'scm2000?1');
INSERT INTO users(name, email, user_role, password) VALUES ('Charlie', 'charlie@gmail.com', 'student', 'scm2000?2');
INSERT INTO users(name, email, user_role, password) VALUES ('Tamy', 'tamy@gmail.com', 'mentor', 'scm2000?3');
INSERT INTO users(name, email, user_role, password) VALUES ('Andi', 'andi@gmail.com', 'student', 'scm2000?4');

INSERT INTO tasks(user_id,task_title,content) VALUES (1, 'Gardening', 'I really love gardening!');
INSERT INTO tasks(user_id,task_title,content) VALUES (1, 'Cooking', 'I really love cooking!');
INSERT INTO tasks(user_id,task_title,content) VALUES (2, 'Travelling', 'I really love travelling!');
INSERT INTO tasks(user_id,task_title,content) VALUES (1, 'Baking', 'I really love baking!');

INSERT INTO schedules(user_id, schedule_title, day_number, is_published) VALUES (1, 'Weekly routine',7, false);
INSERT INTO schedules(user_id, schedule_title, day_number, is_published) VALUES (1, 'Class schedule',4, true);
INSERT INTO schedules(user_id, schedule_title, day_number, is_published) VALUES (1, 'Time table',2, false);

