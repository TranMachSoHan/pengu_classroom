package rmit.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "startTime", nullable = false)
    private Date startTime;

    @Column(name = "endTime", nullable = false)
    private Date endTime;

    @Column(name = "courseCode", nullable = false)
    private String courseCode;

    @Column(name = "description", nullable = false)
    private String description;

    public Course(){}
    public Course(String title){
        this.title = title;
    }

    @ManyToOne
    @JoinColumn(name="teacher_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Teacher teacher;

    @ManyToMany(mappedBy = "courseCollection")
    private Collection<Student> studentCollection;

    public void updateCourse(Course course){
        this.startTime = course.getStartTime();
        this.endTime = course.getEndTime();
        this.title = course.getTitle();
        this.courseCode = course.getCourseCode();
        this.description = course.getDescription();
        this.teacher = course.getTeacher();
        this.studentCollection = course.getStudentCollection();
    }
}
