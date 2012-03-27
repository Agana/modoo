package models;

import play.data.validation.Required;
import play.db.jpa.Model;
import javax.persistence.*;
import play.*;
import java.util.*;

@Entity
public class User extends Model {

    public String username;
    public String password;
    public int employeeId;
    public String authorization;

    public User(@Required String username, @Required String password, @Required int employeeId, @Required String authorization) {
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
        this.authorization = authorization;
    }

    public String toString() {
        return username;
    }
}
