package controllers;

import play.*;

import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class EmployeeController extends Controller {
	
	public static void loadEmployees() {// load employees management page
		render("Employee/employees.html");
	}
	
	public static void createEmployee(String first_name, String last_name,
			String sex, String specialty) { // new employee
		Employee employee = new Employee(first_name, last_name, sex, specialty)
				.save();
		renderJSON(employee);
	}
	
	public static void changeStatus(Long id, boolean fired) {
		Employee employee = Employee.findById(id);
		employee.fired = fired;
		employee.save();
		renderJSON(employee);
	}
	
	

}
