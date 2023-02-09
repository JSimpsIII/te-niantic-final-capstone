package com.techelevator.model;


import java.util.Date;


public class GymVisit {

    private int visitId;
    private Long customerId;
    private Date visitDate;
    private double minutes;

    public GymVisit() {
    }

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public double getMinutes() {
        return minutes;
    }

    public void setMinutes(double minutes) {
        this.minutes = minutes;
    }
}
