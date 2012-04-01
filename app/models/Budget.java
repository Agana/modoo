package models;

import play.db.jpa.Model;
import java.util.*;

import javax.persistence.*;

@Entity
public class Budget extends Model {

	@ManyToOne
    public ExpenseCategory category;
	
	public String name;
	
    public float budgetAmount;
    
    @ManyToOne
    public User createdBy;
    
    @ManyToOne
	public User lastUpdateBy;
    
    public Date budgetPeriod;
    
    public Budget(String name, float budgetAmount,Date budgetPeriod,ExpenseCategory expenseCat){
    	this.name = name;
    	this.budgetAmount = budgetAmount;
    	this.budgetPeriod = budgetPeriod;
    	this.category = expenseCat;
    }
    
    @Override
    public String toString(){
    	return name;
    }

}