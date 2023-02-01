package com.techelevator.dao;

import com.techelevator.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcEmployeeDao implements EmployeeDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcEmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Employee employee = mapRowToEmployee(results);
            employees.add(employee);
        }
        return employees;
    }

    @Override
    public Employee getEmployeeByName(String name) {
        return null;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return null;
    }

    @Override
    public boolean createNewEmployee(Employee employee) {
        String sql = "INSERT INTO employee (employee_name, is_admin) VALUES (?, ?)";
        Integer employeeId;
        try {
            employeeId = jdbcTemplate.queryForObject(sql, Integer.class, employee.getName(), employee.isAdmin());
        } catch (Exception e) {
            return false;
        }
        return (employeeId != null);
    }


    private Employee mapRowToEmployee(SqlRowSet row) {
        Employee employee = new Employee();
        employee.setId(row.getLong("employee_id"));
        employee.setName(row.getString("employee_name"));
        employee.setAdmin(row.getBoolean("is_admin"));
        return employee;
    }
}
