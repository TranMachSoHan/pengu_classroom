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

    @Column(name = "titles", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "marks", nullable = false)
    private float mark;

    @Column(name = "feedbacks", nullable = false)
    private String feedback;

    @Column(name = "isSubmitted", nullable = false)
    private Boolean isSubmitted ;

    @Column(name = "dueDate", nullable = false)
    private Date dueDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "submission_id")
    private Submission submission;

//    @Column(name="description", nullable = false) img
//    @Column(name="submission", nullable = false) word

    //string

    @ManyToOne
    @JoinColumn(name="enrollment_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Enrollment enrollment;

    public Homework(int id, String title, String description, float mark, String feedback,
                    Boolean isSubmitted, Date dueDate, Submission submission, Enrollment enrollment) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.mark = mark;
        this.feedback = feedback;
        this.isSubmitted = isSubmitted;
        this.dueDate = dueDate;
        this.submission = submission;
        this.enrollment = enrollment;
    }

    public Homework() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        this.title = homework.getTitle();
        this.mark = homework.getMark();
        this.feedback = homework.getFeedback();
        this.isSubmitted = homework.getIsSubmitted();
        this.dueDate = homework.getDueDate();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    //- Description : Word file
//- submission: Word file


}
