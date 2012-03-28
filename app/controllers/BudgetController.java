package controllers;

import play.*;

import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;


public class BudgetController extends Controller {

	public static void loadBudgets(){
		List<Budget> budgets = Budget.findAll();
		List<ExpenseCategory> expenseCats = ExpenseCategory.findAll();
		render("Budget/budgets.html", budgets,expenseCats);
	}
	
	public static void newBudget(float budget,Date budget_date,long expenseCat){
		ExpenseCategory expenseCatobj = ExpenseCategory.findById(expenseCat);
		Budget budgetObj = new Budget(budget,budget_date,expenseCatobj);
		budgetObj.save();
		BudgetController.loadBudgets();
	}
	
	public static void deleteBudget(long[] budgetid){
		String name="";
		try{
			for(long i:budgetid){
				Budget bObj = Budget.findById(i);
				name = bObj.category.categoryName;
				bObj.delete();
			}
		}catch(Exception ex){
			flash.error("%s budget cannot be deleted because it is in use", name);
		}finally{
			BudgetController.loadBudgets();
		}
	}
}
