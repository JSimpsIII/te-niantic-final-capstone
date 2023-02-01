package com.techelevator.model.profile;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class ProfileDTO {

    @NotEmpty
    private Customer customer;
    @NotEmpty
    private List<Metric> metrics;
    @NotEmpty
    private List<Goal> goals;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Metric> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<Metric> metrics) {
        this.metrics = metrics;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoal(List<Goal> goals) {
        this.goals = goals;
    }
}
