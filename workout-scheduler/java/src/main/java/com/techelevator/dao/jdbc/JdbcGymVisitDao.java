package com.techelevator.dao.jdbc;

import com.techelevator.dao.GymVisitDao;
import com.techelevator.model.GymVisit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JdbcGymVisitDao implements GymVisitDao {

    private JdbcTemplate jdbcTemplate;
    public JdbcGymVisitDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<GymVisit> getAllVisits(Long customerId) {
        List<GymVisit> gymVisits = new ArrayList<>();
        String sqlQuery = "SELECT * FROM gym_visit WHERE customer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, customerId);
        while (results.next()) {
            GymVisit gymVisit = mapRowToVisit(results);
            gymVisits.add(gymVisit);
        }
        return gymVisits;
    }

    @Override
    public int newVisit(Long customerId) {
        int visitCreated = addNewVisit(customerId);
        return visitCreated;
    }

    @Override
    public boolean updateVisit(Long customerId, int visitId) {
        boolean visitUpdated = updateGymVisit(customerId, visitId);
        return visitUpdated;
    }

    @Override
    public boolean deleteVisit(Long customerId, int visitId) {
        boolean visitDeleted = deleteGymVisit(customerId, visitId);
        return visitDeleted;
    }

    private int addNewVisit(Long customerId) {
        String visitDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        Time checkIn = Time.valueOf(LocalTime.now());

        Integer visitId;
        String sqlQuery = "INSERT INTO gym_visit " +
                "(customer_id, visit_date, check_in) " +
                "VALUES (?, ?, ?) RETURNING visit_id;";
        try {
            visitId = jdbcTemplate.queryForObject(sqlQuery, Integer.class, customerId, visitDate, checkIn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
        if (visitId == null) return -1;
        return visitId;
    }

    private boolean updateGymVisit(Long customerId, int visitId) {
        Time checkOut = Time.valueOf(LocalTime.now());
        String sqlQuery = "UPDATE gym_visit " +
                "SET check_out = ? " +
                "WHERE visit_id = ? AND customer_id = ?;";
        try {
            jdbcTemplate.update(sqlQuery, checkOut, visitId, customerId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean deleteGymVisit(Long customerId, int visitId) {
        String sqlQuery = "DELETE FROM gym_visit " +
                "WHERE visit_id = ? AND customer_id = ?;";
        try {
            jdbcTemplate.update(sqlQuery, visitId, customerId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    GymVisit mapRowToVisit(SqlRowSet row) {
        GymVisit gymVisit = new GymVisit();
        gymVisit.setVisitId(row.getInt("visit_id"));
        gymVisit.setCustomerId(row.getLong("customer_id"));
        gymVisit.setVisitDate(row.getDate("visit_date"));
        gymVisit.setCheckIn(row.getTime("check_in"));
        gymVisit.setCheckOut(row.getTime("check_out"));
        return gymVisit;
    }

}
