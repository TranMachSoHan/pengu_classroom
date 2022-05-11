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

    @Column(name = "day", nullable = false)
    private String day;

    @Column(name = "timezone", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TimeZoneType timeZoneType;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    };

    public String getDay(){
        return day;
    }

    public void setDay(){
        this.day = day;
    }

    public TimeZoneType getZone(){
        return timeZoneType;
    }

    public void setTimeZone(TimeZoneType zone){
        this.timeZoneType = zone;
    }

    @ManyToOne
    @JoinColumn(name="course_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Course course;
}
