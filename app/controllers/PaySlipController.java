package controllers;

import play.*;

import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class PaySlipController extends Controller {

	public static void index() {
		List<Employee> employees = Employee.findAll();
		List<EmployeeType> employeeTypes = EmployeeType.findAll();
		List<PaySlip> unIssuedSlips = PaySlip.findAll();
		render("PaySlip/payslips.html", employeeTypes, unIssuedSlips);
	}

	public static void loadPaySlips(long employeeTypeId, String year, int month) {
		
		List<EmployeeType> employeeTypes = EmployeeType.findAll();
		List<PaySlip> paidOutSlips = PaySlip.find("byRecepientType_idAndYearAndMonth", employeeTypeId, year, month).fetch();
		List<PaySlip> unIssuedSlips = PaySlip.buildUnissuedSlips(paidOutSlips); //pass the list of paid out slips to the method.
		render("PaySlip/payslips.html", unIssuedSlips, employeeTypes);
		
	}

	public static void newPaySlip(long employeeTypeId, int year, int month) {

		EmployeeType typeToPay = EmployeeType.findById(employeeTypeId);
		
		year = Calendar.getInstance().YEAR;
		System.out.println("Year: " + year);
		month = Calendar.getInstance().MONTH;
		System.out.println("Month: " + month);
		String thisMonth = "";
		
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		
		for(int i = 0; i<=months.length; i++){
			if (i == month){
				thisMonth = months[i];
			}
		}
		
		System.out.println("Month: "+month);
		
		if (PaySlip.count()==0){
		 PaySlip unIssuedSlip = new PaySlip(typeToPay, year, thisMonth).save();
		}
		else{
			PaySlip alreadyThere = PaySlip.find("order by id desc").first();
			flash.error("There is already a pay slip for %s. Please revoke it first.", alreadyThere.thisMonth);
		}
		 
		 
		
		
		PaySlipController.index();
	}

}
