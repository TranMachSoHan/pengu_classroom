package rmit.models;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
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

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    };

    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDay(){
        return day;
    }

    public void setDay(){
        this.day = day;
    }

    @ManyToOne
    @JoinColumn(name="course_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Course course;
}
