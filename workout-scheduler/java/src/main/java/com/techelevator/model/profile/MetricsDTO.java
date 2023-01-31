package com.techelevator.model.profile;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class MetricsDTO {

    @NotEmpty
    private Long customerId;
    private Date date;
    private int reps;
    private double pounds;
    private double minutes;
    private int days;
    private String misc;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public double getPounds() {
        return pounds;
    }

    public void setPounds(double pounds) {
        this.pounds = pounds;
    }

    public double getMinutes() {
        return minutes;
    }

    public void setMinutes(double minutes) {
        this.minutes = minutes;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }
}
