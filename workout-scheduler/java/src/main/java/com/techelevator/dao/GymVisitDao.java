package com.techelevator.dao;

import com.techelevator.model.GymVisit;

import java.util.List;

public interface GymVisitDao {

    List<GymVisit> getAllVisits(Long customerId);

    boolean addVisit(Long userId, GymVisit visit);

    boolean deleteVisit(Long userId, int visitId);
}
