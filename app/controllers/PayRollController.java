package controllers;

import play.*;

import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class PayRollController extends Controller {

    //TODO change all methods
    

    public static void loadPayRoll() {// load employees management page
        render("PayRoll/PayRoll.html");
    }


}
