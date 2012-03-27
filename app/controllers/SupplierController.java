package controllers;

import play.*;

import play.mvc.*;

import java.io.File;
import java.util.*;

import models.*;

public class SupplierController extends Controller {

    public static void loadSuppliers() {// load employees management page
    	
    	//list contains a list of all the suppliers that are in the system
    	List<Supplier> suppliers = Supplier.findAll();
        render("Supplier/suppliers.html",suppliers);
    }
    
    public static void newSupplier(String supplierName, String location){
    	User user = User.findById(new Long(1));
    	Supplier supplier = new Supplier(supplierName, location,user).save();
    	SupplierController.loadSuppliers();
    }

    public static void deleteSupplier(long[] supplierids){
    	String name="";
    	try{
    		for(long i:supplierids){
    			Supplier sObj = Supplier.findById(i);
    			name = sObj.supplierName;
    			sObj.delete();
    		}
    	}catch(Exception ex){
    		flash.error("%s can not be delete because it is in use",name);
    	}finally{
    		SupplierController.loadSuppliers();
    	}
    }
}
