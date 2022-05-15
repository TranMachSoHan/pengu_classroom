package rmit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Data
@JsonIgnoreProperties({"course"})
@Table(name = "events")
public class Event {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "day", nullable = false)
    private String day;

    @Column(name = "timezone", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TimeZoneType timeZoneType;

    @ManyToOne
    @JoinColumn(name="course_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Course course;
}
