package rmit.models;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "students")

@JsonIgnoreProperties({"enrollmentList"})
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Student extends Account {

    @Column(name = "average_mark", nullable = true)
    private float average_mark;

    @Column(name = "nickname", nullable = true)
    private String nickname;

    @Column(name = "student_id", nullable = true)
    private String studentId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Collection<Enrollment> enrollments;


    public Student(int id, String username, String password, String profile_picture, float average_mark, String nickname, List<Enrollment> enrollmentList, String studentId) {
//        super(id, username, password, profile_picture, ERole.STUDENT);

        this.average_mark = average_mark;
        this.nickname = nickname;
        this.enrollments = enrollments;
        this.studentId = studentId;
    }

    public Student() {
    }

    public Student(String username, String password,String nickname ) {
        super(username, password);
        this.setRoles(ERole.STUDENT);
        this.nickname = nickname;
    }
}
