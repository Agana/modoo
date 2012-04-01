package controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import models.Employee;
import models.PaySlip;
import play.mvc.Controller;

public class PaySlipController extends Controller {

    public static void index() {
        
        //get calendar to display current month and
        //year
        Calendar c = new GregorianCalendar();
        
        //this list contains the list of all employees that have not been paid for a 
        //particular month and year
        List<Employee> unpaidEmployees = Employee.find("select p from Employee p"
                + " where p.id not in(select c.recepient from PaySlip c where "
                + "year =? and month =?)",c.get(Calendar.YEAR),c.get(Calendar.MONTH)).fetch();
        
        //this list contains the list of all payslips that have been paid for a 
        //particular year and month
        List<PaySlip> paySlips = PaySlip.find("select c from PaySlip c where year"
                + " =? and month =?",c.get(Calendar.YEAR),c.get(Calendar.MONTH)).fetch();
        
        render("PaySlip/payslips.html",unpaidEmployees,paySlips);
    }

    public static void PayEmployees(long[] employeeIds) {
        
        System.out.println(employeeIds.toString());
        
        List<Employee> employees = new ArrayList<Employee>();

        Calendar cal = new GregorianCalendar();

        for(long i:employeeIds){
            employees.add( (Employee) Employee.findById(i));
        }
        
        for(Employee e: employees){
        	System.out.println("tototall tax here =>"+e.type.totalTaxPercentage);
        	new PaySlip(e, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), e.type).save();
        }
        
        PaySlipController.index();
    }
    
    public static void revokePayment(long[] payslipids){
    	
        for(long e: payslipids){
        	PaySlip toRevoke = PaySlip.findById(e);
        	toRevoke.delete();
        	System.out.println("Haha: "+ toRevoke.preTaxSalary);
        }
        
        PaySlipController.index();
    }
}
