package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private Date startTime;
    private Date endTime;
    private String courseCode;
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
