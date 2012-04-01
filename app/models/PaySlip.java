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
	public float preTaxSalary;
	public float afterTaxAmount;
	public int year;
	public int month;
	public float totalTax;

	// create a payslip for a recepient
	public PaySlip(Employee payee, int year, int month,EmployeeType empType) { 
		recepient = payee;
		this.preTaxSalary = recepient.type.preTaxSalary;
		this.year = year;
		this.month = month;
		this.issueDate = new Date();
		this.preTaxSalary = empType.preTaxSalary;
		System.out.println("Pre Tax income"+this.preTaxSalary); 
		this.totalTax = this.preTaxSalary * (empType.totalTaxPercentage/100);
		System.out.println("total Tax"+empType.totalTaxPercentage);
		this.afterTaxAmount = empType.preTaxSalary - totalTax;
	}

	@Override
	public String toString() {
		return this.recepient.toString();
	}
}
