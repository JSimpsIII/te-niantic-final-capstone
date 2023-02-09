package com.techelevator.dao.jdbc;

import com.techelevator.dao.GymVisitDao;
import com.techelevator.model.GymVisit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;


import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public List<GymVisit> getAllVisits(Long userId) {
        List<GymVisit> gymVisits = new ArrayList<>();
        String sqlQuery = "SELECT * FROM gym_visit WHERE customer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, userId);
        while (results.next()) {
            GymVisit gymVisit = mapRowToVisit(results);
            gymVisits.add(gymVisit);
        }
        return gymVisits;
    }

    @Override
    public int checkIn(Long userId) {
        String visitDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String checkIn = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
        Integer visitId;
        String sqlQuery = "INSERT INTO gym_visit " +
                "(customer_id, visit_date, check_in) " +
                "VALUES (?, ?, ?) RETURNING visit_id;";
        try {
            visitId = jdbcTemplate.queryForObject(sqlQuery, Integer.class, userId, visitDate, checkIn);
        } catch (Exception e) {
            return -1;
        }
        if (visitId == null) return -1;
        else return visitId;
    }

    @Override
    public int checkOut(Long userId, int visitId) {
        String checkOut = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
        String sqlQuery = "UPDATE gym_visit SET check_out = ? WHERE visit_id = ? AND customer_id = ?;";
        try {
            jdbcTemplate.update(sqlQuery, checkOut, visitId, userId);
            return visitId;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public boolean deleteVisit(Long customerId, int visitId) {
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
        gymVisit.setCheckIn(row.getTimestamp("check_in"));
        gymVisit.setCheckOut(row.getTimestamp("check_out"));
        return gymVisit;
    }

}
