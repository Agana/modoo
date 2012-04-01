package models;

import java.util.*;
import play.*;
import play.db.jpa.*;
import javax.persistence.*;

@Entity
public class EmployeeType extends Model {
	// TODO controller for this class

	public String name;
	@ManyToOne
	public User createdBy;
	@ManyToOne
	public User lastUpdatedBy;
	public float preTaxSalary;
	public float totalTaxPercentage;
	
	public EmployeeType(String name, float preTaxSalary,float totalTaxPercentage) {
		this.name = name;
		this.preTaxSalary = preTaxSalary;
		this.totalTaxPercentage = totalTaxPercentage;
	}

	public EmployeeType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
