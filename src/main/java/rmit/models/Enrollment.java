package rmit.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Column(name="courseCode", nullable = false)
    private String courseCode;

    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private Collection<Homework> homeworks;

    @ManyToOne(cascade = CascadeType.ALL)
    private Account student;


    public Enrollment() {}

    public Enrollment(String courseCode) {
        this.courseCode = courseCode;
    }

    public Enrollment(int id, Account student, String courseCode, Collection<Homework> homeworks) {
        this.id = id;
        this.student = student;
        this.courseCode = courseCode;
        this.homeworks = homeworks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getStudent() {
        return student;
    }

    public void setStudent(Account student) {
        this.student = student;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Collection<Homework> getHomework() {
        return homeworks;
    }


    public void setHomeworks(Collection<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    public void updateEnrollment(Enrollment enrollment) {
        this.id = enrollment.getId();
        this.student = enrollment.getStudent();
        this.courseCode = enrollment.getCourseCode();
        this.homeworks = enrollment.getHomework();
    }
}