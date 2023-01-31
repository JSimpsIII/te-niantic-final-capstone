package com.techelevator.model.profile;

import com.techelevator.model.profile.Customer;
import com.techelevator.model.profile.Goal;
import com.techelevator.model.profile.Metrics;

public class Profile {

    private Long userId;
    private Customer customer = new Customer();
    private Goal goal = new Goal();
    private Metrics metrics = new Metrics();

    public Profile() {
    }

    public Profile(Customer customer, Goal goal, Metrics metrics){
        this.customer = customer;
        this.goal = goal;
        this.metrics = metrics;
        this.userId = customer.getCustomerId();
    }

    public Profile(Long userId) {
        this.userId = userId;
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

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void setMetrics(Metrics metrics) {
        this.metrics = metrics;
    }
}
