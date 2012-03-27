package controllers;

import play.*;

import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        String welcome_message = "Welcome to Modoo";
        List<Employee> employees = Employee.find("order by id desc").from(0).fetch(5);
        render(employees);
    }

    public static void uploadPhoto(String title, File photo) {
        Receipt receipt = new Receipt(title, photo).save();
        renderJSON(receipt);
    }
    
    public static void start(){
        render("Application/start.html");
    }
}