DROP TABLE IF EXISTS customer_goal, goal, metrics, employee, customer, users CASCADE;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE customer (
	customer_id int NOT NULL,
	customer_name varchar(50) NOT NULL,
	customer_email varchar(50) NOT NULL,
	photo_link varchar(400),
	height_inches numeric,
	CONSTRAINT PK_customer PRIMARY KEY (customer_id)
);

CREATE TABLE employee (
	employee_id int NOT NULL,
	employee_name varchar(50) NOT NULL,
	is_admin boolean NOT NULL,
	CONSTRAINT PK_employee PRIMARY KEY (employee_id)
);

CREATE TABLE metrics (
	metrics_id SERIAL NOT NULL PRIMARY KEY,
	customer_id int NOT NULL,
	metrics_date date NOT NULL,
	weight_lbs numeric,
	current_reps int,
	current_weight int,
	current_time_min numeric,
	current_days int,
	current_misc varchar(50)
);

CREATE TABLE goal (
	goal_id SERIAL NOT NULL PRIMARY KEY,
	goal_name varchar(50) NOT NULL,
	goal_reps int,
	goal_weight_lbs int,
	goal_time_min numeric,
	goal_days int,
	goal_misc varchar(50)
);

CREATE TABLE customer_goal (
	customer_id int NOT NULL,
	goal_id int NOT NULL,
	goal_date date,
	is_completed boolean
);

-- Add seed data in the 03_data.sql script file

-- Add Foreign key constraints in the 04_constraints.sql script file
