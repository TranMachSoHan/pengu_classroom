package rmit.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Account account;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private Collection<Course> courses;

    public Teacher(Account account) {
        this.setAccount(account);
    }

    public Teacher(int id, Account account, Collection<Course> courses) {
        this.id = id;
        this.account = account;
        this.courses = courses;
    }

    public Teacher() {
    }

    public void updateTeacher(Teacher teacher) {
        this.id = teacher.getId();
        this.account = teacher.getAccount();
        this.courses = teacher.getCourses();
    }

}
