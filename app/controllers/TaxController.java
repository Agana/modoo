package controllers;

import play.*;

import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class TaxController extends Controller {

	public static User dummy = new User("s", "password", 22, "admin").save();
	
    public static void loadTax() {// load employees management page
    	
    	//List contains all the taxes in the system
    	List<Tax> taxes = Tax.findAll();
        render("Tax/taxes.html",taxes);
    }
    
    public static void newTax(String taxName, float percentage, User user) { // new
        // employee
    	System.out.println(taxName);
    	Tax tax = new Tax(taxName, percentage, dummy).save();
    	System.out.println(tax.taxName);
       
        TaxController.loadTax();
    }
    
    public static void deleteTax(long[] taxids){
    	String name="";
    	try{
    		for(long i:taxids){
    			Tax taxobj = Tax.findById(i);
    			name = taxobj.taxName;
    			taxobj.delete();
    		}
    	}catch(Exception ex){
    		flash.error("%s can not be deleted because it is in use", name);
    	}finally{
    		TaxController.loadTax();
    	}
    }

}
