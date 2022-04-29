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

@RestController
@RequestMapping("/api/v1/")
public class StudentController {
    @Autowired
    private StudentService studentService;
    private CourseService courseService;

    @GetMapping("students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("students/{id}")
    public ResponseEntity<Account> getStudentById(@PathVariable(value = "id") Integer accountId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(studentService.getStudentById(accountId));
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

    @GetMapping("students/{student_id}/enrollments/courses/events/")
    public JSONArray getEventByStudent(@PathVariable(value = "id") Integer accountId)
            throws ResourceNotFoundException {
        Collection<Enrollment> enroll = studentService.getEnrollmentByStudent(accountId);
        JSONArray jsonArray = new JSONArray();
        for(Enrollment e : enroll){
            int courseId = e.getId();
            Collection<Event> events = courseService.getAllEventByCourseId(courseId);
            for(Event ee : events) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("startTime", ee.getStartTime());
                jsonObject.put("endTime", ee.getEndTime());
                jsonObject.put("day", ee.getDay());
                jsonArray.put(jsonObject);
            }
        }
        return jsonArray;
    }
}
