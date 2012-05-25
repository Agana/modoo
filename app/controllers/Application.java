package controllers;

import play.*;

import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
    	start();
    }

   
    
    public static void checkEmpty(){
    	
    	
    		List<User> users = User.findAll();
    	
    	
    		if(users.isEmpty() || users == null){
        		
    	        render("Application/start.html");
    	    	}
    	    	else{
    	    		render("Authenticate/login.html");
    	    	}
    	
    	
    }
    
    public static void start(){
    	render("Application/start.html");
    }
}
