package com.techelevator.dao;

import com.techelevator.model.profile.Metric;

import java.util.List;

public interface MetricDao {

    List<Metric> getAllMetrics(Long userId);

    boolean logNewMetric(Long userId, Metric metric);

}
