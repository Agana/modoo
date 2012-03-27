package models;

import java.util.*;
import play.*;
import play.db.jpa.*;
import javax.persistence.*;

@Entity
public class ExpenseCategory extends Model {

	@Column(unique=true)
	public String categoryName;
	@OneToOne
	public User created_by;
	
	//@OneToMany(mappedBy = "item_category")
//	public List<RegularExpenseItem> regular_items;
	
	@OneToMany(mappedBy="category")
	public List<Budget> budget;


	public ExpenseCategory(String categoryName, User createdBy) {
		this.categoryName = categoryName;
		this.created_by = createdBy;
	}

	@Override
	public String toString() {
		return categoryName;
	}
}
