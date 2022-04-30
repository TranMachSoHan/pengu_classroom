package rmit.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.ui.Model;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.repositories.CourseRepository;
import rmit.repositories.HomeworkRepository;
import rmit.repositories.TeacherRepository;
import rmit.service.CourseService;
import rmit.service.TeacherService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("teachers/{id}/courses")
    public ResponseEntity<Collection<Course>> getTeachingCourse(@PathVariable(value = "id") int teacher_id) throws ResourceNotFoundException {

        return ResponseEntity.ok().body(teacherService.getAllTeachingCourses(teacher_id));
    }

    //create new course and assign to current teacher
    @PostMapping("teachers/{id}/courses/add_course")
    public Course createNewCourse(@PathVariable(value = "id") int teacher_id, @RequestBody Course course)
            throws ResourceNotFoundException {
        Teacher teacher = teacherService.getTeacherById(teacher_id);
        teacher.getCourses().add(course);
        course.setTeacher(teacher);
        return course;
    }

    //summarize student mark in course
    @GetMapping("teachers/courses/{course_id}/summary")
    public JSONArray summarizeStudentMark(@PathVariable(value = "course_id") int courseId) throws ResourceNotFoundException {
        Course course = courseService.getCourseById(courseId);
        JSONArray jsonArray = new JSONArray();
        Collection<Enrollment> enrollmentCollection = course.getEnrollments();
        for (Enrollment enrollment : enrollmentCollection) {
            Student student = enrollment.getStudent();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("nickname", student.getNickname());
            jsonObject.put("id", student.getId());
            jsonObject.put("homeworkList", enrollment.getHomework());
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    @GetMapping("teachers/get_timetable/{teacher_id}")
    public JSONArray getTeacherTimetable(@PathVariable(value = "id") Integer accountId)
            throws ResourceNotFoundException {
        Collection<Course> courses = teacherService.getAllTeachingCourses(accountId);
        JSONArray jsonArray = new JSONArray();
        for(Course c : courses){ //EXAMPLE: 3 ENROLL -> 3 COURSE
            int courseId = c.getId(); // REQUIRE THE COURSE ID
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
