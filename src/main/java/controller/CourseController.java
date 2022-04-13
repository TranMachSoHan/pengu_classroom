package controller;

import exception.ResourceNotFoundException;
import model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import repository.CourseRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    //get courses
    @GetMapping("courses")
    public List<Course> getAllCourses(){
        return this.courseRepository.findAll();
    }

    //get course by id
    @GetMapping("courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable(value = "id") int course_id)
            throws ResourceNotFoundException {
        Course employee = courseRepository.findById(course_id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + course_id));
        return ResponseEntity.ok().body(employee);
    }

    //save course
    @PostMapping("courses")
    public Course createCourse(@RequestBody Course course){
        return courseRepository.save(course);
    }

    //update course
    @PutMapping("courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable(value = "id") int course_id,
        @RequestBody Course courseDetails) throws ResourceNotFoundException {
        Course course = courseRepository.findById(course_id)
                .orElseThrow(()-> new ResourceNotFoundException("Course not found for this id :: " + course_id));

        course.updateCourse(courseDetails);
        return ResponseEntity.ok(this.courseRepository.save(course));
    }

    //delete course
    @DeleteMapping("courses/{id}")
    public Map<String, Boolean> deleteCourse(@PathVariable(value = "id") int course_id) throws ResourceNotFoundException{
        Course course = courseRepository.findById(course_id)
                .orElseThrow(()-> new ResourceNotFoundException("Course not found for this id :: " + course_id));
        this.courseRepository.delete(course);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
