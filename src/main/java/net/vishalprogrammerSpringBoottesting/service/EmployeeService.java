package net.vishalprogrammerSpringBoottesting.service;

import net.vishalprogrammerSpringBoottesting.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployee();

   Optional<Employee> getEmployeeById(Long id);

   Employee updateEmployee(Employee updateEmployee);

   void deleteEmployee(Long id);
}
