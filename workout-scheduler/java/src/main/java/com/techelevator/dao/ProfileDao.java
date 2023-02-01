package com.techelevator.dao;

import com.techelevator.model.profile.*;

import java.util.List;

public interface ProfileDao {

    List<Customer> getAllCustomers();

    Profile getProfileById(Long userId);

    Profile getProfileByName(String name);

    boolean createNewProfile(Profile profile);

    boolean addNewCustomer(Long userId, Customer customer);

    boolean updateProfileById(Long userId, Profile profile);

    List<Goal> getAllGoalsById(Long userId);

    List<Metric> getAllMetricsById(Long userId);

    boolean addNewGoal(Long userId, Goal goal);

    boolean addNewMetric(Long userId, Metric metric);
}
