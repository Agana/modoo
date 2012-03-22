package controllers;

import play.*;

import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;


public class PurchaseController extends Controller {
	
	public static void loadPurchase(){
		render("Purchase/purchases.html")
	}
	
	public static void newPurchase(String title, Long amount,
			String purchaseDate) {
		Purchase purchase = new Purchase(title, amount, purchaseDate).save();
		renderJSON(purchase);
	}

}
