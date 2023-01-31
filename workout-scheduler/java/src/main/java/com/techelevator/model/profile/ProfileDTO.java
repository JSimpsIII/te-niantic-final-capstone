package com.techelevator.model.profile;

import javax.validation.constraints.NotEmpty;

public class ProfileDTO {

    @NotEmpty
    private Customer customer;
    @NotEmpty
    private Metrics metrics;
    @NotEmpty
    private Goal goal;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void setMetrics(Metrics metrics) {
        this.metrics = metrics;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }
}
