package AngajatiApp.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import AngajatiApp.model.AgeCriteria;
import AngajatiApp.model.DidacticFunction;
import AngajatiApp.model.Employee;
import AngajatiApp.Exception.EmployeeException;
import AngajatiApp.validator.EmployeeValidator;
import AngajatiApp.model.SalaryCriteria;

public class EmployeeImpl implements EmployeeRepositoryInterface {

	private static final String ERROR_WHILE_READING_MSG = "Error while reading: ";
	private final String employeeDBFile = "employeeDB/employees.txt";
	private EmployeeValidator employeeValidator = new EmployeeValidator();
	private List<Employee> employeeList = new ArrayList<>();
	
	public EmployeeImpl() {
		employeeList = loadEmployeesFromFile();
	}

	@Override
	public boolean addEmployee(Employee employee) {
		employee.setId(employeeList.size());
		if (employeeValidator.isValid(employee)) {
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(employeeDBFile, true))) {
				bw.write(employee.toString());
				bw.newLine();
				employeeList.add(employee);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public void modifyEmployeeFunction(Employee oldEmployee, DidacticFunction newFunction) {
		oldEmployee.setFunction(newFunction);
	}
	
	private List<Employee> loadEmployeesFromFile() {
		final List<Employee> newEmployeeList = new ArrayList<Employee>();
		try (BufferedReader br = new BufferedReader(new FileReader(employeeDBFile));){
			String line;
			int counter = 0;
			while ((line = br.readLine()) != null) {
				try {
					final Employee employee = Employee.getEmployeeFromString(line, counter);
					newEmployeeList.add(employee);
					counter++;
				} catch (EmployeeException ex) {
					System.err.println(ERROR_WHILE_READING_MSG + ex.toString());
				}
			}
		} catch (IOException e) {
			System.err.println(ERROR_WHILE_READING_MSG + e);
		} 
		return newEmployeeList;
	}

	@Override
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	@Override
	public List<Employee> getEmployeeByCriteria() {
		List<Employee> employeeSortedList = new ArrayList<Employee>(employeeList);
		Collections.copy(employeeSortedList, employeeList);
		Collections.sort(employeeSortedList, new AgeCriteria());
		//System.out.println(employeeSortedList);
		Collections.sort(employeeSortedList, new SalaryCriteria());
		//System.out.println(employeeSortedList);
		return employeeSortedList;
	}

	@Override
	public Employee findEmployeeById(int idOldEmployee) {
		for (Employee employee : employeeList) {
			if (employee.getId() == idOldEmployee) {
				return employee;
			}
		}
		return null;
	}

}