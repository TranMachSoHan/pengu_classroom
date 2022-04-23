package rmit.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "students")
public class Student extends Account {

    @Column(name = "average_mark", nullable = false)
    private float average_mark;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private List<Enrollment> enrollmentList = new ArrayList<>();


    public Student(int id, String username, String password, String profile_picture, float average_mark, String nickname, List<Enrollment> enrollmentList) {
        super(id, username, password, profile_picture, Role.STUDENT);
        this.average_mark = average_mark;
        this.nickname = nickname;
        this.enrollmentList = enrollmentList;
    }

    public Student() {
    }


    public List<Enrollment> getEnrollmentList() {
        return enrollmentList;
    }

    public void setEnrollmentList(List<Enrollment> enrollmentList) {
        this.enrollmentList = enrollmentList;
    }

//    @Override
//    public String toString(){
//        String info = "";
//        JSONObject jsonInfo = new JSONObject();
//        jsonInfo.put("nickname",this.nickname );
//        jsonInfo.put("average mark",this.average_mark );
//        JSONArray courseArray = new JSONArray();
//        if(this.courseCollection != null && courseCollection.size() >0){
//            this.courseCollection.forEach(course -> {
//                JSONObject subCourse = new JSONObject();
//                subCourse.put("name", course.getTitle());
//                courseArray.put(subCourse);
//            });
//        }
//        jsonInfo.put("courses", courseArray);
//        info = jsonInfo.toString();
//        return info;
//    }

}
