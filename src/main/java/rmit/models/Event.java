package rmit.models;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
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
    private timezone zone;

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

    public enum timezone{
        I,
        II,
        III,
        IV,
        V,
        VI,
        VII;
    }

    public timezone getZone(){
        return zone;
    }

    public void setTimeZone(timezone zone){
        this.zone = zone;
    }

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;
}
