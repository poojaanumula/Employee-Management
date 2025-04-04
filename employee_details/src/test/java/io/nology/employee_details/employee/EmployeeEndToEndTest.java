package io.nology.employee_details.employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import io.nology.employee_details.employee.Employee.EmployeeStatus;
import io.nology.employee_details.employee.Employee.WorkTypeBasis;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EmployeeEndToEndTest {
    @LocalServerPort
    private int port;

    private ArrayList<Employee> employee = new ArrayList<>();

    @Autowired
    private EmployeeRepository repo;

    @BeforeEach
    public void setUp(){

        RestAssured.port = port;
        repo.deleteAll();
        employee.clear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Employee emp1 = new Employee();
        emp1.setFirstname("Samantha");
        emp1.setMiddlename("Ruth");
        emp1.setLastname("Prabhu");
        emp1.setEmail("sam@gmail.com");
        emp1.setEmployeeStatus(EmployeeStatus.CONTRACT);
        emp1.setHoursPerWeek(30);
        emp1.setMobile("0456923450");
        emp1.setResidentialAddress("Westmead - 2146");
        emp1.setStartDate(LocalDate.parse("2025-03-01", formatter));
        emp1.setFinishDate(LocalDate.parse("2025-04-17", formatter));
        emp1.setOngoing(true);
        emp1.setWorkTypeBasis(WorkTypeBasis.FULL_TIME);
        this.repo.save(emp1);
        employee.add(emp1);

    }

   @Test
    public void getAllEmployees() {
        given().when()
                .get("/employee")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(1))
                .body("firstname", hasItems("Samantha"));

    }
    @Test
    public void getAllEmployees_NoEmployeesINDB_ReturnsEmptyArray()
    {
        this.repo.deleteAll();
        given()
        .when()
        .get("/employee")
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("$", hasSize(0));
    
    }

    @Test
    public void getById_ValidID_returnsEmployee()
    {
       Employee firsEmployee = employee.get(0);
        given()
        .when()
        .get("/employee/"+firsEmployee.getId())
        .then()
        .statusCode(HttpStatus.OK.value())
        .body("firstname", equalTo("Samantha"))
        .body("lastname", equalTo("Prabhu"))
        .body(matchesJsonSchemaInClasspath("/schemas/employee-schema.json"));
    }

    @Test
    public void getById_InvalidId_BadRequest()
    {
        given()
        .when()
        .get("/employee/snfdklvnlasnf")
        .then()
        .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void getById_IDNotInDb_NotFound()
    {
        given()
        .when()
        .get("/employee/9999999999")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());
    }
}


