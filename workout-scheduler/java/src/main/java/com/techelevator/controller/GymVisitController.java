package com.techelevator.controller;


import com.techelevator.dao.GymVisitDao;
import com.techelevator.model.GymVisit;
import com.techelevator.model.dto.GymVisitDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class GymVisitController {

    private GymVisitDao gymVisitDao;

    public GymVisitController(GymVisitDao gymVisitDao) {
        this.gymVisitDao = gymVisitDao;
    }

    @RequestMapping(path = "users/{userId}/gymlogs", method = RequestMethod.GET)
    public List<GymVisit> getAllVisits(@PathVariable Long userId) {
        return gymVisitDao.getAllVisits(userId);

    }

    @RequestMapping(path = "users/{userId}/gymlogs", method = RequestMethod.POST)
    public int newVisit(@PathVariable Long userId) {
        return gymVisitDao.newVisit(userId);
    }

    @RequestMapping(path = "users/{userId}/gymlogs/{visitId}", method = RequestMethod.PUT)
    public boolean updateVisit(@PathVariable Long userId, @PathVariable int visitId) {
        return gymVisitDao.updateVisit(userId, visitId);
    }

    @RequestMapping(path = "users/{userId}/gymlogs/{visitId}", method = RequestMethod.DELETE)
    public boolean deletedVisit(@PathVariable Long userId, @PathVariable int visitId) {
        return gymVisitDao.deleteVisit(userId, visitId);
    }
}
