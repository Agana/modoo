package controllers;

import play.*;

import play.db.jpa.JPA;
import play.mvc.*;

import java.io.File;
import java.util.*;

import javax.persistence.Query;

import models.*;

public class PurchaseCategoryController extends Controller {

	
    public static void loadCategories() {
    	List<RegularExpenseItem> regulars = RegularExpenseItem.findAll();
    	List<ExpenseCategory> expenseCats = ExpenseCategory.findAll();
        render("ExpenseCategory/categories.html", regulars,expenseCats);
    }

    public static void newCategory(String categoryName) {
    	
    	//TODO how do we take items from a dropdown strings or id?
    	System.out.println(categoryName);
    	
        ExpenseCategory category = new ExpenseCategory(categoryName);
        category.save();
        PurchaseCategoryController.loadCategories();
    }
    
    public static void deleteCategory(long[] categoryid){
    	String name="";
    	try{
	    	for(long i:categoryid){
	        	ExpenseCategory expobj =ExpenseCategory.findById(i);
	        	name = expobj.categoryName;
	        	expobj.delete();   		
	    	}
    	}catch(Exception ex){
    		flash.error("%s can't be deleted it is in use", name);
    	}finally{
    		PurchaseCategoryController.loadCategories();
    	}
    }
    
	public static void editCategory(long categoryid,String categoryName){
    	ExpenseCategory category = ExpenseCategory.findById(categoryid);
    	category.categoryName = categoryName;
    	category.lastUpdatedBy = Authenticate.getLoggedInUser();
    	category.save();
    	
    	PurchaseCategoryController.loadCategories();
    }
}
