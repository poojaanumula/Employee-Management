package io.nology.employee_details.employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

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
import io.restassured.http.ContentType;

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
                .body("firstname", hasItems("Samantha"))
                .body(matchesJsonSchemaInClasspath("schemas/employee-array-schema.json"));

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
        .body(matchesJsonSchemaInClasspath("schemas/employee-schema.json"));
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
        .get("/employee/99999999999999999")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());
    }
    
    @Test
    public void createEmployee_whenPassedPlainText_415()
    {
        given()
        .contentType(ContentType.TEXT)
        .body("Hello")
        .when()
        .post("/employee")
        .then()
        .statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
    }

  
    @Test
    public void createEmployee_whenPassedEmptyBody_BadRequest()
    {
        given()
        .contentType(ContentType.JSON)
        .when()
        .post("/employee")
        .then()
        .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void createEmployee_whenPassedBadData_BadRequest()
    {
        HashMap<String, String>  data = new HashMap<>();
        data.put("firstName", "Neha");
        given()
        .contentType(ContentType.JSON).body(data)
        .when()
        .post("/employee")
        .then()
        .statusCode(HttpStatus.BAD_REQUEST.value());

    }

    @Test
public void createEmployee_whenPassedValidData_Created() {
    HashMap<String, Object> data = new HashMap<>();
    data.put("firstname", "Lena");
    data.put("middlename", "Marie"); 
    data.put("lastname", "Smith");
    data.put("email", "lena.smith@example.com");
    data.put("mobile", "0412345678");
    data.put("residentialAddress", "123 Sydney Street, NSW");
    data.put("employeeStatus", "PERMANENT");
    data.put("startDate", "2024-04-01"); 
    data.put("finishDate", "2024-12-31"); 
    data.put("ongoing", false);
    data.put("workTypeBasis", "FULL_TIME"); 
    data.put("hoursPerWeek", 38); 

    given()
        .contentType(ContentType.JSON)
        .body(data)
    .when()
        .post("/employee")
    .then()
        .statusCode(HttpStatus.CREATED.value())
        .body(matchesJsonSchemaInClasspath("schemas/employee-schema.json"));
}


   

}


