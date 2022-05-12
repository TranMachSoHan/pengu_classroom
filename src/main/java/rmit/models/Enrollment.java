package rmit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@JsonIgnoreProperties({"homeworks","student","course"})
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(name="courseCode", nullable = false)
    private String courseCode;

    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private Collection<Homework> homeworks;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Student student;

    @ManyToOne
    @JoinColumn(name="course_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Course course;

    public Enrollment() {}

    public Enrollment(String courseCode) {
        this.courseCode = courseCode;
    }

    public Enrollment(int id, Student student, String courseCode, Collection<Homework> homeworks) {
        this.id = id;
        this.student = student;
        this.courseCode = courseCode;
        this.homeworks = homeworks;
    }

    public void updateEnrollment(Enrollment enrollment) {
        this.id = enrollment.getId();
        this.student = enrollment.getStudent();
        this.courseCode = enrollment.getCourseCode();
        this.homeworks = enrollment.getHomeworks();
    }
}

