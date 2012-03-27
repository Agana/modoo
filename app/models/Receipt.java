package models;

import play.db.jpa.Model;

import java.io.File;
import java.util.*;

import javax.persistence.*;

@Entity
public class Receipt extends Model {

    public String title;
    public File photo;

    public Receipt(String title, File photo) {
        this.title = title;
        this.photo = photo;
    }
}
