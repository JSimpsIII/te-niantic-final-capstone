package com.techelevator.dao;

import com.techelevator.model.profile.*;

import java.util.List;

public interface ProfileDao {

    List<Customer> getAllCustomers();

    Profile getProfileById(Long userId);

    Profile getProfileByUsername(String username);

    void createNewProfile(int id);

    void createNewProfile(String username);

    boolean createNewProfile(Profile profile);

    boolean addNewCustomer(Long userId, Customer customer);

    boolean addNewCustomer(String username);

    boolean updateCustomerById(Long userId, Customer customer);

//    boolean updateProfileById(Long userId, Profile profile);

    boolean updateProfileById(Long userId, String email, String photo, double height);

    List<Goal> getAllGoalsById(Long userId);

    List<Metric> getAllMetricsById(Long userId);

    boolean addNewGoal(Long userId, Goal goal);

    boolean addNewMetric(Long userId, Metric metric);
}
