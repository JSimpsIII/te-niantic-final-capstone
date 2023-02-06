package com.techelevator.dao.jdbc;

import com.techelevator.dao.ExerciseDao;
import com.techelevator.model.Exercise;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcExerciseDao implements ExerciseDao {

    private JdbcTemplate jdbcTemplate;
    public JdbcExerciseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private String sqlQueryGetAll = "SELECT ex.exercise_id, ex.exercise_name, " +
            "b.bodypart_name, eq.equipment_name, tg.target_name, ex.gif_url " +
            "FROM exercise AS ex " +
            "JOIN exercise_bodypart AS b ON b.bodypart_id = ex.body_part " +
            "JOIN exercise_equipment AS eq ON eq.equipment_id = ex.equipment " +
            "JOIN exercise_target AS tg ON tg.target_id = ex.exercise_target";

    @Override
    public List<Exercise> getAllExercises() {
        List<Exercise> exercises = new ArrayList<>();
        String sqlQuery = sqlQueryGetAll + ";";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery);
        while(results.next()) {
            Exercise exercise = mapRowToExercise(results);
            exercises.add(exercise);
        }
        return exercises;
    }

    @Override
    public Exercise getExerciseById(int id) {
        Exercise exercise = new Exercise();
        String sqlQuery = sqlQueryGetAll + " WHERE ex.exercise_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, id);
        if(results.next()) {
            exercise = mapRowToExercise(results);
        }
        return exercise;
    }

    private Exercise mapRowToExercise(SqlRowSet row) {
        Exercise exercise = new Exercise();
        exercise.setId(row.getInt("exercise_id"));
        exercise.setName(row.getString("exercise_name"));
        exercise.setBodyPart(row.getString("bodypart_name"));
        exercise.setEquipment(row.getString("equipment_name"));
        exercise.setTarget(row.getString("target_name"));
        exercise.setGifUrl(row.getString("gif_url"));
        return exercise;
    }

}
