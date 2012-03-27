package models;

import play.db.jpa.Model;
import java.util.*;

import javax.persistence.*;

@Entity
public class RegularExpenseItem extends Model {

    public String item_name;
    @OneToOne
    public User created_by;
    @OneToMany
    public List<Purchase> purchases;
    @OneToOne
    public ExpenseCategory item_category;
    
//  @OneToOne
//  public Supplier supplier;
    

    public RegularExpenseItem(String item_name,ExpenseCategory expenseCategory, User createdBy) {
        this.item_name = item_name;
        this.item_category = expenseCategory;
        this.created_by = createdBy;
    }
    
    @Override
    public String toString(){
    	return item_name;
    }
    
}