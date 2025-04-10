package io.nology.employee_details.employee;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import io.nology.employee_details.common.exceptions.ConflictExceptions;
import io.nology.employee_details.common.exceptions.NotFoundExceptions;
import jakarta.validation.Valid;
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;   
    private ModelMapper mapper;


    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public Employee createEmployee(CreateEmployeeDTO data) throws NotFoundExceptions, ConflictExceptions {
        if (employeeRepository.existsByEmail(data.getEmail())) {
            throw new ConflictExceptions("Email already exists.");
        }

        if (data.getOngoing() && data.getFinishDate() != null) {
            throw new NotFoundExceptions("Finish date must be empty if the employee is ongoing.");
        }
        if (!data.getOngoing() && data.getFinishDate() == null) {
            throw new NotFoundExceptions("Finish date is required for employees who are not ongoing.");
        }

        Employee newEmployee = mapper.map(data, Employee.class);
        return this.employeeRepository.save(newEmployee);
        
    }


    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }


    public Optional<Employee> getEmployeeById(Long id) {
        return this.employeeRepository.findById(id);
    }


    public boolean deleteEmployeeWithId(Long id) {
        Optional<Employee> result = this.getEmployeeById(id);
        if(result.isEmpty())
        {
            return false;
        }
        this.employeeRepository.delete(result.get());
        return true;
    }

    public Optional<Employee> updateEmployeeById(Long id, UpdateEmployeeDTO data) throws NotFoundExceptions, ConflictExceptions
    {
        Optional<Employee> result = this.getEmployeeById(id);
        if(result.isEmpty())
        {
            return result;
        }
        Employee foundEmployee = result.get();

        if (data.getOngoing() && data.getFinishDate() != null) {
            throw new NotFoundExceptions("Finish date must be empty if the employee is ongoing.");
        }
        if (!data.getOngoing() && data.getFinishDate() == null) {
            throw new NotFoundExceptions("Finish date is required for employees who are not ongoing.");
        }
        mapper.map(data, foundEmployee);
        this.employeeRepository.save(foundEmployee);
        return Optional.of(foundEmployee);
    }

}
