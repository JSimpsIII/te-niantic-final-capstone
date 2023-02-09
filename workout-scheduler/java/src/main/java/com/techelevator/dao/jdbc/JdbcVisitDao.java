package com.techelevator.dao.jdbc;

import com.techelevator.dao.VisitDao;
import com.techelevator.model.Visit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcVisitDao implements VisitDao {

    private JdbcTemplate jdbcTemplate;
    public JdbcVisitDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Visit> getAllVisits(Long userId) {
        List<Visit> visits = new ArrayList<>();
        String sqlQuery = "SELECT * FROM gym WHERE customer_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, userId);
        while (results.next()) {
            Visit visit = mapRowToVisit(results);
            visits.add(visit);
        }
        return visits;
    }

    @Override
    public boolean addVisit(Long userId, Visit visit) {
        boolean visitCreated = createNewVisit(userId, visit);
        return visitCreated;
    }

    private boolean createNewVisit(Long userId, Visit visit) {
        int customerId = visit.getCustomerId();
        String date = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        double minutes = visit.getMinutes();
        Integer visitId;
        String sqlQuery = "INSERT INTO gym " +
                "(customer_id, visit_date, visit_time_min) " +
                "VALUES (?, DATE ?, ?) RETURNING visit_id;";
        try {
            visitId = jdbcTemplate.queryForObject(sqlQuery, Integer.class, userId, date, minutes);
        } catch (Exception e) {
            return false;
        }
        return (visitId != null);
    }

    @Override
    public boolean deleteVisit(Long userId, int visitId) {
        String sqlQuery = "DELETE FROM gym " +
                "WHERE visit_id = ? AND customer_id = ?;";
        try {
            jdbcTemplate.update(sqlQuery, visitId, userId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private Visit mapRowToVisit(SqlRowSet row) {
        Visit visit = new Visit();
        visit.setVisitId(row.getInt("visit_id"));
        visit.setCustomerId(row.getInt("customer_id"));
        visit.setVisitDate(row.getDate("visit_date"));
        visit.setMinutes(row.getDouble("visit_time_min"));
        return visit;
    }

}
