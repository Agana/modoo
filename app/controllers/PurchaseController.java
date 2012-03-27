package controllers;

import play.*;

import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class PurchaseController extends Controller {

	static User dummy = new User("a", "a", 1, "admin").save();

	public static void index() {
		render();
	}

	public static void loadPurchases() {
		// list of regular expense items
		List<RegularExpenseItem> regulars = RegularExpenseItem.findAll();
		// list of suppliers from which we make purchases
		List<Supplier> suppliers = Supplier.findAll();
		// list of purchase we have already made
		List<Purchase> purchases = Purchase.findAll();
		render("Purchase/purchases.html", regulars, suppliers, purchases);
	}

	public static void newPurchase(long regularItemId, float amount,
			long expCategoryId, long supplierId, Date purchaseDate, User user) {

		System.out.println("Purchase category amount: " + amount);

		RegularExpenseItem expCat = RegularExpenseItem.findById(regularItemId);
		Supplier supplier = Supplier.findById(supplierId);
		// Supplier supplier = RegularExpenseItem.find("findBy=", params)",
		// params)

		WithHeldTax witheldTax = WithHeldTax.find("order by id desc").first();

		float amountWitheld = 0;
		float amountPaid = amount;

		if (amount >= witheldTax.totalAmount) {
			amountWitheld = (amount * witheldTax.percentage / 100);
			amountPaid -= (amountWitheld); // getting the amount

		}

		Budget budget = Budget.findById(expCat.item_category.id);

		System.out.println("budget" + budget);

		if (amount <= budget.budgetAmount) {

			Purchase purchase = new Purchase(expCat, amount, supplier,
					purchaseDate, dummy);
			purchase.amountPaid = amountPaid;
			purchase.amountWitheld = amountWitheld;

			purchase.save();

			PurchaseController.loadPurchases();
		} else {
			
			flash.error("Sorry, the amount exceeds your budget for %s", expCat.item_category);
			PurchaseController.loadPurchases();

		}
	}

	public static void deletePurchase(long[] purchaseid) {

		for (long i : purchaseid) {
			Purchase pObj = Purchase.findById(i);
			pObj.delete();
		}

		PurchaseController.loadPurchases();
	}
}
