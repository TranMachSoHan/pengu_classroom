package rmit.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "homeworks")
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "courseCode", nullable = false)
    private String courseCode;

    @Column(name = "titles", nullable = false)
    private String title;

    @Column(name = "marks", nullable = false)
    private float mark;

    @Column(name = "feedbacks", nullable = false)
    private String feedback;

    @Column(name = "isSubmitted", nullable = false)
    private Boolean isSubmitted ;

    @Column(name = "dueDate", nullable = false)
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name="enrollment_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Enrollment enrollment;

    public Homework(int id, String courseCode, String title, float mark, String feedback, Boolean isSubmitted, Date dueDate) {
        this.id = id;
        this.courseCode = courseCode;
        this.title = title;
        this.mark = mark;
        this.feedback = feedback;
        this.isSubmitted = isSubmitted;
        this.dueDate = dueDate;
    }

    public Homework() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Boolean getSubmitted() {
        return isSubmitted;
    }

    public void setSubmitted(Boolean submitted) {
        isSubmitted = submitted;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void updateHomework(Homework homework) {
        this.id = homework.getId();
        this.courseCode = homework.getCourseCode();
        this.title = homework.getTitle();
        this.mark = homework.getMark();
        this.feedback = homework.getFeedback();
        this.isSubmitted = homework.getIsSubmitted();
        this.dueDate = homework.getDueDate();
    }

//- Description : Word file
//- submission: Word file


}
