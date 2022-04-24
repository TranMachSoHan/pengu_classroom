package rmit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Course;
import rmit.models.Enrollment;
import rmit.models.Homework;
import rmit.repositories.CourseRepository;
import rmit.repositories.HomeworkRepository;

import java.util.*;

@RestController
@RequestMapping("/api/v1/")
public class HomeworkController {
    @Autowired
    private HomeworkRepository homeworkRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("homeworks")
    public List<Homework> getAllHomework(){
        return this.homeworkRepository.findAll();
    }

    @GetMapping("homeworks/{id}")
    public ResponseEntity<Homework> getHomeworkById(@PathVariable(value = "id") int homeworkId)
            throws ResourceNotFoundException {
        Homework homework = homeworkRepository.findById(homeworkId)
                .orElseThrow(() -> new ResourceNotFoundException("Homework not found for this id :: " + homeworkId));
        return ResponseEntity.ok().body(homework);
    }

    @PostMapping("homeworks")
    public Homework createHomework(@RequestBody Homework homework){
        return homeworkRepository.save(homework);
    }

    @PutMapping("homeworks/{id}")
    public ResponseEntity<Homework> updateHomework(@PathVariable(value = "id") int homeworkId,
                                               @RequestBody Homework homeworkDetails) throws ResourceNotFoundException {
        Homework homework = homeworkRepository.findById(homeworkId)
                .orElseThrow(()-> new ResourceNotFoundException("Course not found for this id :: " + homeworkId));

        homework.updateHomework(homeworkDetails);
        return ResponseEntity.ok(this.homeworkRepository.save(homework));
    }

    @DeleteMapping("homeworks/{id}")
    public Map<String, Boolean> deleteHomework(@PathVariable(value = "id") int homeworkId) throws ResourceNotFoundException{
        Homework homework = homeworkRepository.findById(homeworkId)
                .orElseThrow(()-> new ResourceNotFoundException("Course not found for this id :: " + homeworkId));
        this.homeworkRepository.delete(homework);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    //publish graded homework
    @PostMapping("homeworks/{id}")
    public Homework publishGradedHomework(@PathVariable(value="id") int homeworkId, float mark) throws ResourceNotFoundException {
        Homework homework = homeworkRepository.findById(homeworkId)
                .orElseThrow(()-> new ResourceNotFoundException("Homework not found for this id :: " + homeworkId));
        homework.setMark(mark);
//        this.homeworkRepository.save(homework);
        return homework;
    }

    //edit description of homework
    @PutMapping("homeworks/{id}/description")
    public ResponseEntity<Homework> updateHomework(@PathVariable(value = "id") int homeworkId,
                                                   @RequestBody String newDescription) throws ResourceNotFoundException {
        Homework homework = homeworkRepository.findById(homeworkId)
                .orElseThrow(()-> new ResourceNotFoundException("Homework not found for this id :: " + homeworkId));

        homework.setDescription(newDescription);
        return ResponseEntity.ok(this.homeworkRepository.save(homework));
    }

    //create homework then assign it to all enrollments in course
    @PutMapping("teacher/courses/{id}/add-homework")
    public String createNewHomework(@RequestBody Homework homework, @PathVariable(value = "id") int courseId)
        throws ResourceNotFoundException {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(()-> new ResourceNotFoundException("Course not found for this id :: " + courseId));
        Collection<Enrollment> enrollmentCollection = course.getEnrollments();
        for(Enrollment enrollment : enrollmentCollection) {
            enrollment.getHomework().add(homework);
        }
        homeworkRepository.save(homework);
        return "redirect:/homeworks";
    }



}
