package rmit.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Course;
import rmit.models.Enrollment;
import rmit.models.Homework;
import rmit.service.CourseService;
import rmit.service.HomeworkService;

import java.util.*;

@RestController
@RequestMapping("/api/v1/")
public class HomeworkController {
    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private CourseService courseService;

    @GetMapping("homeworks")
    public List<Homework> getAllHomework(){
        return homeworkService.getAllHomeworks();
    }

    @GetMapping("homeworks/{id}")
    public ResponseEntity<Homework> getHomeworkById(@PathVariable(value = "id") int homeworkId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(homeworkService.getHomeworkById(homeworkId));
    }

    @PostMapping("homeworks")
    public Homework createHomework(@RequestBody Homework homework){
        return homeworkService.createHomework(homework);
    }

    @PutMapping("homeworks/{id}")
    public ResponseEntity<Homework> updateHomework(@PathVariable(value = "id") int homeworkId,
                                                   @RequestBody Homework homeworkDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(homeworkService.updateHomework(homeworkId,homeworkDetails));
    }

    @DeleteMapping("homeworks/{id}")
    public Map<String, Boolean> deleteHomework(@PathVariable(value = "id") int homeworkId) throws ResourceNotFoundException{
        homeworkService.deleteHomework(homeworkId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    //publish graded homework
    @PostMapping("homeworks/{id}/mark")
    public Homework publishGradedHomework(@PathVariable(value="id") int homeworkId, float mark) throws ResourceNotFoundException {
        return homeworkService.markHomework(homeworkId, mark);
    }

    //edit description of homework
    @PutMapping("homeworks/{id}/description")
    public ResponseEntity<Homework> updateHomeworkDescription(@PathVariable(value = "id") int homeworkId,
                                                              @RequestBody String newDescription) throws ResourceNotFoundException {
        return ResponseEntity.ok(this.homeworkService.editDescriptionHomework(homeworkId, newDescription));
    }

    //create homework then assign it to all enrollments in course
    @PostMapping("teachers/courses/{id}/add-homework")
    public Course createNewHomework(@RequestBody JsonNode homeworkJson, @PathVariable(value = "id") int courseId)
            throws ResourceNotFoundException {
        Course course = courseService.getCourseById(courseId);
        Collection<Enrollment> enrollmentCollection = course.getEnrollments();
        for(Enrollment enrollment : enrollmentCollection) {
            Homework homework = new ObjectMapper().convertValue(homeworkJson, Homework.class);
            homework.setEnrollment(enrollment);
            enrollment.getHomeworks().add(homework);
            homeworkService.createHomework(homework);
        }
        return course;
    }

}