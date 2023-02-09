package com.techelevator.dao;

import com.techelevator.model.GymVisit;

import java.util.List;

public interface GymVisitDao {

    List<GymVisit> getAllVisits(Long customerId);

    int checkIn(Long userId);

    int checkOut(Long userId, int visitId);

    boolean deleteVisit(Long userId, int visitId);
}
