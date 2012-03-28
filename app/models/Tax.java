package models;

import play.data.validation.Required;
import play.db.jpa.Model;
import javax.persistence.*;
import play.*;
import java.util.*;

@Entity
public class Tax extends Model {
    //TODO controller for this class

    public String taxName;
    
    @ManyToOne
    public User createdBy;
    
    public float percentage;

    public Tax(String taxName, float percentage) {
        this.taxName = taxName;
        this.createdBy = createdBy;
        this.percentage = percentage;
    }
    
    @Override
    public String toString(){
    	return taxName;
    }
}
