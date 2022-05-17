package rmit.models;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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
        if(!Objects.equals(nickname, "") ){
            this.nickname = nickname;
        }
    }

    public void updateStudent(Student student){
        this.updateAccount(student);
        this.nickname=student.getNickname();
    }
}
