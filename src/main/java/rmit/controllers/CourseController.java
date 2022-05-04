package rmit.controllers;

import org.json.JSONObject;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.models.Enrollment;
import rmit.models.Student;
import rmit.repositories.CourseRepository;
import rmit.service.CourseService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@EnableSwagger2
@RequestMapping("/api/v1/")
public class CourseController {

    @Autowired
    private CourseService courseService;

    //get courses
    @GetMapping("courses")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    //get course by id
    @GetMapping("courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable(value = "id") int courseId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(courseService.getCourseById(courseId));
    }

    //save course
    @PostMapping("courses")
    public Course createCourse(@RequestBody Course course){
        return courseService.createCourse(course);
    }

    //update course
    @PutMapping("courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable(value = "id") int courseId,
                                               @RequestBody Course courseDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(courseService.updateCourse(courseId,courseDetails));
    }

    //delete course
    @DeleteMapping("courses/{id}")
    public Map<String, Boolean> deleteCourse(@PathVariable(value = "id") int course_id) throws ResourceNotFoundException{
        courseService.deleteCourse(course_id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    //get class code of current course
    @GetMapping("courses/{id}/code")
    public String getCurrentClassCode(@PathVariable(value = "id") int course_id) throws ResourceNotFoundException {
        return courseService.getCourseById(course_id).getCourseCode();
    }

    //view all students of that course
    @GetMapping("courses/{id}/all-students")
    public ResponseEntity<List<Student>> getAllStudentsByCourse(@PathVariable("id") int courseId)
            throws ResourceNotFoundException {
        Course course = courseService.getCourseById(courseId);
        Collection<Enrollment> enrollmentCollection = course.getEnrollments();
        List<Student> studentList = new ArrayList<>();
        for(Enrollment e : enrollmentCollection) {
            studentList.add(e.getStudent());
        }
        return ResponseEntity.ok(studentList);
    }
}