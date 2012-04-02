package controllers;

import play.*;

import play.data.validation.Email;
import play.data.validation.Password;
import play.data.validation.Required;
import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class EmployeeController extends Controller {

	public static void loadEmployees() {// load employees management page
		// list of the types of employees that the organization has
		List<EmployeeType> empTypes = EmployeeType.findAll();

		// list of all the employees that the organization current has
		List<Employee> employees = Employee.findAll();
		render("Employee/employees.html", empTypes, employees);
	}

	public static void createEmployee(String first_name, String middle_name,
			String last_name, String sex, long employeetypeid, String email,
			Date date_hired, Date date_fired) { // new
		// employee
//		System.out.println(first_name);
		Employee employee = new Employee(first_name, middle_name, last_name,
				sex, employeetypeid, email, date_hired, date_fired);
//		if (session.get("user") == null) {
//			Authenticate.login();
//		}
//		employee.createdBy = Authenticate.getLoggedInUser();
		employee.save();
		EmployeeController.loadEmployees();
	}

	public static void createFirstEmployee(String first_name, String middle_name, String last_name, String username, @Password String password, @Email String email, String sex, Date date_hired, Date date_fired) {
		
		EmployeeType firstType = new EmployeeType("Administrator").save();
		Employee firstEmployee = new Employee(first_name, middle_name, last_name, sex, firstType.id, email, date_hired, date_fired).save();
		
		User firstUser = new User(firstEmployee, username, firstEmployee.email, password, firstEmployee.first_name, firstEmployee.last_name).save();
		Authenticate.doRegister(firstUser);
	}

	public static void removeEmployee(long[] employeeid) {

		for (long i : employeeid) {
			Employee employee = Employee.findById(i);
			employee.delete();
		}
		EmployeeController.loadEmployees();
	}
	
	public static void editEmployee(long employeeid, String first_name, String middle_name,
			String last_name, String sex, long employeetypeid, @Required @Email String email,
			Date date_hired, Date date_fired){
		if (session.get("user") == null) {
			Authenticate.login();
			}
		Employee editee = Employee.findById(employeeid);
		editee.first_name = first_name;
		editee.middle_name = middle_name;
		editee.last_name = last_name;
		editee.sex = sex;
		editee.type.id = employeetypeid;
		editee.email = email; // TODO see if a check on isUser is necessary
		editee.date_hired = date_fired;
		editee.date_fired = date_fired;
//		editee.lastUpdateBy = Authenticate.getLoggedInUser(); //who last modified this employee record
		editee.save();
		EmployeeController.loadEmployees();
	}

	public static void listEmployees() {
		List<Employee> employees = Employee.find("order by first_name desc")
				.fetch(10);
		renderJSON(employees);
	}

}
