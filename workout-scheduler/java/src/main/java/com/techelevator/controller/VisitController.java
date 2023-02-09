package com.techelevator.controller;
import com.techelevator.model.dto.VisitDTO;
import com.techelevator.dao.VisitDao;
import com.techelevator.model.Visit;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class VisitController {

    private VisitDao visitDao;
    public VisitController(VisitDao visitDao) {
        this.visitDao = visitDao;
    }

    @RequestMapping(path = "/users/{userId}/gym", method = RequestMethod.GET)
    public List<Visit> getAllVisits(@PathVariable Long userId) {
        return visitDao.getAllVisits(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/users/{userId}/gym", method = RequestMethod.POST)
    public boolean addVisit(@PathVariable Long userId, @RequestBody VisitDTO visitDTO) {
        Visit visit = new Visit();
        visit.setCustomerId(visitDTO.getCustomerId());
        visit.setVisitDate(visitDTO.getVisitDate());
        visit.setMinutes(visitDTO.getMinutes());
        return visitDao.addVisit(userId, visit);
    }


    @RequestMapping(path = "/users/{userId}/gym/{visitId}", method = RequestMethod.DELETE)
    public boolean deleteVisit(@PathVariable Long userId, @PathVariable int visitId) {
        return visitDao.deleteVisit(userId, visitId);
    }
}
