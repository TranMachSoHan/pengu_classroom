package rmit.controllers;

import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Course;
import rmit.models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.repositories.TeacherRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    //get teacher
    @GetMapping("teachers")
    public List<Teacher> getAllTeachers(){
        return this.teacherRepository.findAll();
    }

    //get teacher by id
    @GetMapping("teachers/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable(value = "id") int teacher_id)
            throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(teacher_id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + teacher_id));
        return ResponseEntity.ok().body(teacher);
    }

    //save teacher
    @PostMapping("teachers")
    public Teacher createTeacher(@RequestBody Teacher teacher){
        return teacherRepository.save(teacher);
    }

    //delete teacher
    @DeleteMapping("teachers/{id}")
    public Map<String, Boolean> deleteTeacher(@PathVariable(value = "id") int teacher_id) throws ResourceNotFoundException{
        Teacher teacher = teacherRepository.findById(teacher_id)
                .orElseThrow(()-> new ResourceNotFoundException("Teacher not found for this id :: " + teacher_id));
        this.teacherRepository.delete(teacher);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    //update teacher
    @PutMapping("teacher/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable(value = "id") int teacher_id,
                                               @RequestBody Teacher teacherDetail) throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(teacher_id)
                .orElseThrow(()-> new ResourceNotFoundException("Course not found for this id :: " + teacher_id));

        teacher.updateTeacher(teacherDetail);
        return ResponseEntity.ok(this.teacherRepository.save(teacher));
    }
}
