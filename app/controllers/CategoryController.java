package controllers;

import play.*;

import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class CategoryController extends Controller{
	
	public static void loadCategories(){
		
		render("ExpenseCategory/categories.html");
	}
	
	public static void newCategory(String title, long yearly_budget){
		ExpenseCategory category = new ExpenseCategory(title, yearly_budget).save();
		renderJSON(category);
	}

}
