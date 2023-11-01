package AngajatiApp.repository;

import AngajatiApp.model.DidacticFunction;
import AngajatiApp.model.Employee;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeImplTest {
    private Employee employee;
    private EmployeeImplTest et;

    EmployeeMock employeeMock=new EmployeeMock();
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @AfterAll
    static void afterAll() {

    }

    @Test
    void Test_addEmployeeCnpInvalid() {
  Employee employee = new Employee(20,"Alexandra","Simula","123456789101", DidacticFunction.TEACHER, 3000.00);
assertFalse(employeeMock.addEmployee(employee));
    }
    @Test
    void Test_addEmployeeName() {
        Employee employee = new Employee(20,"A", "Simula","1234567891012",DidacticFunction.TEACHER,3000.00);
        assertFalse(employeeMock.addEmployee(employee));
    }
    @Test
    void Test_addEmployeeCnpValid() {
        Employee employee = new Employee(20,"Alexandra", "Simula", "1234567891012", DidacticFunction.TEACHER,3000.00);
        assertTrue(employeeMock.addEmployee(employee));
    }
    @Test
    void Test_addEmployeeNameValid() {
        Employee employee = new Employee(20,"Alex","Simula","1234567891012",DidacticFunction.TEACHER,3000.00);
        assertTrue(employeeMock.addEmployee(employee));
    }
}