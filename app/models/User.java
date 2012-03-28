package models;

import play.data.validation.*;
import play.db.jpa.Model;
import play.libs.Codec;
import javax.persistence.*;

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
	public String firstname;
	@Required
	public String lastname;

	public void setPassword(String password) {
		this.password = password;
		this.passwordHash = Codec.hexMD5(password);
	}

	public static boolean isValidLogin(String username, String password) {
		// return TRUE if there is a single matching username/passwordHash
		return (count("username=? AND PasswordHash=?", username,
				Codec.hexMD5(password)) == 1);
	}
}
