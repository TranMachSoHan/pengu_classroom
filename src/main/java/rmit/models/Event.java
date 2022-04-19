package rmit.models;

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
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "dayOfTheWeek", nullable = false)
    private String day;

    @Column(name = "time", nullable = false)
    private Date time;

    public Event(){}
    public Event(String day){
        this.day = day;
    }

    @ManyToOne
    @JoinColumn(name="course_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Course course;
}
