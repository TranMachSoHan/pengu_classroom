package rmit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "homeworks")
@JsonIgnoreProperties({"enrollment"})

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

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private HomeworkType homeworkType;

    @Column(name = "feedbacks", nullable = false)
    private String feedback;

    @Column(name = "isSubmitted", nullable = false)
    private Boolean isSubmitted ;

    @Column(name = "isPublished", nullable = false)
    private Boolean isPublished ;

    @Column(name = "isGraded", nullable = false)
    private Boolean isGraded;

    @Column(name = "dueDate", nullable = false)
    private Date dueDate;

    @Column(name = "submissions", nullable = false)
    private String submissionLink;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "submission_id")
//    private Submission submission;


    @ManyToOne
    @JoinColumn(name="enrollment_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Enrollment enrollment;

    public Homework(){}

    public Homework(int id, String title, String description,
                    float mark, HomeworkType homeworkType, String feedback,
                    Boolean isSubmitted, Boolean isPublished, Boolean isGraded,
                    Date dueDate, String submissionLink, Enrollment enrollment) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.mark = mark;
        this.homeworkType = homeworkType;
        this.feedback = feedback;
        this.isSubmitted = isSubmitted;
        this.isPublished = isPublished;
        this.isGraded = isGraded;
        this.dueDate = dueDate;
        this.submissionLink = submissionLink;
        this.enrollment = enrollment;
    }

    public void updateHomework(Homework homework) {
        this.id = homework.getId();
        this.title = homework.getTitle();
        this.description = homework.getDescription();
        this.mark = homework.getMark();
        this.homeworkType = homework.getHomeworkType();
        this.feedback = homework.getFeedback();
        this.isSubmitted = homework.getIsSubmitted();
        this.isPublished = homework.getIsPublished();
        this.isGraded = homework.getIsGraded();
        this.dueDate = homework.getDueDate();
//        this.submission = homework.getSubmission();
        this.enrollment = homework.getEnrollment();
        this.submissionLink = homework.getSubmissionLink();
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public HomeworkType getHomeworkType() {
        return homeworkType;
    }

    public void setHomeworkType(HomeworkType homeworkType) {
        this.homeworkType = homeworkType;
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

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public Boolean getGraded() {
        return isGraded;
    }

    public void setGraded(Boolean graded) {
        isGraded = graded;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

//    public Submission getSubmission() {
//        return submission;
//    }
//
//    public void setSubmission(Submission submission) {
//        this.submission = submission;
//    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public String getSubmissionLink() {
        return submissionLink;
    }

    public void setSubmissionLink(String submissionLink) {
        this.submissionLink = submissionLink;
    }
}
