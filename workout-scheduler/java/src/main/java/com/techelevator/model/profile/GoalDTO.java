package com.techelevator.model.profile;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class GoalDTO {

    @NotEmpty
    private int id;
    private String name;
    private Long customerId;
    private int exerciseId;
    private Date date;
    private double reps;
    private double weight; //lbs
    private double time; //min
    private double sets;
    private int days;
    private String misc;
    private boolean isCompleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
