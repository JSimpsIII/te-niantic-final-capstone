package com.techelevator.dao;

import com.techelevator.model.GymVisit;

import java.util.Date;
import java.util.List;

public interface GymVisitDao {

    List<GymVisit> getAllVisits(Long customerId);

    int newVisit(Long userId);

    boolean updateVisit(Long userId, int visitId);

    boolean deleteVisit(Long userId, int visitId);
}
