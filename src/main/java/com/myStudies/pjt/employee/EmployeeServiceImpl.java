package com.myStudies.pjt.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.myStudies.pjt.employee.model.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	final Random random = new Random();

	// For ease we are not making the database interaction here. 
	// Readers can inject the dao layer here to make the real-time database interactions.
	@Override
	public List<Employee> findAll() {
		final List<Employee> employees = new ArrayList<>();
		// Creating a list of employees using the "faker" object.
		for(int count=0; count<21; count++) {
			employees.add(new Employee(random.nextInt(30 + 1), "Amine "+random.nextInt(30 + 1), 
					"Mon Titre "+random.nextInt(30 + 1), "My job "+random.nextInt(30 + 1)));
		}
		return employees;
	}
}
