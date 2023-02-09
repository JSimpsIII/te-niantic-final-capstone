package com.techelevator.dao.jdbc;

import com.techelevator.dao.GymVisitDao;
import com.techelevator.model.GymVisit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
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
    public int newVisit(Long userId, GymVisit visit) {
        Long customerId = visit.getCustomerId();
        Date visitDate = (Date) visit.getVisitDate();
        Time checkIn = visit.getCheckIn();
        Time checkOut = Time.valueOf(LocalTime.now());

        Integer visitId;
        String sqlQuery = "INSERT INTO gym_visit " +
                "(customer_id, visit_date, check_in, check_out) " +
                "VALUES (?, ?, ?, ?) RETURNING visit_id;";
        try {
            visitId = jdbcTemplate.queryForObject(sqlQuery, Integer.class, customerId, visitDate, checkIn, checkOut);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
        if (visitId == null) return -1;
        else return visitId;
    }

    @Override
    public boolean updateVisit(Long userId, int visitId, GymVisit visit) {
        Time checkOut = Time.valueOf(LocalTime.now());;
        String sqlQuery = "UPDATE gym_visit " +
                "SET check_out = ? " +
                "WHERE visit_id = ?;";
        try {
            jdbcTemplate.update(sqlQuery, checkOut, visitId);
        } catch (Exception e) {
            return false;
        }
        return true;
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
        gymVisit.setCheckIn(row.getTime("check_in"));
        gymVisit.setCheckOut(row.getTime("check_out"));
        return gymVisit;
    }

}
