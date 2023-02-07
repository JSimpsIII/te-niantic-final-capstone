package com.techelevator.dao;

import com.techelevator.model.profile.Goal;

import java.util.List;

public interface GoalDao {

    List<Goal> getAllGoals(Long userId);

    boolean addNewGoal(Long userId, Goal goal);

    boolean updateGoal(Long userId, int goalId, Goal goal);

    boolean deleteGoal(Long userId, int goalId);

}
