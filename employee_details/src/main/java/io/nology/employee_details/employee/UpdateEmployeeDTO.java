package io.nology.employee_details.employee;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UpdateEmployeeDTO {
    
      @NotBlank(message = "First name can't be empty")
    private String firstname;

    private String middlename;

    @NotBlank(message = "Last name can't be empty")
    private String lastname;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid mobile number format")
    private String mobile;

    @NotBlank(message = "Residential address can't be empty")
    private String residentialAddress;

    @NotNull(message = "Employee status can't be empty")
    private Employee.EmployeeStatus employeeStatus;

    @NotNull(message = "Start date can't be empty")
    private LocalDate startDate;

    private LocalDate finishDate;

    private Boolean ongoing;

    @NotNull(message = "Work type basis can't be empty")
    private Employee.WorkTypeBasis workTypeBasis;

    @NotNull(message = "Hours per week can't be empty")
    @Min(value = 1, message = "Hours per week must be at least 1")
    @Max(value = 40, message = "Hours per week cannot exceed 40")
    private Integer hoursPerWeek;

    public UpdateEmployeeDTO() {}

    public UpdateEmployeeDTO(
        String firstname,
        String middlename,
        String lastname,
        String email,
        String mobile,
        String residentialAddress,
        Employee.EmployeeStatus employeeStatus,
        LocalDate startDate,
        LocalDate finishDate,
        Boolean ongoing,
        Employee.WorkTypeBasis workTypeBasis,
        Integer hoursPerWeek
    ) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.email = email;
        this.mobile = mobile;
        this.residentialAddress = residentialAddress;
        this.employeeStatus = employeeStatus;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.ongoing = ongoing;
        this.workTypeBasis = workTypeBasis;
        this.hoursPerWeek = hoursPerWeek;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public Employee.EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(Employee.EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public Boolean getOngoing() {
        return ongoing;
    }

    public void setOngoing(Boolean ongoing) {
        this.ongoing = ongoing;
    }

    public Employee.WorkTypeBasis getWorkTypeBasis() {
        return workTypeBasis;
    }

    public void setWorkTypeBasis(Employee.WorkTypeBasis workTypeBasis) {
        this.workTypeBasis = workTypeBasis;
    }

    public Integer getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(Integer hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }
}
