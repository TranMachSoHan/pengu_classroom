package rmit.models;

import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "average_mark", nullable = false)
    private float average_mark;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @OneToOne
    @JoinColumn(name="user_id")
    private Account user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_enrollment", //Tạo ra một join Table tên là "address_person"
            joinColumns = @JoinColumn(name = "student_id"),  // Trong đó, khóa ngoại chính là address_id trỏ tới class hiện tại (student)
            inverseJoinColumns = @JoinColumn(name = "course_id") //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới (Course)
    )
    private Collection<Course> courseCollection;

    public Student(int id, float average_mark, String nickname, Account user, Collection<Course> courseCollection) {
        this.id = id;
        this.average_mark = average_mark;
        this.nickname = nickname;
        this.user = user;
        this.courseCollection = courseCollection;
    }

    public Student(Account user) {
        this.user = user;
    }

    public Student() {

    }

    @Override
    public String toString(){
        String info = "";
        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("nickname",this.nickname );
        jsonInfo.put("average mark",this.average_mark );
        JSONArray courseArray = new JSONArray();
        if(this.courseCollection != null && courseCollection.size() >0){
            this.courseCollection.forEach(course -> {
                JSONObject subCourse = new JSONObject();
                subCourse.put("name", course.getTitle());
                courseArray.put(subCourse);
            });
        }
        jsonInfo.put("courses", courseArray);
        info = jsonInfo.toString();
        return info;
    }
}
