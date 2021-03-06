package models;

import java.util.*;
import play.*;
import play.db.jpa.*;
import javax.persistence.*;

@Entity
public class ExpenseCategory extends Model {

	
	public String categoryName;
	@ManyToOne
	public User created_by;
	
	@ManyToOne
	public User lastUpdatedBy;
	
	//@OneToMany(mappedBy = "item_category")
//	public List<RegularExpenseItem> regular_items;
	
	@OneToMany(mappedBy="category", cascade=CascadeType.ALL)
	public List<Budget> budget;


	public ExpenseCategory(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return categoryName;
	}
}
