package rmit.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@Table(name = "teachers")
public class Teacher extends Account {


    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private Collection<Course> courses;

    @Column(name = "title", nullable = true)
    private String title;

    public Teacher() {

    }
    public Teacher(int id, String user_name, String password, String profile_picture, Collection<Course> courses) {
//        super(id, user_name, password, profile_picture, ERole.TEACHER);
        this.courses=courses;
    }

    public Teacher(String username, String password, String title) {
        super(username, password);
        this.setRoles(ERole.TEACHER);
        if(!Objects.equals(title, "")){
            this.title = title;
        }
    }

    public void updateTeacher(Teacher teacher){
        this.updateAccount(teacher);
        this.title=teacher.getTitle();
    }
}


