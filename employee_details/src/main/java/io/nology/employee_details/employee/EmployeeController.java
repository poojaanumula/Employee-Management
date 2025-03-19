package io.nology.employee_details.employee;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

private EmployeeService employeeService;


public EmployeeController(EmployeeService employeeService) 
{
    this.employeeService = employeeService;
}


@PostMapping
public ResponseEntity<Employee> postEmployee(@Valid @RequestBody CreateEmployeeDTO data)
{
          Employee newEmployee =  this.employeeService.createEmployee(data);  
          return new ResponseEntity<Employee>(newEmployee, HttpStatus.CREATED);
}


@GetMapping
public ResponseEntity<List<Employee>> getEmployees()
{
      List<Employee> employeesList =  this.employeeService.getAllEmployees();
      return new ResponseEntity<>(employeesList, HttpStatus.OK);
}

}
