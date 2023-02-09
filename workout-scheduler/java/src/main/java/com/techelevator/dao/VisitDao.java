package com.techelevator.dao;

import com.techelevator.model.Visit;

import java.util.List;

public interface VisitDao {

    List<Visit> getAllVisits(Long userId);

    boolean addVisit(Long userId, Visit visit);

    boolean deleteVisit(Long userId, int visitId);
}
