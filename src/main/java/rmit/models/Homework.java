package rmit.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

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

    @Column(name = "submissions")
    private String submissionLink;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "submission_id")
//    private Submission submission;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "enrollment_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Enrollment enrollment;

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
}