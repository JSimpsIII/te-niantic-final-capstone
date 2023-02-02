DROP TABLE IF EXISTS equipment, target, bodypart;

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

INSERT INTO exercise_equipment (equipment_name) VALUES ('assisted');
INSERT INTO exercise_equipment (equipment_name) VALUES ('band');
INSERT INTO exercise_equipment (equipment_name) VALUES ('barbell');
INSERT INTO exercise_equipment (equipment_name) VALUES ('body weight');
INSERT INTO exercise_equipment (equipment_name) VALUES ('bosu ball');
INSERT INTO exercise_equipment (equipment_name) VALUES ('cable');
INSERT INTO exercise_equipment (equipment_name) VALUES ('dumbbell');
INSERT INTO exercise_equipment (equipment_name) VALUES ('elliptical machine');
INSERT INTO exercise_equipment (equipment_name) VALUES ('ez barbell');
INSERT INTO exercise_equipment (equipment_name) VALUES ('hammer');
INSERT INTO exercise_equipment (equipment_name) VALUES ('kettlebell');
INSERT INTO exercise_equipment (equipment_name) VALUES ('leverage machine');
INSERT INTO exercise_equipment (equipment_name) VALUES ('medicine ball');
INSERT INTO exercise_equipment (equipment_name) VALUES ('olympic barbell');
INSERT INTO exercise_equipment (equipment_name) VALUES ('resistance band');
INSERT INTO exercise_equipment (equipment_name) VALUES ('roller');
INSERT INTO exercise_equipment (equipment_name) VALUES ('rope');
INSERT INTO exercise_equipment (equipment_name) VALUES ('skierg machine');
INSERT INTO exercise_equipment (equipment_name) VALUES ('sled machine');
INSERT INTO exercise_equipment (equipment_name) VALUES ('smith machine');
INSERT INTO exercise_equipment (equipment_name) VALUES ('stability ball');
INSERT INTO exercise_equipment (equipment_name) VALUES ('stationary bike');
INSERT INTO exercise_equipment (equipment_name) VALUES ('stepmill machine');
INSERT INTO exercise_equipment (equipment_name) VALUES ('tire');
INSERT INTO exercise_equipment (equipment_name) VALUES ('trap bar');
INSERT INTO exercise_equipment (equipment_name) VALUES ('upper body ergometer');
INSERT INTO exercise_equipment (equipment_name) VALUES ('weighted');
INSERT INTO exercise_equipment (equipment_name) VALUES ('wheel roller');

INSERT INTO exercise_target (target_name) VALUES ('abductors');
INSERT INTO exercise_target (target_name) VALUES ('abs');
INSERT INTO exercise_target (target_name) VALUES ('adductors');
INSERT INTO exercise_target (target_name) VALUES ('biceps');
INSERT INTO exercise_target (target_name) VALUES ('calves');
INSERT INTO exercise_target (target_name) VALUES ('cardiovascular system');
INSERT INTO exercise_target (target_name) VALUES ('delts');
INSERT INTO exercise_target (target_name) VALUES ('forearms');
INSERT INTO exercise_target (target_name) VALUES ('glutes');
INSERT INTO exercise_target (target_name) VALUES ('hamstrings');
INSERT INTO exercise_target (target_name) VALUES ('lats');
INSERT INTO exercise_target (target_name) VALUES ('levator scapulae');
INSERT INTO exercise_target (target_name) VALUES ('pectorals');
INSERT INTO exercise_target (target_name) VALUES ('quads');
INSERT INTO exercise_target (target_name) VALUES ('serratus anterior');
INSERT INTO exercise_target (target_name) VALUES ('spine');
INSERT INTO exercise_target (target_name) VALUES ('traps');
INSERT INTO exercise_target (target_name) VALUES ('triceps');
INSERT INTO exercise_target (target_name) VALUES ('upper back');

INSERT INTO exercise_bodypart (bodypart_name) VALUES ('back'),
INSERT INTO exercise_bodypart (bodypart_name) VALUES ('cardio'),
INSERT INTO exercise_bodypart (bodypart_name) VALUES ('chest'),
INSERT INTO exercise_bodypart (bodypart_name) VALUES ('lower arms'),
INSERT INTO exercise_bodypart (bodypart_name) VALUES ('lower legs'),
INSERT INTO exercise_bodypart (bodypart_name) VALUES ('neck'),
INSERT INTO exercise_bodypart (bodypart_name) VALUES ('shoulders'),
INSERT INTO exercise_bodypart (bodypart_name) VALUES ('upper arms'),
INSERT INTO exercise_bodypart (bodypart_name) VALUES ('upper legs'),
INSERT INTO exercise_bodypart (bodypart_name) VALUES ('waist'),
