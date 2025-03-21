package io.nology.employee_details.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public Employee createEmployee(CreateEmployeeDTO data) {

        if (data.getOngoing() && data.getFinishDate() != null) {
            throw new IllegalArgumentException("Finish date must be empty if the employee is ongoing.");
        }
        if (!data.getOngoing() && data.getFinishDate() == null) {
            throw new IllegalArgumentException("Finish date is required for employees who are not ongoing.");
        }


        
        Employee employee = new Employee();
        employee.setFirstname(data.getFirstname());
        employee.setMiddlename(data.getMiddlename());
        employee.setLastname(data.getLastname());
        employee.setEmail(data.getEmail());
        employee.setMobile(data.getMobile());
        employee.setResidentialAddress(data.getResidentialAddress());
        employee.setEmployeeStatus(data.getEmployeeStatus());
        employee.setStartDate(data.getStartDate());
        employee.setFinishDate(data.getFinishDate());
        employee.setOngoing(data.getOngoing());
        employee.setWorkTypeBasis(data.getWorkTypeBasis());
        employee.setHoursPerWeek(data.getHoursPerWeek());

        return employeeRepository.save(employee);
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

    public Optional<Employee> updateEmployeeById(Long id, UpdateEmployeeDTO data) {
        Optional<Employee> result = this.getEmployeeById(id);
        if(result.isEmpty())
        {
            return result;
        }
        Employee foundEmployee = result.get();
        if (data.getFirstname() != null) {
            foundEmployee.setFirstname(data.getFirstname().trim());
        }
        if (data.getMiddlename() != null) {
            foundEmployee.setMiddlename(data.getMiddlename().trim());
        }
        if (data.getLastname() != null) {
            foundEmployee.setLastname(data.getLastname().trim());
        }
        if (data.getEmail() != null) {
            foundEmployee.setEmail(data.getEmail().trim());
        }
        if (data.getMobile() != null) {
            foundEmployee.setMobile(data.getMobile().trim());
        }
        if (data.getResidentialAddress() != null) {
            foundEmployee.setResidentialAddress(data.getResidentialAddress().trim());
        }
        if (data.getEmployeeStatus() != null) {
            foundEmployee.setEmployeeStatus(data.getEmployeeStatus());
        }
        if (data.getStartDate() != null) {
            foundEmployee.setStartDate(data.getStartDate());
        }
        if (data.getFinishDate() != null) {
            foundEmployee.setFinishDate(data.getFinishDate());
        }
        if (data.getOngoing() != null) {
            foundEmployee.setOngoing(data.getOngoing());
        }
        if (data.getWorkTypeBasis() != null) {
            foundEmployee.setWorkTypeBasis(data.getWorkTypeBasis());
        }
        if (data.getHoursPerWeek() != null) {
            foundEmployee.setHoursPerWeek(data.getHoursPerWeek());
        }
        this.employeeRepository.save(foundEmployee);
        return Optional.of(foundEmployee);
    }

}
