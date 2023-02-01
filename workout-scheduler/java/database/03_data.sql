
-- insert users table data
INSERT INTO users (user_id, username,password_hash,role) VALUES (1, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (user_id, username,password_hash,role) VALUES (2, 'jennifer', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (user_id, username,password_hash,role) VALUES (3, 'admin', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (user_id, username,password_hash,role) VALUES (4, 'tony', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (user_id, username,password_hash,role) VALUES (5, 'cdanvers', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (user_id, username,password_hash,role) VALUES (6, 'mjolnir', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (user_id, username,password_hash,role) VALUES (7, 'jvandyne', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (user_id, username,password_hash,role) VALUES (8, 'nat', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (user_id, username,password_hash,role) VALUES (9, 'clint', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
ALTER SEQUENCE seq_user_id RESTART WITH 10;

-- insert customer table data
INSERT INTO customer (customer_id, customer_name, customer_email) VALUES (1, 'Steve Rogers', 'srogers@shield.gov');
INSERT INTO customer (customer_id, customer_name, customer_email) VALUES (2, 'Jennifer Walters', 'jenwalt@gmail.com');
INSERT INTO customer (customer_id, customer_name, customer_email) VALUES (4, 'Tony Stark', 'tony@stark.com');
INSERT INTO customer (customer_id, customer_name, customer_email) VALUES (5, 'Carol Danvers', 'cdanvers@shield.gov');
INSERT INTO customer (customer_id, customer_name, customer_email) VALUES (6, 'Thor Odinson', 'thor@asgard.org');
INSERT INTO customer (customer_id, customer_name, customer_email) VALUES (7, 'Janet van Dyne', 'j.van.dyne@gmail.com');

-- insert employee table data
INSERT INTO employee (employee_id, employee_name, is_admin) VALUES (3, 'Nick Fury', true);
INSERT INTO employee (employee_id, employee_name, is_admin) VALUES (8, 'Natasha Romanoff', false);
INSERT INTO employee (employee_id, employee_name, is_admin) VALUES (9, 'Clint Barton', false);

-- insert metrics table data
INSERT INTO metrics (customer_id, metrics_date, current_weight_lbs, current_misc) VALUES (1, DATE '2020-01-01', 610, 'squat');
INSERT INTO metrics (customer_id, metrics_date, current_weight_lbs, current_misc) VALUES (1, DATE '2020-06-22', 625, 'squat');
INSERT INTO metrics (customer_id, metrics_date, current_weight_lbs, current_misc) VALUES (1, DATE '2020-11-01', 650, 'squat');
INSERT INTO metrics (customer_id, metrics_date, current_weight_lbs, current_misc) VALUES (1, DATE '2021-04-23', 670, 'squat');
INSERT INTO metrics (customer_id, metrics_date, current_weight_lbs, current_misc) VALUES (1, DATE '2022-07-15', 695, 'squat');
INSERT INTO metrics (customer_id, metrics_date, current_weight_lbs, current_misc) VALUES (1, DATE '2023-01-03', 710, 'squat');
INSERT INTO metrics (customer_id, metrics_date, current_time_min, current_misc) VALUES (2, DATE '2022-12-01', 7.2, 'mile run');
INSERT INTO metrics (customer_id, metrics_date, current_weight_lbs, current_days) VALUES (4, DATE '2021-01-01', 255, 3);
INSERT INTO metrics (customer_id, metrics_date, current_weight_lbs, current_days) VALUES (4, DATE '2021-08-01', 254, 4);
INSERT INTO metrics (customer_id, metrics_date, current_weight_lbs, current_days) VALUES (4, DATE '2021-12-01', 256, 5);

-- insert goal table data
INSERT INTO goal (goal_id, goal_name, goal_weight_lbs) VALUES (1, 'squat', '750');
INSERT INTO goal (goal_id, goal_name, goal_time_min) VALUES (2, 'mile run', 6.0);
INSERT INTO goal (goal_id, goal_name, goal_days) VALUES (3, 'days in gym', 5);

-- insert customer_goal table data
INSERT INTO customer_goal (customer_id, goal_id, goal_date, is_completed) VALUES (1, 1, DATE '2023-12-01', false);
INSERT INTO customer_goal (customer_id, goal_id, goal_date, is_completed) VALUES (2, 2, DATE '2023-05-20', false);
INSERT INTO customer_goal (customer_id, goal_id, goal_date, is_completed) VALUES (4, 3, DATE '2022-01-01', true);
