package rmit.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@JsonIgnoreProperties({"teacher", "events"})
@Table(name = "courses")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "startTime", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startTime;

    @Column(name = "endTime", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endTime;

    @Column(name = "courseCode", nullable = false)
    private String courseCode;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name="teacher_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Teacher teacher;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Collection<Event> events;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Collection<Enrollment> enrollments;

    public void updateCourse(Course course){
        this.startTime = course.getStartTime();
        this.endTime = course.getEndTime();
        this.title = course.getTitle();
        this.courseCode = course.getCourseCode();
        this.description = course.getDescription();
        this.teacher = course.getTeacher();
    }

    public Course(){}

    public Course(int id, String title, Date startTime, Date endTime, String courseCode,
                  String description, Teacher teacher, Collection<Enrollment> enrollments,
                  Collection<Event> events) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.courseCode = courseCode;
        this.description = description;
        this.teacher = teacher;
        this.enrollments = enrollments;
        this.events = events;
    }

    public void setEvent(Collection<Event> events) {
        this.events = events;
    }
}
