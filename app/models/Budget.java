package models;

import play.db.jpa.Model;
import java.util.*;

import javax.persistence.*;

@Entity
public class Budget extends Model {

	@ManyToOne
    public ExpenseCategory category;
	
	
    public float budgetAmount;
    
    @OneToOne
    public User createdBy;
    public Date budgetPeriod;
    
    public Budget(float budgetAmount,Date budgetPeriod,ExpenseCategory expenseCat, User createdBy){
    	this.budgetAmount = budgetAmount;
    	this.budgetPeriod = budgetPeriod;
    	this.createdBy = createdBy;
    	this.category = expenseCat;
    }

}