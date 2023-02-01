package com.techelevator.dao;

import com.techelevator.model.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> getAllEmployees();

    Employee getEmployeeByName(String name);

    Employee getEmployeeById(Long id);

    boolean createNewEmployee(Employee employee);



}
