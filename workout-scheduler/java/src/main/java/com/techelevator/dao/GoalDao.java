package com.techelevator.dao;

import com.techelevator.model.profile.Goal;

import java.util.List;

public interface GoalDao {

    List<Goal> getAllGoalsById(Long userId);

    boolean addNewGoal(Long userId, Goal goal);

    boolean updateGoal(Goal goal);

    boolean deleteGoal(Long userId, int goalId);

}
