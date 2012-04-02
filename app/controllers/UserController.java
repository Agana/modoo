package controllers;

import play.*;

import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class UserController extends Controller {

    public static void loadUsers() {// load employees management page
        render("Authenticate/register.html");
    }
    
    public static void deleteUsers(){
    	
    }

//    public static void newUser(String username, String password,
//            int employeeId, String authorization) {
//        User user = new User(username, password, employeeId).save();
//        renderJSON(user);
//    }

}
