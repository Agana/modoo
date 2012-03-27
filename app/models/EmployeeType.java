package models;

import java.util.*;
import play.*;
import play.db.jpa.*;
import javax.persistence.*;

@Entity
public class EmployeeType extends Model {
    //TODO controller for this class

    public String name;
    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    public List<Employee> employees;
    @OneToMany
    public List<Tax> employeeTaxes;
    @OneToOne
    public User createdBy;
    
    public float salary;

    public EmployeeType(String name,long[] employeetaxid,User createdBy) {
        this.name = name;
        this.createdBy = createdBy;
        for (long i:employeetaxid){
        	Tax tax = Tax.findById(i);
        	this.employeeTaxes.add(tax);
        }
    }

    public EmployeeType(String name) {
        this.name = name;
    }
    
    @Override
    public String toString(){
    	return name;
    }
}
