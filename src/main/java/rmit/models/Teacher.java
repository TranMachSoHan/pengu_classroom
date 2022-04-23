package rmit.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "teachers")
public class Teacher extends Account {


    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private Collection<Course> courses;

    public Teacher() {

    }
    public Teacher(int id, String user_name, String password, String profile_picture,Collection<Course> courses) {
        super(id, user_name, password, profile_picture);
        this.courses=courses;
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

}


