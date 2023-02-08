package com.techelevator.controller;


import com.techelevator.dao.GymVisitDao;
import com.techelevator.model.GymVisit;
import com.techelevator.model.dto.GymVisitDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "users/{userId}/gymlogs", method = RequestMethod.POST)
    public boolean newVisit(@PathVariable Long userId, @RequestBody GymVisitDTO gymVisitDTO) {
        Long customerId = gymVisitDTO.getCustomerId();
        Date checkIn = gymVisitDTO.getCheckIn();
        GymVisit gymVisit = new GymVisit(customerId, checkIn);
        return gymVisitDao.newVisit(userId, gymVisit);
    }

    @RequestMapping(path = "users/{userId}/gymlogs/{visitId}", method = RequestMethod.PUT)
    public boolean updateVisit(@PathVariable Long userId, @PathVariable int visitId, @RequestBody GymVisitDTO gymVisitDTO) {
        Date checkIn = gymVisitDTO.getCheckIn();
        Date checkOut = gymVisitDTO.getCheckOut();
        GymVisit gymVisit = new GymVisit(visitId, userId, checkIn, checkOut);
        return gymVisitDao.updateVisit(userId, visitId, gymVisit);
    }

    @RequestMapping(path = "users/{userId}/gymlogs/{visitId}", method = RequestMethod.DELETE)
    public boolean deletedVisit(@PathVariable Long userId, @PathVariable int visitId) {
        return gymVisitDao.deleteVisit(userId, visitId);
    }
}
