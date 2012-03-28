package controllers;

import play.*;

import play.db.jpa.JPA;
import play.mvc.*;

import java.io.File;
import java.util.*;

import org.hibernate.mapping.Array;

import models.*;

public class PaySlipController extends Controller {

	public static void index() {
		List<Employee> employees = Employee.findAll();
		List<EmployeeType> employeeTypes = EmployeeType.findAll();
		List<PaySlip> issuedSlips = PaySlip.findAll();
		render("PaySlip/payslips.html", employeeTypes, issuedSlips);
	}

	public static void loadPaySlips(long employeeTypeId, String year,
			String month) {

		System.out.println("Year: " + year);
		System.out.println("Month: " + month);
		System.out.println("Id: " + employeeTypeId);

		EmployeeType typeToPay = EmployeeType.findById(employeeTypeId);
		
		// give me every single employee

		List<EmployeeType> employeeTypes = EmployeeType.findAll(); 
		List<PaySlip> paidOutSlips = PaySlip.find("recepientType_id = ? and year like ? and month like ? ",
				employeeTypeId,year,month).fetch();
		
		System.out.println(paidOutSlips);
		
		List<Employee> unpaidEmployees = new ArrayList<Employee>();
		List<EmployeeType> empTypes = new ArrayList<EmployeeType>();

		long[] employeeIds = new long[paidOutSlips.size()];
		long[] employeeTypeIds = new long[paidOutSlips.size()];

		int i = 0;

		for (PaySlip p : paidOutSlips) {
			employeeIds[i] = p.recepient.id;
			employeeTypeIds[i] = p.recepientType.id;
		}

		if (paidOutSlips.isEmpty()) {
			unpaidEmployees = Employee.findAll();
		} else {
			unpaidEmployees = Employee.find(
					"id not in ? and recepientType_id not in ?",
					employeeIds, employeeTypeIds).fetch();
		}

		List<PaySlip> unIssuedSlips = PaySlip.buildUnissuedSlips(paidOutSlips); // pass
																				// the
																				// list
																				// of
																				// paid
																				// out
																				// slips
																				// to
																				// the
																				// method.
		render("PaySlip/payslips.html", unIssuedSlips, employeeTypes,
				unpaidEmployees,year,month);

	}

	public static void PayEmployees(long[] employeeIds,String year,String month) {
		
		for (long i : employeeIds) {
			Employee payee = Employee.findById(i);			// pick out each employee
			System.out.println("Found: " + payee.first_name);
			PaySlip issuedSlip = new PaySlip(payee,year,month).save();
		}
		
//		PaySlipController.loadPaidEmployeeTypes(payee.id);

	}

//	public static void loadPaidEmployees(long employeeTypeId) {
//
//		List<Employee> paidEmployees = PaySlip.find("byRecepient_id", employeeId).fetch();
//		render("PaySlip/payslips.html", paidEmployees);
//
//	}

	// public static void newPaySlip(long employeeTypeId, String year, String
	// month) {
	//
	//
	//
	// // year = Calendar.getInstance().YEAR;
	// System.out.println("Year: " + year);
	// // month = Calendar.getInstance().MONTH;
	// System.out.println("Month: " + month);
	// String thisMonth = "";
	//
	// String[] months = {"January", "February", "March", "April", "May",
	// "June", "July", "August", "September", "October", "November",
	// "December"};
	//
	// System.out.println("Month: "+month);
	//
	// if (PaySlip.count()==0){
	// PaySlip unIssuedSlip = new PaySlip(typeToPay, year, thisMonth).save();
	// }
	// else{
	// PaySlip alreadyThere = PaySlip.find("order by id desc").first();
	// flash.error("There is already a pay slip for %s. Please revoke it first.",
	// alreadyThere.month);
	// }
	//
	//
	//
	//
	// PaySlipController.index();
	// }

}
