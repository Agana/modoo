package controllers;

import play.*;

import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class UserController extends Controller {
	
	public static void loadUsers() {// load employees management page
		render("User/users.html");
	}
	
	public static void newUser(String username, String password,
			int employeeId, String authorization) {
		User user = new User(username, password, employeeId, authorization)
				.save();
		renderJSON(user);
	}
	
	public static void changeStatus(Long id, boolean fired) {
		User user = User.findById(id);
		if(fired){ user.authorization = "notAdmin";} else
		user.authorization = "admin";
		user.save();
		renderJSON(user);
	}
	

}
