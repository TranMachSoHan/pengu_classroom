package rmit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Homework;
import rmit.repositories.HomeworkRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class HomeworkController {
    @Autowired
    private HomeworkRepository homeworkRepository;

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
}
