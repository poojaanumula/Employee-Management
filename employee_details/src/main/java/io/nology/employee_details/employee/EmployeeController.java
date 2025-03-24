package io.nology.employee_details.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nology.employee_details.common.exceptions.NotFoundExceptions;
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


@GetMapping("/{id}")
public ResponseEntity<Employee> getIndividualEmployee(@PathVariable Long id) throws NotFoundExceptions{
      Optional<Employee> resultOfEmployee = this.employeeService.getEmployeeById(id);
      Employee foundEmployee = resultOfEmployee.orElseThrow(()-> new NotFoundExceptions("Sorry, could not find employee with id "+id));
      return new ResponseEntity<Employee>(foundEmployee, HttpStatus.OK);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteByBookId(@PathVariable Long id) throws NotFoundExceptions{
    boolean wasDeleted = this.employeeService.deleteEmployeeWithId(id);
    if(!wasDeleted)
    {
        throw new NotFoundExceptions("Could not delete employee with id " +id +"it doesnot exists");
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
    
//@PutMapping("/{id}")
@PutMapping("/{id}")
public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @Valid @RequestBody UpdateEmployeeDTO data) throws NotFoundExceptions
{
Optional<Employee> result = this.employeeService.updateEmployeeById(id, data);
Employee foundEmployee = result.orElseThrow(()-> new NotFoundExceptions("Could not update employee with id " +id));
return new ResponseEntity<Employee>(foundEmployee, HttpStatus.OK);
} 


}
