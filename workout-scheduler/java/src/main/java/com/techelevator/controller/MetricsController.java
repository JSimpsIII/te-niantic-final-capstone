package com.techelevator.controller;

import com.techelevator.model.profile.Metric;
import com.techelevator.model.profile.MetricDTO;
import org.springframework.web.bind.annotation.*;
import com.techelevator.dao.MetricDao;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class MetricsController {

    private MetricDao metricDao;
    public MetricsController(MetricDao metricDao) {
        this.metricDao = metricDao;
    }

    @RequestMapping(path = "/users/{id}/metrics", method = RequestMethod.GET)
    public List<Metric> getAllMetricsById(@RequestParam Long id) {
        return metricDao.getAllMetricsById(id);
    }

    @RequestMapping(path = "/users/{id}/metrics", method = RequestMethod.POST)
    public boolean addNewMetric(@RequestParam Long id, @RequestBody MetricDTO metricDTO) {
        Long customerId = metricDTO.getCustomerId();
        int exerciseId = metricDTO.getExerciseId();
        Date date = metricDTO.getDate();
        double reps = metricDTO.getReps();
        double weight = metricDTO.getWeight();
        double time = metricDTO.getTime();
        double distance = metricDTO.getDistance();
        int days = metricDTO.getDays();
        String misc = metricDTO.getMisc();
        Metric metric = new Metric(customerId, exerciseId, date, reps, weight, time, distance, days, misc);
        return metricDao.addNewMetric(id, metric);
    }
}
