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

    public static void createRegularPurchase(String name, long categoryid, String category_name) {
        ExpenseCategory expenseCat = ExpenseCategory.findById(categoryid);
        category_name = expenseCat.categoryName;
        RegularExpenseItem item = new RegularExpenseItem(name,expenseCat, category_name);
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
    
    public static void editRegularPurchase(long regularpurchaseid, String name){
    	RegularExpenseItem reg = RegularExpenseItem.findById(regularpurchaseid);
    	reg.item_name = name;
    	reg.item_category.id = reg.item_category.id;
    	reg.lastUpdatedBy = Authenticate.getLoggedInUser();
    	reg.save();
    	RegularPurchaseController.loadRegularPurchases();
    }
}
