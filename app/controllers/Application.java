package controllers;

import play.*;

import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class Application extends Controller {

	public static void index() {
		String welcome_message = "Welcome to Modoo";
		List<Employee> employees = Employee.find("order by id desc").from(0)
				.fetch(5);
		render(employees);
	}

	// new purchse

	public static void newPurchase(String title, Long amount,
			String purchaseDate) {
		Purchase purchase = new Purchase(title, amount, purchaseDate).save();
		renderJSON(purchase);
	}


	public static void uploadPhoto(String title, File photo) {
		Receipt receipt = new Receipt(title, photo).save();
		renderJSON(receipt);
	}

}