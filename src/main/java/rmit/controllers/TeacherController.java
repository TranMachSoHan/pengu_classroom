package rmit.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.ui.Model;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.repositories.*;
import rmit.service.CourseService;
import rmit.service.TeacherService;

import java.util.*;

@RestController
@RequestMapping("/api/v1/")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    //get teacher
    @GetMapping("teachers")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    //get teacher by id
    @GetMapping("teachers/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable(value = "id") int teacher_id)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(teacherService.getTeacherById(teacher_id));
    }

    //save teacher
    @PostMapping("teachers")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    //delete teacher
    @DeleteMapping("teachers/{id}")
    public Map<String, Boolean> deleteTeacher(@PathVariable(value = "id") int teacher_id) throws ResourceNotFoundException {
        teacherService.deleteTeacher(teacher_id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    //update teacher
    @PutMapping("teachers/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable(value = "id") int teacher_id,
                                                 @RequestBody Teacher teacherDetail) throws ResourceNotFoundException {
        return ResponseEntity.ok(teacherService.updateTeacher(teacher_id, teacherDetail));
    }

    //get all courses that are currently taught by teacher
    @GetMapping("teachers/{id}/all-courses")
    public ResponseEntity<Collection<Course>> getTeachingCourse(@PathVariable(value = "id") int teacher_id) throws ResourceNotFoundException {

        return ResponseEntity.ok().body(teacherService.getAllTeachingCourses(teacher_id));
    }

    //create new course and assign to current teacher
    @PostMapping("teachers/{id}/add-course")
    public Course createNewCourse(@PathVariable(value = "id") int teacher_id, @RequestBody Course course)
            throws ResourceNotFoundException {
        Teacher teacher = teacherService.getTeacherById(teacher_id);
        teacher.getCourses().add(course);
        course.setTeacher(teacher);
        return course;
    }

    //summarize student mark in course
    @GetMapping("teachers/courses/{course_id}/summary")
    public ResponseEntity<List<Map<String, Object>>> summarizeStudentMark(@PathVariable(value = "course_id") int courseId) throws ResourceNotFoundException {
        Course course = courseService.getCourseById(courseId);
        List<Map<String, Object>> list = new ArrayList<>();
        Collection<Enrollment> enrollmentCollection = course.getEnrollments();
        for (Enrollment enrollment : enrollmentCollection) {
            Map<String,Object> response = new HashMap<>();
            Student student = enrollment.getStudent();
            response.put("nickname", student.getNickname());
            response.put("id", student.getStudentId());
            response.put("homeworkList", enrollment.getHomeworks());
            list.add(response);
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("students/get_timetable/{teacher_id}")
    public ResponseEntity<List<Map<String, Object>>> getTeacherTimetable(@PathVariable(value = "teacher_id") int accountId)
            throws ResourceNotFoundException {
        Collection<Course> courses = teacherService.getAllTeachingCourses(accountId);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Course c : courses) {
            Map<String, Object> response = new HashMap<>();
            response.put("courseTitle", c.getTitle());
            response.put("courseId", c.getId());
            response.put("courseDescription", c.getDescription());
            response.put("courseCode", c.getCourseCode());
            list.add(response);
        }
        return ResponseEntity.ok().body(list);
    }

    //get the teacher's timetable
//    @GetMapping("teachers/get_timetable/{teacher_id}")
//    public JSONArray getTeacherTimetable(@PathVariable(value = "teacher_id") Integer accountId)
//            throws ResourceNotFoundException {
//        Collection<Course> courses = teacherService.getAllTeachingCourses(accountId);
//        JSONArray jsonArray = new JSONArray();
//
//        for(Course c : courses){ //EXAMPLE: 3 ENROLL -> 3 COURSE
//            JSONObject jsonObjectCourse = new JSONObject();
//            int courseId = c.getId(); // REQUIRE THE COURSE ID
//            Collection<Event> events = courseService.getAllEventByCourseId(courseId);
//
//            for(Event ee : events) {
//                JSONObject jsonObjectEvent = new JSONObject();
//                jsonObjectEvent.put("courseName", c.getTitle());
//                jsonObjectEvent.put("day", ee.getDay());
//                jsonObjectEvent.put("timeZone", ee.getZone());
//                jsonArray.put(jsonObjectEvent);
//            }
//        }
//        return jsonArray;
//    }
}