DROP TABLE IF EXISTS equipment, target, bodypart;

CREATE TABLE equipment (
    equipment_id SERIAL NOT NULL PRIMARY KEY,
    equipment_name varchar(50) NOT NULL
)

CREATE TABLE bodytarget (
    bodytarget_id SERIAL NOT NULL PRIMARY KEY,
    bodytarget_name varchar(50) NOT NULL
)

CREATE TABLE bodypart (
    bodypart_id SERIAL NOT NULL PRIMARY KEY,
    bodypart_name varchar(50) NOT NULL
)

INSERT INTO equipment (equipment_name) VALUES ('assisted');
INSERT INTO equipment (equipment_name) VALUES ('band');
INSERT INTO equipment (equipment_name) VALUES ('barbell');
INSERT INTO equipment (equipment_name) VALUES ('body weight');
INSERT INTO equipment (equipment_name) VALUES ('bosu ball');
INSERT INTO equipment (equipment_name) VALUES ('cable');
INSERT INTO equipment (equipment_name) VALUES ('dumbbell');
INSERT INTO equipment (equipment_name) VALUES ('elliptical machine');
INSERT INTO equipment (equipment_name) VALUES ('ez barbell');
INSERT INTO equipment (equipment_name) VALUES ('hammer');
INSERT INTO equipment (equipment_name) VALUES ('kettlebell');
INSERT INTO equipment (equipment_name) VALUES ('leverage machine');
INSERT INTO equipment (equipment_name) VALUES ('medicine ball');
INSERT INTO equipment (equipment_name) VALUES ('olympic barbell');
INSERT INTO equipment (equipment_name) VALUES ('resistance band');
INSERT INTO equipment (equipment_name) VALUES ('roller');
INSERT INTO equipment (equipment_name) VALUES ('rope');
INSERT INTO equipment (equipment_name) VALUES ('skierg machine');
INSERT INTO equipment (equipment_name) VALUES ('sled machine');
INSERT INTO equipment (equipment_name) VALUES ('smith machine');
INSERT INTO equipment (equipment_name) VALUES ('stability ball');
INSERT INTO equipment (equipment_name) VALUES ('stationary bike');
INSERT INTO equipment (equipment_name) VALUES ('stepmill machine');
INSERT INTO equipment (equipment_name) VALUES ('tire');
INSERT INTO equipment (equipment_name) VALUES ('trap bar');
INSERT INTO equipment (equipment_name) VALUES ('upper body ergometer');
INSERT INTO equipment (equipment_name) VALUES ('weighted');
INSERT INTO equipment (equipment_name) VALUES ('wheel roller');

INSERT INTO bodytarget (bodytarget_name) VALUES ('abductors');
INSERT INTO bodytarget (bodytarget_name) VALUES ('abs');
INSERT INTO bodytarget (bodytarget_name) VALUES ('adductors');
INSERT INTO bodytarget (bodytarget_name) VALUES ('biceps');
INSERT INTO bodytarget (bodytarget_name) VALUES ('calves');
INSERT INTO bodytarget (bodytarget_name) VALUES ('cardiovascular system');
INSERT INTO bodytarget (bodytarget_name) VALUES ('delts');
INSERT INTO bodytarget (bodytarget_name) VALUES ('forearms');
INSERT INTO bodytarget (bodytarget_name) VALUES ('glutes');
INSERT INTO bodytarget (bodytarget_name) VALUES ('hamstrings');
INSERT INTO bodytarget (bodytarget_name) VALUES ('lats');
INSERT INTO bodytarget (bodytarget_name) VALUES ('levator scapulae');
INSERT INTO bodytarget (bodytarget_name) VALUES ('pectorals');
INSERT INTO bodytarget (bodytarget_name) VALUES ('quads');
INSERT INTO bodytarget (bodytarget_name) VALUES ('serratus anterior');
INSERT INTO bodytarget (bodytarget_name) VALUES ('spine');
INSERT INTO bodytarget (bodytarget_name) VALUES ('traps');
INSERT INTO bodytarget (bodytarget_name) VALUES ('triceps');
INSERT INTO bodytarget (bodytarget_name) VALUES ('upper back');

INSERT INTO bodypart (bodypart_name) VALUES ('back'),
INSERT INTO bodypart (bodypart_name) VALUES ('cardio'),
INSERT INTO bodypart (bodypart_name) VALUES ('chest'),
INSERT INTO bodypart (bodypart_name) VALUES ('lower arms'),
INSERT INTO bodypart (bodypart_name) VALUES ('lower legs'),
INSERT INTO bodypart (bodypart_name) VALUES ('neck'),
INSERT INTO bodypart (bodypart_name) VALUES ('shoulders'),
INSERT INTO bodypart (bodypart_name) VALUES ('upper arms'),
INSERT INTO bodypart (bodypart_name) VALUES ('upper legs'),
INSERT INTO bodypart (bodypart_name) VALUES ('waist'),