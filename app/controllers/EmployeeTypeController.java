package controllers;

import play.*;

import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class EmployeeTypeController extends Controller {

	// TODO change all methods

	public static void loadEmployeeTypes() {// load employees management page`

		// contains all the taxes that existing in the system for employees
		List<Tax> tax = Tax.findAll();

		// contains all the employeetypes in the system
		List<EmployeeType> empTypes = EmployeeType.findAll();
		render("EmployeeType/EmployeeType.html", tax, empTypes);
	}

	public static void renderTaxList() {
		List<Tax> tax = Tax.find("order by taxName desc").fetch();
		renderJSON(tax.iterator().next().taxName);
	}

	public static void createEmployeeType(String expenseCatName, long[] taxids, float preTaxSalary) {
		EmployeeType empType = new EmployeeType(expenseCatName, taxids, preTaxSalary);
		empType.createdBy = Authenticate.getLoggedInUser();
		empType.save();
		EmployeeTypeController.loadEmployeeTypes();
	}

	public static void deleteEmployeeType(long[] empTypeid) {
		String name = "";
		try {
			for (long i : empTypeid) {
				EmployeeType empObj = EmployeeType.findById(i);
				name = empObj.name;
				empObj.delete();
			}
		} catch (Exception ex) {
			flash.error("%s can not be deleted because it is in use", name);
		} finally {
			EmployeeTypeController.loadEmployeeTypes();
		}
	}
}
