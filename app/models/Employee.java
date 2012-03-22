package models;

import java.util.Date;
import javax.persistence.*;

import org.hibernate.dialect.FirebirdDialect;

import play.db.jpa.Model;
import play.*;
import java.util.*;

@Entity
public class Employee extends Model {

	public String first_name;
	public String other_name;
	public String last_name;
	public String full_name = first_name + " " + last_name;
	public String sex;
	public String specialty;
	public String employeeType;
	public Date date_hired;
	public Date date_fired;
	public Date registration_date;
	public boolean fired;
	
	public String authorization;
	public String username;
	public String password;

	public Employee(String first_name, String other_name, String last_name,
			String sex, String employee_type, Date date_hired,
			Date date_fired, Date registration_date) {

		this.first_name = first_name;
		this.other_name = other_name;
		this.last_name = last_name;
		this.sex = sex;
		this.employeeType = employee_type;
		this.date_fired = date_fired;
		this.date_hired = date_hired;
		this.registration_date = registration_date;
	}

	public Employee(String first_name, String last_name, String sex, String specialty) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.sex = sex;
		this.specialty=specialty;
		this.full_name = first_name + " " + last_name;
		this.authorization = authorization;
		this.username= username;
		this.password = password;
	}
	
	

	public String toString() {
		return first_name;
	}

}