package com.techelevator.dao;

import com.techelevator.model.Exercise;

import java.util.List;

public interface ExerciseDao {

    List<Exercise> getAllExercises();

    List<Exercise> getAllExercisesByEquipment(String equipment);

    List<Exercise> getAllExercisesByTarget(String target);

    List<Exercise> getAllExercisesByBodypart(String bodypart);

    Exercise getExerciseByName(String name);

    Exercise getExerciseById(int id);
}
