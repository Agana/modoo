package models;

import play.db.jpa.Model;
import java.util.*;

import javax.persistence.*;

@Entity
public class Supplier extends Model {

    public String supplierName;
    @OneToMany(mappedBy = "supplier")
    public List<Purchase> purchasesFromThisSupplier;

    @OneToOne
    public User createdBy;
    
    public String location;
    
    public Supplier(String supplerName, String location,User createdby) {
        this.supplierName = supplerName;
        this.location=location;
        this.createdBy = createdby;
    }
    
    @Override
    public String toString(){
    	return this.supplierName;
    }
}
