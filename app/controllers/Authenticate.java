package controllers;

import play.data.validation.Valid;
import play.mvc.Controller;
import models.*;

public class Authenticate extends Controller {

	private static void doLoginLogic(String username) {
		session.put("user", username);
	}

	public static void register() {
		render(); //renders the registration page
	}

	public static void doRegister(@Valid User user) { //receives form data
		// if there are errors, redisplay the registration form,
		// otherwise save the user
		
		if (!user.validateAndSave()) {
			params.flash();
			validation.keep();
			register();
		}
		// if no errors, log the user in and redirect to the index page
		doLoginLogic(user.username);
		Application.start();
	}
	
	public static User getLoggedInUser() {
		return User.find("byUsername", session.get("user")).first();
		}
	
	
	public static void login() {
		render();
		}
	
	public static void doLogin(String username, String password) {
		if (User.isValidLogin(username, password)) {
		doLoginLogic(username);
		Application.index();
		} else {
			validation.addError("username",
			"Username/Password combination was incorrect");
			validation.keep();
			login();
			}
			}
			public static void logout() {
			session.remove("user");
			Application.index();
			}


			

}
