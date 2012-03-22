package models;

import play.db.jpa.Model;
import java.util.*;

import javax.persistence.*;

@Entity
public class Purchase extends Model{
	
	public ExpenseCategory category;
	public Long amount;
	public String purchaseDate;
	
	public Purchase(String categoryName, Long amount, String purchaseDate){
		this.category = new ExpenseCategory(categoryName);
		this.amount = amount;
		this.purchaseDate = purchaseDate;
	}
	
	public String toString(){
		return category.title;
	}

}
