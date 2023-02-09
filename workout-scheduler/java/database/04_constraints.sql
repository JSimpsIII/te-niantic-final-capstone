-- add database constraints here - such as foreign key constraints
-- very common to add constraints AFTER bulk loading data


ALTER TABLE customer
ADD CONSTRAINT FK_customer_users
FOREIGN KEY (customer_id)
REFERENCES users(user_id);

ALTER TABLE exercise
ADD CONSTRAINT FK_exercise_exercise_equipment
FOREIGN KEY (equipment)
REFERENCES exercise_equipment(equipment_id);

ALTER TABLE exercise
ADD CONSTRAINT FK_exercise_exercise_target
FOREIGN KEY (exercise_target)
REFERENCES exercise_target(target_id);

ALTER TABLE exercise
ADD CONSTRAINT FK_exercise_exercise_bodypart
FOREIGN KEY (body_part)
REFERENCES exercise_bodypart(bodypart_id);

ALTER TABLE metrics
ADD CONSTRAINT FK_metrics_customer
FOREIGN KEY (customer_id)
REFERENCES customer(customer_id);

ALTER TABLE metrics
ADD CONSTRAINT FK_metrics_exercise
FOREIGN KEY (exercise_id)
REFERENCES exercise(exercise_id);

ALTER TABLE goal
ADD CONSTRAINT FK_goal_customer
FOREIGN KEY (customer_id)
REFERENCES customer(customer_id);

ALTER TABLE goal
ADD CONSTRAINT FK_goal_exercise
FOREIGN KEY (exercise_id)
REFERENCES exercise(exercise_id);

ALTER TABLE gym
ADD CONSTRAINT FK_gym_customer
FOREIGN KEY (customer_id)
REFERENCES customer(customer_id);



