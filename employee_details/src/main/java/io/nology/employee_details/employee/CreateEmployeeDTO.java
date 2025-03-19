package io.nology.employee_details.employee;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateEmployeeDTO {
 @NotBlank(message = "First name is required")
    private String firstname;

    private String middlename;

    @NotBlank(message = "Last name is required")
    private String lastname;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid mobile number format")
    private String mobile;

    @NotBlank(message = "Residential address is required")
    private String residentialAddress;

    @NotNull(message = "Employee status is required")
    private Employee.EmployeeStatus employeeStatus;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    private LocalDate finishDate;

    @NotNull(message = "Ongoing status is required")
    private Boolean ongoing;  

    @NotNull(message = "Work type is required")
    private Employee.WorkTypeBasis workTypeBasis;

    @Min(value = 1, message = "Hours per week must be at least 1")
    @Max(value = 40, message = "Hours per week cannot exceed 40")
    private int hoursPerWeek;

  
    public CreateEmployeeDTO() {
    }

    public CreateEmployeeDTO(String firstname, String middlename, String lastname, String email, String mobile,
                             String residentialAddress, Employee.EmployeeStatus employeeStatus, LocalDate startDate,
                             LocalDate finishDate, Boolean ongoing, Employee.WorkTypeBasis workTypeBasis, int hoursPerWeek) {
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

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(int hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    @Override
    public String toString() {
        return "CreateEmployeeDTO [firstname=" + firstname + ", middlename=" + middlename + ", lastname=" + lastname
                + ", email=" + email + ", mobile=" + mobile + ", residentialAddress=" + residentialAddress
                + ", employeeStatus=" + employeeStatus + ", startDate=" + startDate + ", finishDate=" + finishDate
                + ", ongoing=" + ongoing + ", workTypeBasis=" + workTypeBasis + ", hoursPerWeek=" + hoursPerWeek + "]";
    }
    
    

}
