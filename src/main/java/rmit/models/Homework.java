package rmit.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Column(name = "marks")
    private Float mark;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date dueDate;

    @Column(name = "submissions")
    private String submissionLink;

    @Column(name= "submissionTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date submissionTime;


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
        this.enrollment = homework.getEnrollment();
        this.submissionLink = homework.getSubmissionLink();
        this.submissionTime = homework.getSubmissionTime();
    }
}