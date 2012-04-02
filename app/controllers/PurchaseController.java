package controllers;

import play.*;

import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class PurchaseController extends Controller {


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
			long expCategoryId, long supplierId, String recTitle, File photo, Date purchaseDate) {

		System.out.println("Purchase category amount: " + amount);

		RegularExpenseItem expCat = RegularExpenseItem.findById(regularItemId);
		System.out.println("expCat id: " + expCat.id);
		Supplier supplier = Supplier.findById(supplierId);
		// Supplier supplier = RegularExpenseItem.find("findBy=", params)",
		// params)

		WithHeldTax witheldTax = WithHeldTax.find("order by id desc").first();

		float amountWitheld = 0;
		float amountPaid = amount;
		
		Budget budget = Budget.findById(expCat.id);
		if (budget == null){
			flash.error("Please create a budget first");
			PurchaseController.loadPurchases();
		}else{
			
			if (witheldTax == null){
				flash.error("Please set the witheld tax first");
				PurchaseController.loadPurchases();
			}else{
				
				float difference = budget.budgetAmount - amount;
			if (amount <= budget.budgetAmount) {

				if (amount >= witheldTax.totalAmount) {
					amountWitheld = (amount * witheldTax.percentage / 100);
					amountPaid -= (amountWitheld); // getting the amount

				}
				
				
				Purchase purchase = new Purchase(expCat, amount, supplier,
						purchaseDate);
				
				purchase.amountPaid = amountPaid;
				purchase.amountWitheld = amountWitheld;
				purchase.createdBy = Authenticate.getLoggedInUser();

				purchase.save();
				
				
				Receipt receipt = new Receipt(recTitle, photo);
				receipt.createdBy = Authenticate.getLoggedInUser();
				receipt.save();

				PurchaseController.loadPurchases();
			} else {
				
				flash.error("Sorry, the amount exceeds your budget for %s", expCat.item_name);
				PurchaseController.loadPurchases();

			}
			PurchaseController.loadPurchases();
			}
		}
	}
		
		public static void editPurchase(long purchaseid, long regularItemId, float amount,
				long expCategoryId, long supplierId, String recTitle, File photo, Date purchaseDate) {

			System.out.println("Purchase category amount: " + amount);

			RegularExpenseItem expCat = RegularExpenseItem.findById(regularItemId);
			Supplier supplier = Supplier.findById(supplierId);
			// Supplier supplier = RegularExpenseItem.find("findBy=", params)",
			// params)
			Purchase purchase = Purchase.findById(purchaseid);
			purchase.regular_item.id = regularItemId;
			purchase.amount = amount;
			purchase.regular_item.item_category.id = expCategoryId;
			purchase.supplier.id = supplierId;
			purchase.purchaseDate = purchaseDate;
			
			WithHeldTax witheldTax = WithHeldTax.find("order by id desc").first();

			float amountWitheld = 0;
			float amountPaid = amount;
			
			Budget budget = Budget.findById(expCat.item_category.id);
			if (budget == null){
				flash.error("Please create a budget first");
				PurchaseController.loadPurchases();
			}else{
				
				if (witheldTax == null){
					flash.error("Please set the witheld tax first");
					PurchaseController.loadPurchases();
				}else{
				if (amount <= budget.budgetAmount) {

					if (amount >= witheldTax.totalAmount) {
						amountWitheld = (amount * witheldTax.percentage / 100);
						amountPaid -= (amountWitheld); // getting the amount

					}
					
					
					purchase.amountPaid = amountPaid;
					purchase.amountWitheld = amountWitheld;
					purchase.createdBy = Authenticate.getLoggedInUser();

					purchase.save();
					
					
					Receipt receipt = new Receipt(recTitle, photo);
					receipt.createdBy = Authenticate.getLoggedInUser();
					receipt.save();

					PurchaseController.loadPurchases();
				} else {
					
					flash.error("Sorry, the amount exceeds your budget for %s", expCat.item_category);
					PurchaseController.loadPurchases();

				}
				PurchaseController.loadPurchases();
				}
			}
			
		

		

		

		

		
		
	}

	public static void deletePurchase(long[] purchaseid) {

		for (long i : purchaseid) {
			Purchase pObj = Purchase.findById(i);
			pObj.delete();
		}

		PurchaseController.loadPurchases();
	}
	
	 public static void uploadPhoto(String title, File photo) {
	        Receipt receipt = new Receipt(title, photo).save();
	        renderJSON(receipt);
	    }
	
	
}
