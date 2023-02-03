package com.techelevator.model.profile;

import java.util.ArrayList;
import java.util.List;

public class Profile {

    private Long userId;
    private Customer customer = new Customer();
    private List<Goal> goals = new ArrayList<>();
    private List<Metric> metrics = new ArrayList<>();

    public Profile() {
    }

    public Profile(Long userId, Customer customer, List<Goal> goals, List<Metric> metrics) {
        this.userId = userId;
        this.customer = customer;
        this.goals = goals;
        this.metrics = metrics;
        this.userId = customer.getCustomerId();
    }

    public Profile(Long userId) {
        this.userId = userId;
    }

    public Profile(Long userId, Customer customer, List<Metric> metrics) {
        this.userId = userId;
        this.customer = customer;
        this.metrics = metrics;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public List<Metric> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<Metric> metrics) {
        this.metrics = metrics;
    }
}
