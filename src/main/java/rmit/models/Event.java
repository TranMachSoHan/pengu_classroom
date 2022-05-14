package rmit.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "events")
public class Event {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "startTime", nullable = false)
    private Date startTime;

    @Column(name = "endTime", nullable = false)
    private Date endTime;

    @Column(name = "day", nullable = false)
    private String day;

    @Column(name = "timezone", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private timezone zone;

    @ManyToOne
    @JoinColumn(name="course_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Course course;


    public enum timezone{
        I,
        II,
        III,
        IV,
        V,
        VI,
        VII;
    }



}
