-- add database constraints here - such as foreign key constraints
-- very common to add constraints AFTER bulk loading data

ALTER TABLE customer
ADD CONSTRAINT FK_customer_user
FOREIGN KEY (user_id)
REFERENCES users(user_id);

ALTER TABLE employee
ADD CONSTRAINT FK_employee_user
FOREIGN KEY (employee_id)
REFERENCES users(user_id);

ALTER TABLE metics
ADD CONSTRAINT FK_metrics_customer
FOREIGN KEY (customer_id)
REFERENCES customer(customer_id);

ALTER TABLE customer_goal
ADD CONSTRAINT FK_customer_goal_customer
FOREIGN KEY (customer_id)
REFERENCES customer(customer_id);

ALTER TABLE customer_goal
ADD CONSTRAINT FK customer_goal_goal
FOREIGN KEY (goal_id)
REFERENCES goal(goal_id);