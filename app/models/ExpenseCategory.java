package models;

import java.util.*;
import play.*;
import play.db.jpa.*;
import javax.persistence.*;

@Entity
public class ExpenseCategory extends Model {

	public String title;
	public long yearly_budget;
	//Queue<Purchase> expenseList = new LinkedList<Purchase>();

	public ExpenseCategory(String title, long yearly_budget) { //for creating a brand new category
		this.title = title;
		this.yearly_budget = yearly_budget;

	}
	
	public ExpenseCategory(String title){
		this.title = title;
	}
	
	public String toString(){
		return title;
	}

}
