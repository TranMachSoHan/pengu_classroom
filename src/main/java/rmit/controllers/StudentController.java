package rmit.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.*;
import rmit.service.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;
    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private AccountService accountService;

    @GetMapping("students")
//    @PreAuthorize("hasAuthority('STUDENT')")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("students/{id}")
//    @PreAuthorize("hasAuthority('STUDENT')")
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
            response.put("detail_event", detail_event_list);
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

    //view all homeworks of one student in specific course
    @GetMapping("students/{student_id}/courses/{course_id}/all-homeworks")
    public ResponseEntity<Map<String,List<Homework>>> getAllHomeworksByStudentIdAndCourseId(
            @PathVariable(value = "course_id") int courseId,
            @PathVariable(value = "student_id") int studentId) throws ResourceNotFoundException {
        Map<String,List<Homework>> response = new HashMap<>();
        //find the common enrollment in course and student attribute
        Course course = courseService.getCourseById(courseId);
        Student student = studentService.getStudentById(studentId);
        List<Enrollment> commonEnrollment = course.getEnrollments().stream()
                .filter(student.getEnrollments()::contains)
                .collect(Collectors.toList());
        if(commonEnrollment.size() == 0) {
            response.put("futureHomework", new ArrayList<>());
            response.put("pastHomework", new ArrayList<>());
            return ResponseEntity.ok().body(response);
        }
        List<Homework> homeworkList = new ArrayList<>(commonEnrollment.get(0).getHomeworks());
        List<Homework> futureHomeworkList = new ArrayList<>();
        List<Homework> pastHomeworkList = new ArrayList<>();
        for (Homework homework : homeworkList) {
            if(homework.getDueDate().after(new Date()) || homework.getDueDate().equals(new Date())) {
                futureHomeworkList.add(homework);
            } else pastHomeworkList.add(homework);
        }
        response.put("futureHomework", futureHomeworkList);
        response.put("pastHomework",pastHomeworkList);
        return ResponseEntity.ok().body(response);
    }

}