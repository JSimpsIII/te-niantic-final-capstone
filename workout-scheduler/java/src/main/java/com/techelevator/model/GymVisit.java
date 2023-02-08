package com.techelevator.model;

import java.sql.Time;
import java.util.Date;


public class GymVisit {

    private int visitId;
    private Long customerId;
    private Date visitDate;
    private Time checkIn;
    private Time checkOut;

    public GymVisit() {
    }

    public GymVisit(Long customerId, Date visitDate, Time checkIn) {
        this.customerId = customerId;
        this.checkIn = checkIn;
    }

    public GymVisit(int visitId, Long customerId, Date visitDate, Time checkIn, Time checkOut) {
        this.visitId = visitId;
        this.customerId = customerId;
        this.visitDate = visitDate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
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

    public Time getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Time checkIn) {
        this.checkIn = checkIn;
    }

    public Time getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Time checkOut) {
        this.checkOut = checkOut;
    }
}
