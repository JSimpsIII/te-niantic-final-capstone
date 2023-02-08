package com.techelevator.dao;

import com.techelevator.model.GymVisit;

import java.util.List;

public interface GymVisitDao {

    List<GymVisit> getAllVisits(Long customerId);

    boolean newVisit(Long userId, GymVisit gymVisit);

    boolean updateVisit(Long userId, int visitId, GymVisit gymVisit);

    boolean deleteVisit(Long userId, int visitId);
}
