package io.nology.employee_details.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import io.nology.employee_details.common.exceptions.ConflictExceptions;
import io.nology.employee_details.common.exceptions.NotFoundExceptions;

public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;   
    @Mock
    private ModelMapper mapper;

    @Spy
    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.openMocks(this);

    }
    
    @Test
    public void getAll_callsFindAll()
    {
        employeeService.getAllEmployees();
        verify(employeeRepository).findAll();
    }
    
    @Test
    public void getEmployeeById_callsFindById()
    {
        employeeService.getEmployeeById(1L);
        verify(employeeRepository).findById(1L);
    }

    //     @Test
    // public void createBook_repoSavesMappedClass() {
    //     // given
    //     CreateBookDTO emptyDto = new CreateBookDTO();
    //     Book mockBook = new Book();
    //     // when
    //     when(modelmapper.map(emptyDto, Book.class)).thenReturn(mockBook);
    //     when(bookRepository.save(any(Book.class))).thenReturn(mockBook);
    //     // act
    //     Book result = bookService.createBook(emptyDto);
    //     // assert
    //     verify(bookRepository).save(mockBook);
    //     assertNotNull(result);
    //     assertEquals(mockBook, result);

    // }

    @Test
    public void createEmployee_repoSavesMappedClass() throws NotFoundExceptions, ConflictExceptions
    {
        CreateEmployeeDTO emptyDTO =  new CreateEmployeeDTO();
        Employee mockEmployee = new Employee();
        when(mapper.map(emptyDTO, Employee.class)).thenReturn(mockEmployee);
        when(employeeRepository.save(any(Employee.class))).thenReturn(mockEmployee);
        Employee result = employeeService.createEmployee(emptyDTO);
        verify(employeeRepository).save(mockEmployee);
        assertNotNull(result);
        assertEquals(mockEmployee, result);

    }
}
