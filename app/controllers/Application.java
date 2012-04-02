package controllers;

import play.*;

import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
    	Authenticate.login();
    }

   
    
    public static void checkEmpty(){
    	List<User> users = null;
    	try{
    	users = User.findAll();
    	}catch(Exception e){
    		flash.error("No users");
    		
    		if(users.isEmpty()){
        		
    	        render("Application/start.html");
    	    	}
    	    	else{
    	    		render("Authenticate/login.html");
    	    	}
    	}
    	
    }
    
    public static void start(){
    	render("Application/start.html");
    }
}