package com.techelevator.model;

import java.util.Date;


public class GymVisit {

    private int visitId;
    private Long customerId;
    private Date checkIn;
    private Date checkOut;

    public GymVisit() {
    }

    public GymVisit(int visitId, Long customerId, Date checkIn, Date checkOut) {
        this.visitId = visitId;
        this.customerId = customerId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public GymVisit(Long customerId, Date checkIn) {
        this.customerId = customerId;
        this.checkIn = checkIn;
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

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

}
