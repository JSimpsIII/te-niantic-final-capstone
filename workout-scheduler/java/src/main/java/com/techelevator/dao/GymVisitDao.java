package com.techelevator.dao;

import com.techelevator.model.GymVisit;

import java.util.List;

public interface GymVisitDao {

    List<GymVisit> getAllVisits(Long customerId);

    int newVisit(Long userId, GymVisit visit);

    boolean updateVisit(Long userId, int visitId, GymVisit visit);

    boolean deleteVisit(Long userId, int visitId);
}
