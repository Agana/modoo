package models;

import play.data.validation.Email;
import play.data.validation.MinSize;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.Model;
import javax.persistence.*;
import play.*;
import java.util.*;
import play.libs.Codec;

@Entity
public class User extends Model {

	@Required
	@MinSize(6)
	public String username;

	@Required
	@Email
	public String email;

	@Required
	@Password
	@Transient
	public String password;
	public String passwordHash;

	@Required
	public String firstName;
	@Required
	public String lastName;

//removed employeeId because if we have a table of users, who are already known then we don't need to map them to themselves
	//in another table
	

//	public User(@Required String username, @Required String password,
//			int employeeId, String authorization) {
//		this.employeeId = employeeId;
//		this.username = username;
//		this.password = password;
//		this.authorization = authorization;
//	}

	public void setPassword(String password) {
		this.password = password;
		this.passwordHash = Codec.hexMD5(password);
	}

	public static boolean isValidLogin(String username, String password) {
		// return TRUE if there is a single matching username/passwordHash
		return (count("username=? AND PasswordHash=?", username,
				Codec.hexMD5(password)) == 1);
	}

	public String toString() {
		return username;
	}
}
