package controllers;

import play.*;

import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class EmployeeController extends Controller {


    public static void loadEmployees() {// load employees management page
    	//list of the types of employees that the organization has
    	List<EmployeeType> empTypes = EmployeeType.findAll();
    	
    	//list of all the employees that the organization current has
    	List<Employee> employees = Employee.findAll();
        render("Employee/employees.html",empTypes,employees);
    }

    public static void createEmployee(String first_name, String middle_name, String last_name,
            String sex, long employeetypeid, Date date_hired, Date date_fired, User createdBy) { // new
        // employee
    	System.out.println(first_name);
        Employee employee = new Employee(first_name, middle_name, last_name, sex, employeetypeid,date_hired, date_fired);
        employee.save();
        EmployeeController.loadEmployees();
    }

    public static void removeEmployee(long[] employeeid) {
    	
    	for(long i: employeeid){
    		Employee employee = Employee.findById(i);
        	employee.delete();
    	}
    	EmployeeController.loadEmployees();
    }

    public static void listEmployees() {
        List<Employee> employees = Employee.find("order by first_name desc").fetch(10);
        renderJSON(employees);
    }

}
