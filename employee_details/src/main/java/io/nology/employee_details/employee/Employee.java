package io.nology.employee_details.employee;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

    public enum EmployeeStatus
    {
        PERMANENT,
        CONTRACT
    }

    public enum WorkTypeBasis{
        PART_TIME,
        FULL_TIME
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column
    private String middlename;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;
  
    @Column(nullable = false, unique = true)
    private String mobile;

    @Column(nullable = false)
    private String residentialAddress;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;

     @Column(nullable = false)
    private LocalDate startDate;

    @Column
    private LocalDate finishDate;

    @Column(nullable = false)
    private boolean ongoing; 

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkTypeBasis workTypeBasis;

    @Column(nullable = false)
    private int hoursPerWeek;

    public Employee() {
    }

    public Employee(String firstname, String middlename, String lastname, String email, String mobile,
            String residentialAddress, EmployeeStatus employeeStatus, LocalDate startDate, LocalDate finishDate,
            boolean ongoing, WorkTypeBasis workTypeBasis, int hoursPerWeek) {
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

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
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

    public boolean isOngoing() {
        return ongoing;
    }

    public void setOngoing(boolean ongoing) {
        this.ongoing = ongoing;
    }

    public WorkTypeBasis getWorkTypeBasis() {
        return workTypeBasis;
    }

    public void setWorkTypeBasis(WorkTypeBasis workTypeBasis) {
        this.workTypeBasis = workTypeBasis;
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(int hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }
    
    
    

}
