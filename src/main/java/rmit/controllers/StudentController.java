package rmit.controllers;

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

    @GetMapping("students/{id}/get-student-courses")
    public ResponseEntity<List<Map<String, Object>>> getStudentCourses(@PathVariable(value = "id") int accountId)
            throws ResourceNotFoundException {
        Collection<Enrollment> enroll = studentService.getEnrollmentByStudent(accountId);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Enrollment e : enroll) {
            Map<String, Object> response = new HashMap<>();
            int courseId = e.getCourse().getId();
            response.put("course", e.getCourse());
            list.add(response);
        }
        return ResponseEntity.ok().body(list);
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

    @GetMapping("students/get-timetable/{student_id}")
    public ResponseEntity<List<Map<String, Object>>> getStudentTimetable(@PathVariable(value = "student_id") int accountId)
            throws ResourceNotFoundException {
        Collection<Enrollment> enroll = studentService.getEnrollmentByStudent(accountId);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Enrollment e : enroll) {
            Map<String, Object> response = new HashMap<>();
            int courseId = e.getCourse().getId();
            response.put("course_name", e.getCourse().getTitle());

            Collection<Event> events = courseService.getAllEventByCourseId(courseId);
            List<Map<String, Object>> detail_event_list = new ArrayList<>();
            for (Event ee : events) {
                Map<String, Object> responseEvent = new HashMap<>();
                responseEvent.put("id", ee.getId());
                responseEvent.put("day", ee.getDay());
                responseEvent.put("timeZoneType", ee.getTimeZoneType());
                detail_event_list.add(responseEvent);
            }
            response.put("detail_event",detail_event_list );
            list.add(response);
        }
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("students/{student_id}/student-insert-course-code")
    public void updateStudentIntoCourse(@PathVariable(value = "student_id") int accountId,
                                          @RequestBody Object courseCode)throws ResourceNotFoundException {
        Student student = studentService.getStudentById(accountId);
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourseCode(courseCode.toString());
        Collection<Course> course = courseService.getAllCourses();
        for (Course c : course) {
            if (c.getCourseCode() == courseCode) {
                enrollment.setCourse(c);
                c.getEnrollments().add(enrollment);
            }
        }
    }

}
//    @GetMapping("students/{id}/enrollments")
//    public ResponseEntity<Collection<Enrollment>> getStudentEnrollment(@PathVariable(value = "id") Integer accountId
//                     throws ResourceNotFoundException {
//        return ResponseEntity.ok().body(studentService.getEnrollmentByStudent(accountId));
//    }