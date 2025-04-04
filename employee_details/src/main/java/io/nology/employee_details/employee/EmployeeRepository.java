package io.nology.employee_details.employee;


import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository <Employee, Long> {
    boolean existsByEmail(String email);  // Check if email already exists
}
