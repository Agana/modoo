package controllers;

import play.*;

import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class PayRollController extends Controller {

    //TODO change all methods
    
    static User dummy = new User("a", "a", 1, "admin").save();

    public static void loadPayRoll() {// load employees management page
        render("PayRoll/PayRoll.html");
    }


}
