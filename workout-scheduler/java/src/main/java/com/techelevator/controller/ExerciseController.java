package com.techelevator.controller;

import com.techelevator.dao.ExerciseDao;
import com.techelevator.model.Exercise;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class ExerciseController {

    private ExerciseDao exerciseDao;
    public ExerciseController(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    @RequestMapping(path = "/exercises", method = RequestMethod.GET)
    public List<Exercise> getAllExercises() {
        return exerciseDao.getAllExercises();
    }
    
    @RequestMapping(path = "/exercises/{id}", method = RequestMethod.GET)
    public Exercise getExerciseById(@PathVariable int id) {
        return exerciseDao.getExerciseById(id);
    }

}
