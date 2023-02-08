package com.techelevator.model.dto;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class GymVisitDTO {

    @NotEmpty
    private int visitId;
    @NotEmpty
    private Long customerId;
    @NotEmpty
    private Date checkIn;
    private Date checkOut;

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
