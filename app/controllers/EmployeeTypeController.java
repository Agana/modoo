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

	public static void createEmployeeType(String expenseCatName,float preTaxSalary,long[] taxids) {
//		System.out.println(expenseCatName);
		float totalPercentage=0;
//		System.out.println("Pre tax salary: " + preTaxSalary);
		if(taxids == null){
			flash.error("Please enter at least one tax deduction.");
			EmployeeTypeController.loadEmployeeTypes();
		}
		for(long i:taxids){
			Tax tax = Tax.findById(i);
			totalPercentage+= tax.percentage;
		}
		
//		System.out.println("Total Tax percentage "+totalPercentage);
		
		//creating a new EmployeeType
		EmployeeType empType = new EmployeeType(expenseCatName,preTaxSalary,totalPercentage);
		empType.save();
		
		EmployeeTypeController.loadEmployeeTypes();
		
//		EmployeeType empType = new EmployeeType(expenseCatName, preTaxSalary);
//		empType.createdBy = Authenticate.getLoggedInUser();
//		for(long i:taxids){
//			System.out.println(" taxids for Employee Category =>"+i);
//		}
//		empType.save();
//		EmployeeTypeController.loadEmployeeTypes();
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
	
	public static void editEmployeeType(long empTypeid, String expenseCatName,float preTaxSalary,long[] taxids){
		EmployeeType e = EmployeeType.findById(empTypeid);
		e.name = expenseCatName;
		e.preTaxSalary = preTaxSalary;
		float newTotalPercentage=0;
//		if(taxids == null){
//			flash.error("Please enter at least one tax deduction.");
//			EmployeeTypeController.loadEmployeeTypes();
//		}
		for(long i:taxids){
			Tax tax = Tax.findById(i);
			newTotalPercentage+= tax.percentage;
		}
		e.totalTaxPercentage = newTotalPercentage;
//		e.lastUpdatedBy = Authenticate.getLoggedInUser();
		e.save();
		EmployeeTypeController.loadEmployeeTypes();
		
	}
}
