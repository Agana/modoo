package models;

import play.db.jpa.*;
import play.*;
import javax.persistence.*;

import java.util.Date;

@Entity
public class WithHeldTax extends Model {

	public float totalAmount;
	public float percentage;
	public Date year;

	public WithHeldTax(float totalAmount, float percentage, Date year) {
		this.totalAmount = totalAmount;
		this.percentage = percentage;
		this.year = year;
	}

}
