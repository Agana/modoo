package controllers;

import java.util.List;

import play.data.validation.Valid;
import play.mvc.Controller;
import models.*;

public class Authenticate extends Controller {

	private static void doLoginLogic(String username) {
		session.put("user", username);
		render("Application/start.html");
	}

	public static void register() {
		List<Employee> employees = Employee.findAll();
		render(employees); //renders the registration page
	}

	public static User newUser;
	public static void takeRegistrationDetails(long employeeId, String username, String password){
		Employee userEmp = Employee.findById(employeeId);
		System.out.println("Found's name " + userEmp.first_name);
		System.out.println("Username: "+ username);
		System.out.println("Password: "+ password);
		newUser = new User(userEmp.id, username, userEmp.email, password, userEmp.first_name, userEmp.last_name);
		System.out.println("New user's first name: " + newUser.firstname);
		Authenticate.doRegister(newUser);
	}
	public static void doRegister(User user) { //receives form data
		// if there are errors, redisplay the registration form,
		// otherwise save the user
//		Employee emp = Employee.findById(user.employeeId);
		
		if (!user.validateAndSave()) {
			params.flash();
			validation.keep();
			register();
		}
		// if no errors, log the user in and redirect to the index page
		doLoginLogic(user.username);
		
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
