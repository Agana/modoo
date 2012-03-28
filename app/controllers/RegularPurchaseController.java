package controllers;

import play.*;

import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class RegularPurchaseController extends Controller {

    public static void index() {
        render();
    }

    public static void loadRegularPurchases() {
    	//list of the possible categories that an expense can belong to
    	List <ExpenseCategory> expenseCats = ExpenseCategory.findAll();
    	
    	//list of all the RegularExpenseItem that are in the system
    	List<RegularExpenseItem> regExpenseItems = RegularExpenseItem.findAll();
        render("RegularPurchases/RegularPurchases.html",expenseCats,regExpenseItems);
    }

    public static void createRegularPurchase(String name, long categoryids) {
        ExpenseCategory expenseCat = ExpenseCategory.findById(categoryids);
        RegularExpenseItem item = new RegularExpenseItem(name,expenseCat);
        item.created_by = Authenticate.getLoggedInUser();
        item.save();
        RegularPurchaseController.loadRegularPurchases();
    }
    
    public static void deleteRegularPurchase(long[] expenseid){
    	String name ="";
    	try{
    	for(long i:expenseid){
    		RegularExpenseItem rg = RegularExpenseItem.findById(i);
    		name = rg.item_name;
    		rg.delete();
    	}
    	}catch(Exception ex){
    		flash.error("%S can not be delete because it is in use", name);
    	}finally{
    		RegularPurchaseController.loadRegularPurchases();
    	}
    }
}
