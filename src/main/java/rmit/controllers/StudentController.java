package rmit.controllers;

import com.fasterxml.jackson.databind.node.TextNode;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.*;
import rmit.service.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("students/{id}")
    public ResponseEntity<Account> getStudentById(@PathVariable(value = "id") Integer accountId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(studentService.getStudentById(accountId));
    }

    @GetMapping("students/{id}/get_student_courses")
    public JSONArray getAllStudentCourse(@PathVariable(value = "id") int student_id)
            throws ResourceNotFoundException {
        Collection<Enrollment> enroll = studentService.getEnrollmentByStudent(student_id);
        JSONArray jsonArray = new JSONArray();
        for (Enrollment e : enroll) { //EXAMPLE: 3 ENROLL -> 3 COURSE
            JSONObject jsonObjectCourse = new JSONObject();
            jsonObjectCourse.put("courseTitle", e.getCourse());
            jsonArray.put(jsonObjectCourse);
        }
        return jsonArray;
    }

    @PostMapping("students")
    public Account createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("students/{id}")
    public ResponseEntity<Account> updateStudent(@PathVariable(value = "id") Integer accountId,
                                                 @RequestBody Student student) throws ResourceNotFoundException {
        return ResponseEntity.ok(studentService.updateStudent(accountId, student));
    }

    @DeleteMapping("students/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Integer accountId)
            throws ResourceNotFoundException {
        studentService.deleteStudent(accountId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("students/{student_id}/insert-course-code")
    public ResponseEntity<Course> updateStudentIntoCourse(@PathVariable(value = "student_id") int studentId,
                                        @RequestBody Map<String,String> courseCode)throws ResourceNotFoundException {
        Student student = studentService.getStudentById(studentId);
        System.out.println(courseCode.get("courseCode"));
//        Enrollment enroll = enrollmentService.createEnrollment(accountId);
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        return ResponseEntity.ok().body(courseService.addNewEnrollmentToCourse(courseCode
                .get("courseCode"), enrollment));
    }


    @GetMapping("students/get_timetable/{student_id}")
    public ResponseEntity<List<Map<String, Object>>> getStudentTimetable(@PathVariable(value = "student_id") int accountId)
            throws ResourceNotFoundException {
        Collection<Enrollment> enroll = studentService.getEnrollmentByStudent(accountId);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Enrollment e : enroll) {
            Map<String, Object> response = new HashMap<>();
            int courseId = e.getCourse().getId();
            response.put("courseTitle", e.getCourse().getTitle());
            Collection<Event> events = courseService.getAllEventByCourseId(courseId);
            for (Event ee : events) {
                response.put("nickname", ee.getId());
                response.put("id", ee.getDay());
                response.put("homeworkList", ee.getZone());
            }
            list.add(response);
        }
        return ResponseEntity.ok().body(list);
    }

//    //get all homework by student ID
//    @GetMapping("students/{id}/all-homeworks")
//    public ResponseEntity<List<Collection<Homework>>> getAllHomeworksByStudentID(@PathVariable(value="id") int studentId)
//        throws ResourceNotFoundException {
//        List<Collection<Homework>> response = new ArrayList<>();
//        Student student = studentService.getStudentById(studentId);
//        List<Enrollment> enrollmentList = student.getEnrollmentList();
//        for(Enrollment e : enrollmentList) {
//            response.add(e.getHomeworks());
//        }
//        return ResponseEntity.ok().body(response);
//    }


}
//    @GetMapping("students/{id}/enrollments")
//    public ResponseEntity<Collection<Enrollment>> getStudentEnrollment(@PathVariable(value = "id") Integer accountId
//                     throws ResourceNotFoundException {
//        return ResponseEntity.ok().body(studentService.getEnrollmentByStudent(accountId));
//    }




//events = [
//        {
//        'course_name': 'EnterpriseApplication',
//        'detail_event' :[
//        {
//        'id':1,
//        'day':'MON',
//        'time_zone' : 'I',
//        },
//        {
//        'id':2,
//        'day':'FRI',
//        'time_zone' : 'II',
//        }
//        ],
//        }
//        ]
