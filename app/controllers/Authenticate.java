package controllers;

import java.util.List;

import play.data.validation.MinSize;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.mvc.Controller;
import models.*;

public class Authenticate extends Controller {

	private static void doLoginLogic(String username) {
		session.put("user", username);
		Authenticate.register();
	}

	public static void register() {
		
		List<Employee> employees = Employee.findAll();
		
		try{
		List<User> users = User.findAll();
		render("Authenticate/register.html", employees, users); //renders the registration page
		}catch(Exception e){
			flash.error("No users.");
			render("Authenticate/register.html", employees); //renders the registration page
		}
		
	}

	public static User newUser;
	
	public static void takeRegistrationDetails(long employeeId, @Valid User user){
		Employee userEmp = Employee.findById(employeeId);
		System.out.println("User's id: " + employeeId);
		System.out.println("Found's name " + userEmp.first_name);
		user = new User(userEmp, user.username, userEmp.email, user.password, userEmp.first_name, userEmp.last_name);
		if(!user.validateAndSave()){
			params.flash();
			validation.keep();
			register();
		}
		doLoginLogic(user.username);
		
//		Authenticate.doRegister(newUser);
	}
	
	
	public static void doRegister(@Valid User user) { //receives form data
		// if there are errors, redisplay the registration form,
		// otherwise save the user
//		Employee emp = Employee.findById(user.employeeId);
		
		if (user == null) {
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
	
	public static void deleteUsers(long[] userids) {

		for (long i : userids) {
			User user = User.findById(i);
			user.delete();
		}
		Authenticate.register();
	}


			

}
