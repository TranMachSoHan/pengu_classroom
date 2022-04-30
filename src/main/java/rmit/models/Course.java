package rmit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@JsonIgnoreProperties({"teacher"})
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

    public Collection<Event> getEvent() {
        return events;
    }

    public void setEvent(Collection<Event> events) {
        this.events = events;
    }

    @ManyToOne
    @JoinColumn(name="teacher_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Teacher teacher;

    public void updateCourse(Course course){
        this.startTime = course.getStartTime();
        this.endTime = course.getEndTime();
        this.title = course.getTitle();
        this.courseCode = course.getCourseCode();
        this.description = course.getDescription();
        this.teacher = course.getTeacher();
//        this.studentCollection = course.getStudentCollection();
    }

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Collection<Enrollment> enrollments;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Collection<Event> events;
}
