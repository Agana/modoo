package models;

import play.db.jpa.Model;
import java.util.*;

import javax.persistence.*;

@Entity
public class Purchase extends Model {
	
	@ManyToOne
    public RegularExpenseItem regular_item;
    public float amount;
    public float amountPaid;
    public float amountWitheld;
    public Date purchaseDate;
    @OneToOne
    public Supplier supplier;
    @ManyToOne
    public User createdBy;
    

    public Purchase(RegularExpenseItem item, float amount, Supplier supplier, Date purchaseDate) {
        this.regular_item = item;
        this.amount = amount;
        this.supplier = supplier;
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return regular_item.item_name;
    }
}
