
-- insert users table data
INSERT INTO users (user_id, username,password_hash,role) VALUES (1, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (user_id, username,password_hash,role) VALUES (3, 'admin', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
ALTER SEQUENCE seq_user_id RESTART WITH 3;

-- insert customer table data
INSERT INTO customer (customer_id, customer_name, customer_email) VALUES (1, 'Steve Rogers', srogers@shield.gov);
INSERT INTO customer (customer_id, customer_name, customer_email) VALUES (2, 'Jennifer Walters', jenwalt@gmail.com);
INSERT INTO customer (customer_id, customer_name, customer_email) VALUES (4, 'Tony Stark', tony@stark.com);
INSERT INTO customer (customer_id, customer_name, customer_email) VALUES (5, 'Carol Danvers', cdanvers@shield.gov);
INSERT INTO customer (customer_id, customer_name, customer_email) VALUES (6, 'Thor Odinson', thor@asgard.org);
INSERT INTO customer (customer_id, customer_name, customer_email) VALUES (7, 'Janet van Dyne', j.van.dyne@gmail.com);

-- insert employee table data
INSERT INTO employee (employee_id, employee_name, is_admin) VALUES (3, 'Nick Fury', true);