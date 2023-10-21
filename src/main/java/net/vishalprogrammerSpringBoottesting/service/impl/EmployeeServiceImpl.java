package net.vishalprogrammerSpringBoottesting.service.impl;

import ch.qos.logback.classic.spi.IThrowableProxy;
import net.vishalprogrammerSpringBoottesting.exception.ResourceNotFoundException;
import net.vishalprogrammerSpringBoottesting.model.Employee;
import net.vishalprogrammerSpringBoottesting.repository.EmployeeRepository;
import net.vishalprogrammerSpringBoottesting.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeServiceImpl implements EmployeeService {


    EmployeeRepository employeeRepository;

    // we no need to write the annotation because the we have only one value that why it is automatically inject
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
    Optional<Employee> findByEmployee =employeeRepository.findByEmail(employee.getEmail());
    if(findByEmployee.isPresent()){
       throw new ResourceNotFoundException("employee already exit with given email :"+employee.getEmail());
    }else{
       return employeeRepository.save(employee);
    }

    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee updateEmployee(Employee updateEmployee) {
        return employeeRepository.save(updateEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
