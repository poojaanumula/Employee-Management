package io.nology.employee_details.employee;

import java.util.List;

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

}
