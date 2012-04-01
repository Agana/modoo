package models;

import play.db.jpa.Model;

import java.io.File;
import java.util.*;

import javax.persistence.*;

import com.mysql.jdbc.Blob;

@Entity
public class Receipt extends Model {

   
	public String title;
    public File receipt;
    @ManyToOne
    public User createdBy;
    
    

    public Receipt(String title, File receipt) {
        this.title = title;
        this.receipt = receipt;
    }
}
