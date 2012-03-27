package models;

import java.util.Date;

import java.util.*;
import play.*;
import play.db.jpa.*;
import javax.persistence.*;

@Entity
public class PaySlip extends Model {

	@ManyToOne
	public Employee recepient;

	public Date issueDate;
	public float preTaxAmount;
	public float afterTaxAmount;
	public int year;
	public String thisMonth;

	@ManyToOne
	public EmployeeType recepientType; // a payslip is issued for each employee
										// type

	@OneToMany
	public List<Tax> taxes;

	public PaySlip(Employee payee) { // create a payslip for a recepient
		recepient = payee;
		List<Tax> taxes = recepient.type.employeeTaxes;
		float totalTax = 0;

		for (Tax t : taxes) { // look through all this employee's taxes
			float tmp = (t.percentage / 100) * recepient.type.salary;
			totalTax += tmp; // total taxes due on this employee
		}
		System.out.println(totalTax);

	}

	public PaySlip(EmployeeType type, int year, String thisMonth) {

		this.year = year;
		this.thisMonth = thisMonth;
		recepientType = type;

		List<Tax> taxes = recepientType.employeeTaxes;
		float totalTax = 0;

		for (Tax t : taxes) {
			float tmp = (t.percentage / 100) * recepientType.salary;
		}
		System.out.println("total tax on this employee type is : " + totalTax);

	}

	public static List<PaySlip> buildUnissuedSlips(List<PaySlip> paidOutSlips) { // generate
		// a
		// list
		// of
		// unissued
		// slips

		List<PaySlip> unIssuedSlips = PaySlip.find("recepient_id not in ? ",
				paidOutSlips).fetch(); // by fetching all slips that are not in
										// the
		// list of paid out slips

		return unIssuedSlips; // here they are.

	}

}
