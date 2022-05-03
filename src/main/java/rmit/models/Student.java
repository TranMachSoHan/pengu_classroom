package rmit.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "students")
public class Student extends Account {

    @Column(name = "average_mark", nullable = true)
    private float average_mark;

    @Column(name = "nickname", nullable = true)
    private String nickname;

    @Column(name = "student_id", nullable = true)
    private String studentId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private List<Enrollment> enrollmentList = new ArrayList<>();


    public Student(int id, String username, String password, String profile_picture, float average_mark, String nickname, List<Enrollment> enrollmentList, String studentId) {
        super(id, username, password, profile_picture, Role.STUDENT);
        this.average_mark = average_mark;
        this.nickname = nickname;
        this.enrollmentList = enrollmentList;
        this.studentId = studentId;
    }

    public Student() {
    }


    public List<Enrollment> getEnrollmentList() {
        return enrollmentList;
    }

    public void setEnrollmentList(List<Enrollment> enrollmentList) {
        this.enrollmentList = enrollmentList;
    }

}
