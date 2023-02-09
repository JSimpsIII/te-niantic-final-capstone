package com.techelevator.model.profile;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class MetricDTO {

    @NotEmpty
    private int id;
    @NotEmpty
    private Long customerId;
    private int exerciseId;
    private Date date;
    private double reps;
    private double weight;
    private double time;
    private double sets;
    private int days;
    private String misc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getReps() {
        return reps;
    }

    public void setReps(double reps) {
        this.reps = reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getSets() {
        return sets;
    }

    public void setSets(double sets) {
        this.sets = sets;
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
