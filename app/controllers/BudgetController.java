package controllers;

import play.*;

import play.data.validation.Required;
import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class BudgetController extends Controller {

	public static void loadBudgets() {
		List<Budget> budgets = Budget.findAll();
		List<ExpenseCategory> expenseCats = ExpenseCategory.findAll();
		render("Budget/budgets.html", budgets, expenseCats);
	}

	public static void newBudget(String name, float budget, @Required Date budget_date, @ Required long expenseCat) {
		if (session.get("user") == null) {
		Authenticate.login();
		}
		
		
			ExpenseCategory expenseCatobj = ExpenseCategory
					.findById(expenseCat);
			name = expenseCatobj.categoryName;
			Budget budgetObj = new Budget(name, budget, budget_date, expenseCatobj);
			budgetObj.createdBy = Authenticate.getLoggedInUser();
			budgetObj.save();
			BudgetController.loadBudgets();
	}

	public static void deleteBudget(long[] budgetid) {
		String name = "";
		try {
			for (long i : budgetid) {
				Budget bObj = Budget.findById(i);
				name = bObj.category.categoryName;
				bObj.delete();
			}
		} catch (Exception ex) {
			flash.error("%s budget cannot be deleted because it is in use",
					name);
		} finally {
			BudgetController.loadBudgets();
		}
	}
	
	public static void editBudget(long budgetid, float budgetAmount){
		System.out.println("Budget ID: "+ budgetid);
    	Budget budget = Budget.findById(budgetid);
    	System.out.println("Budget Returned: "+ budget.budgetAmount);
    	budget.budgetAmount = budgetAmount;
    	budget.lastUpdateBy = Authenticate.getLoggedInUser(); //record who last modified this budget
    	budget.save();
    	
    	BudgetController.loadBudgets();
    }
}
