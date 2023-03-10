package com.techelevator.dao;

import com.techelevator.model.Exercise;

import java.util.List;

public interface ExerciseDao {

    List<Exercise> getAllExercises();

    Exercise getExerciseById(int id);
}
