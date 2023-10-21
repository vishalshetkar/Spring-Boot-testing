package net.vishalprogrammerSpringBoottesting.controller;

import net.vishalprogrammerSpringBoottesting.model.Employee;
import net.vishalprogrammerSpringBoottesting.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Employees")
public class EmployeeController {

    private EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee){

        return employeeService.saveEmployee(employee);
    }

    @GetMapping

    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }


    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") long employeeId){
        return employeeService.getEmployeeById(employeeId)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId,@RequestBody Employee employee){
        return employeeService.getEmployeeById(employeeId)
                .map(savedEmployee->{
                    savedEmployee.setFirstName(employee.getFirstName());
                    savedEmployee.setLastName(employee.getLastName());
                    savedEmployee.setEmail(employee.getEmail());

                  Employee updateEmployee= employeeService.updateEmployee(savedEmployee);
                  return  new ResponseEntity<>(updateEmployee,HttpStatus.OK);
                }).orElseGet(()->ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId){

         employeeService.deleteEmployee(employeeId);
         return new ResponseEntity<>("it is delete",HttpStatus.OK);
    }
}
