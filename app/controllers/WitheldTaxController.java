package controllers;

import java.util.Date;
import java.util.List;

import play.mvc.*;
import models.*;
import play.*;

public class WitheldTaxController extends Controller {
	
	public static void loadWitheldTaxes(){
		//list of all the with holding tax available
		//in the system
		List <WithHeldTax> withHeldTaxList = WithHeldTax.findAll();
		render("WitheldTax/witheldtaxes.html",withHeldTaxList);
	}
	
	public static void newWitheldTax(float totalAmount, float percentage){
		Date year = new Date();
		WithHeldTax witheldTax = new WithHeldTax(totalAmount, percentage, year);
		witheldTax.createdBy = Authenticate.getLoggedInUser();
		witheldTax.save();
		WitheldTaxController.loadWitheldTaxes();
	}

	public static void deleteWitheldTax(long[] taxids){
		for(long i:taxids){
			WithHeldTax taxObj = WithHeldTax.findById(i);
			taxObj.delete();
		}
		WitheldTaxController.loadWitheldTaxes();
	}
}
