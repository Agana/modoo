package models;

import java.util.Date;
import javax.persistence.*;

import org.hibernate.dialect.FirebirdDialect;

import play.db.jpa.Model;
import play.*;
import java.util.*;
import play.data.validation.Required;

@Entity
public class Employee extends Model {

	public String first_name;
	public String middle_name;
	public String last_name;
	public String sex;
	public Date date_hired;
	public Date date_fired;
	// public Date registration_date;
	@ManyToOne
	public EmployeeType type;
	@ManyToOne
	public User createdBy;

	public Employee(@Required String first_name, String middle_name,
			@Required String last_name, @Required String sex,
			@Required long employeeTypeId, Date date_hired, Date date_fired,
			@Required User user) {

		this.first_name = first_name;
		this.last_name = last_name;
		this.middle_name = middle_name;
		this.sex = sex;
		this.type = EmployeeType.findById(employeeTypeId);
		this.date_hired = date_hired;
		this.date_fired = date_fired;
		this.createdBy = user;
	}

	public void setDateHired(Date dateHired) {
		this.date_hired = dateHired;
	}

	public void setDateFired(Date dateFired) {
		this.date_fired = dateFired;
	}

	@Override
	public String toString() { // used by CRUD admin
		return first_name + " " + middle_name + " " + last_name;
	}
}
