package com.techelevator.model.dto;

import javax.validation.constraints.NotEmpty;
import java.sql.Time;
import java.util.Date;

public class GymVisitDTO {

    private int visitId;
    @NotEmpty
    private Long customerId;
    @NotEmpty
    private Date visitDate;
    @NotEmpty
    private Time checkIn;
    private Time checkOut;

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


