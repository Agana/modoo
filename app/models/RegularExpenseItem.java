package models;

import play.db.jpa.Model;
import java.util.*;

import javax.persistence.*;

@Entity
public class RegularExpenseItem extends Model {

    public String item_name;
    @ManyToOne
    public User created_by;
    @ManyToOne
    public User lastUpdatedBy;
    @OneToMany
    public List<Purchase> purchases;
    @OneToOne
    public ExpenseCategory item_category;
    
    public String category_name;
    
//  @OneToOne
//  public Supplier supplier;
    

    public RegularExpenseItem(String item_name,ExpenseCategory expenseCategory, String category_name) {
        this.item_name = item_name;
        this.item_category = expenseCategory;
        this.category_name = category_name;
    }
    
    @Override
    public String toString(){
    	return item_name;
    }
    
}