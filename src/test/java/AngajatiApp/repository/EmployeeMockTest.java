package AngajatiApp.repository;

import AngajatiApp.model.DidacticFunction;
import AngajatiApp.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMockTest {
    private EmployeeMock employeeMock;
@BeforeEach
void setUp(){ employeeMock = new EmployeeMock();}
    @Test
    void testModifyEmployeeFunction() {
        int employeeId = 4;

        Employee existingEmployee = employeeMock.findEmployeeById(employeeId);
        assertNotNull(existingEmployee, "Existing employee is null" + employeeId);
        assertEquals(DidacticFunction.ASISTENT, existingEmployee.getFunction());

        DidacticFunction newFunction = DidacticFunction.TEACHER;

        employeeMock.modifyEmployeeFunction(existingEmployee, newFunction);

        Employee modifiedEmployee = employeeMock.findEmployeeById(employeeId);
        assertNotNull(modifiedEmployee, "Modified employee is null. ID" + employeeId);
        assertEquals(newFunction, modifiedEmployee.getFunction());

    }
//    @Test
//    void testNotExistingId(){
//    int notExistingEmployeeId = 30;
//    Employee existingEmployee = employeeMock.findEmployeeById(notExistingEmployeeId);
//    assertNull(existingEmployee);
//    DidacticFunction newDidacticF = DidacticFunction.LECTURER;
//    employeeMock.modifyEmployeeFunction(existingEmployee, newDidacticF);
//    Employee notModifiedEmployee = employeeMock.findEmployeeById(notExistingEmployeeId);
//    assertNull(notModifiedEmployee,"Employee function remsains unchanged id" + notModifiedEmployee);
//    }
//
    @Test
    void testEmployeeNull(){
    assertThrows(NullPointerException.class, () -> employeeMock.modifyEmployeeFunction(null,DidacticFunction.TEACHER));
    }
}