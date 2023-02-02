package com.techelevator.service.ExerciseMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techelevator.model.Exercise;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileWriter {


    private static File exercises = new File("C:\\Users\\Student\\workspace\\capstone-workout-companion\\exercises-with-select.sql");
    public static void createExerciseFile() {
        try {
            try {
                ExerciseMapService exerciseMapService = new ExerciseMapService();
                try (PrintWriter writer = new PrintWriter(exercises)) {

                    List<Exercise> list1 = exerciseMapService.getExerciseList1();
                    List<Exercise> list2 = exerciseMapService.getExerciseList2();
                    List<Exercise> list3 = exerciseMapService.getExerciseList3();
                    List<Exercise> list4 = exerciseMapService.getExerciseList4();
                    List<Exercise> list5 = exerciseMapService.getExerciseList5();
                    List<Exercise> list6 = exerciseMapService.getExerciseList6();
                    List<Exercise> list7 = exerciseMapService.getExerciseList7();
                    List<Exercise> list8 = exerciseMapService.getExerciseList8();
                    List<Exercise> list9 = exerciseMapService.getExerciseList9();
                    List<Exercise> list10 = exerciseMapService.getExerciseList10();
                    List<Exercise> list11 = exerciseMapService.getExerciseList11();
                    List<Exercise> list12 = exerciseMapService.getExerciseList12();
                    List<Exercise> list13 = exerciseMapService.getExerciseList13();
                    List<Exercise> list14 = exerciseMapService.getExerciseList14();
                    List<Exercise> list15 = exerciseMapService.getExerciseList15();
                    List<Exercise> list16 = exerciseMapService.getExerciseList16();
                    List<Exercise> list17 = exerciseMapService.getExerciseList17();

                    writeList(list1, writer);
                    writeList(list2, writer);
                    writeList(list3, writer);
                    writeList(list4, writer);
                    writeList(list5, writer);
                    writeList(list6, writer);
                    writeList(list7, writer);
                    writeList(list8, writer);
                    writeList(list9, writer);
                    writeList(list10, writer);
                    writeList(list11, writer);
                    writeList(list12, writer);
                    writeList(list13, writer);
                    writeList(list14, writer);
                    writeList(list15, writer);
                    writeList(list16, writer);
                    writeList(list17, writer);

                }catch (IOException exception) {
                    exception.printStackTrace();
                }
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void writeList(List<Exercise> list, PrintWriter writer) {
        for (Exercise exercise : list) {
            String bodyPart = exercise.getBodyPart();
            String equipment = exercise.getEquipment();
            String gifUrl = exercise.getGifUrl();
            String id = String.valueOf(exercise.getId());
            String name = exercise.getName();
            String target = exercise.getTarget();
            writer.println("INSERT INTO exercise (exercise_id, exercise_name, exercise_target, body_part, equipment, gif_url) VALUES (" + id + ", '" + name + "', (SELECT target_id FROM exercise_target WHERE target_name = '" + target + "'), (SELECT bodypart_id FROM exercise_bodypart WHERE bodypart_name = '" + bodyPart + "'), (SELECT equipment_id FROM exercise_equipment WHERE equipment_name = '" + equipment + "'), '" + gifUrl + "');");
        }
    }
}


