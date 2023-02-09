DROP TABLE IF EXISTS gym, customer_goal, goal, metrics, customer, users, exercise, exercise_equipment, exercise_target, exercise_bodypart CASCADE;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP SEQUENCE IF EXISTS seq_customer_id;
DROP SEQUENCE IF EXISTS seq_goal_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_customer_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_goal_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE customer (
	customer_id int DEFAULT nextval('seq_customer_id'::regclass) NOT NULL,
	customer_username varchar(50) NOT NULL UNIQUE,
	customer_name varchar(50),
	customer_email varchar(50),
	photo_link varchar(400),
	height_inches numeric,
	CONSTRAINT PK_customer PRIMARY KEY (customer_id)
);

CREATE TABLE exercise (
    exercise_id int NOT NULL PRIMARY KEY,
    exercise_name varchar(200),
    exercise_target int,
    body_part int,
    equipment int,
    gif_url varchar(200)
);

CREATE TABLE exercise_equipment (
    equipment_id SERIAL NOT NULL PRIMARY KEY,
    equipment_name varchar(50) NOT NULL
);

CREATE TABLE exercise_target (
    target_id SERIAL NOT NULL PRIMARY KEY,
    target_name varchar(50) NOT NULL
);

CREATE TABLE exercise_bodypart (
    bodypart_id SERIAL NOT NULL PRIMARY KEY,
    bodypart_name varchar(50) NOT NULL
);

CREATE TABLE metrics (
	metrics_id SERIAL NOT NULL PRIMARY KEY,
	customer_id int NOT NULL,
	exercise_id int,
	metrics_date date NOT NULL,
	current_reps numeric,
	current_weight_lbs numeric,
	current_time_min numeric,
	current_sets numeric,
	current_days int,
	current_misc varchar(50)
);

CREATE TABLE goal (
	goal_id int DEFAULT nextval('seq_goal_id'::regclass) NOT NULL,
	goal_name varchar(50) NOT NULL,
	customer_id int NOT NULL,
	exercise_id int,
	goal_date date,
	goal_reps numeric,
	goal_weight_lbs numeric,
	goal_time_min numeric,
	goal_sets numeric,
	goal_days int,
	goal_misc varchar(50),
	is_completed boolean
);

CREATE TABLE gym (
    visit_id SERIAL NOT NULL PRIMARY KEY,
    customer_id int NOT NULL,
    visit_date date NOT NULL,
    visit_time_min numeric
);






-- Add seed data in the 03_data.sql script file

-- Add Foreign key constraints in the 04_constraints.sql script file
