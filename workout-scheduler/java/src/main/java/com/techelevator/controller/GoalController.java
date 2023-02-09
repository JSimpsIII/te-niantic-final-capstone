package com.techelevator.controller;

import com.techelevator.dao.GoalDao;
import com.techelevator.model.profile.Goal;
import com.techelevator.model.profile.GoalDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin
public class GoalController {

    private GoalDao goalDao;

    public GoalController(GoalDao goalDao) {
        this.goalDao = goalDao;
    }

    @RequestMapping(path = "/users/{userId}/goals", method = RequestMethod.GET)
    public List<Goal> getAllGoals(@PathVariable Long userId) {
        return goalDao.getAllGoals(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/users/{userId}/goals", method = RequestMethod.POST)
    public boolean addNewGoal(@PathVariable Long userId, @RequestBody GoalDTO goalDTO) {
        String name = goalDTO.getName();
        Long customerId = goalDTO.getCustomerId();
        int exerciseId = goalDTO.getExerciseId();
        Date date = goalDTO.getDate();
        double reps = goalDTO.getReps();
        double weight = goalDTO.getWeight();
        double time = goalDTO.getTime();
        double sets = goalDTO.getSets();
        int days = goalDTO.getDays();
        String misc = goalDTO.getMisc();
        boolean isCompleted = goalDTO.isCompleted();
        Goal goal = new Goal(name, customerId, exerciseId, date, reps, weight, time, sets, days, misc, isCompleted);
        return goalDao.addNewGoal(userId, goal);
    }

    @RequestMapping(path = "/users/{userId}/goals/{goalId}", method = RequestMethod.PUT)
    public boolean updateGoal(@PathVariable Long userId, @PathVariable int goalId, @RequestBody GoalDTO goalDTO) {
        String name = goalDTO.getName();
        Long customerId = goalDTO.getCustomerId();
        int exerciseId = goalDTO.getExerciseId();
        Date date = goalDTO.getDate();
        double reps = goalDTO.getReps();
        double weight = goalDTO.getWeight();
        double time = goalDTO.getTime();
        double sets = goalDTO.getSets();
        int days = goalDTO.getDays();
        String misc = goalDTO.getMisc();
        boolean isCompleted = goalDTO.isCompleted();
        Goal goal = new Goal(goalId, name, customerId, exerciseId, date, reps, weight, time, sets, days, misc, isCompleted);
        return goalDao.updateGoal(userId, goalId, goal);
    }

    @RequestMapping(path = "/users/{userId}/goals/{goalId}", method = RequestMethod.DELETE)
    public boolean deleteGoal(@PathVariable Long userId, @PathVariable int goalId) {
        return goalDao.deleteGoal(userId, goalId);
    }
}
