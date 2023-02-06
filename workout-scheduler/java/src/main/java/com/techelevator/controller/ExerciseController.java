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

//    @RequestMapping(path = "/exercises/{equipment}", method = RequestMethod.GET)
//    public List<Exercise> getAllExercisesByEquipment(@RequestParam String equipment) {
//        return exerciseDao.getAllExercisesByEquipment(equipment);
//    }
//
//    @RequestMapping(path = "/exercises/{target}", method = RequestMethod.GET)
//    public List<Exercise> getAllExercisesByTarget(@RequestParam String target) {
//        return exerciseDao.getAllExercisesByTarget(target);
//    }
//
//    @RequestMapping(path = "/exercises/{bodypart}", method = RequestMethod.GET)
//    public List<Exercise> getAllExercisesByBodypart(@RequestParam String bodypart) {
//        return exerciseDao.getAllExercisesByBodypart(bodypart);
//    }
//
//    @RequestMapping(path = "/exercises/{name}", method = RequestMethod.GET)
//    public Exercise getExerciseByName(@RequestParam String name) {
//        return exerciseDao.getExerciseByName(name);
//    }

    @RequestMapping(path = "/exercises/{id}", method = RequestMethod.GET)
    public Exercise getExerciseById(@PathVariable int id) {
        return exerciseDao.getExerciseById(id);
    }

}
