package rmit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Enrollment;
import rmit.repositories.EnrollmentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class EnrollmentController {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @GetMapping("enrollments")
    public List<Enrollment> getAllEnrollment() {
        return enrollmentRepository.findAll();
    }

    @GetMapping("enrollments/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable(value = "id") Integer enrollmentId)
            throws ResourceNotFoundException {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found for this id :: " + enrollmentId));
        return ResponseEntity.ok().body(enrollment);
    }

    @PostMapping("enrollments")
    public Enrollment createEnrollment( @RequestBody Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @PutMapping("enrollments/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable(value = "id") Integer enrollmentId,
                                                  @RequestBody Enrollment enrollmentDetails) throws ResourceNotFoundException {
         Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found for this id :: " + enrollmentId));

        enrollment.updateEnrollment(enrollmentDetails);
        return ResponseEntity.ok(this.enrollmentRepository.save(enrollment));
    }

    @DeleteMapping("enrollments/{id}")
    public Map<String, Boolean> deleteEnrollment(@PathVariable(value = "id") Integer enrollmentId)
            throws ResourceNotFoundException {
        Enrollment enrollment  = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found for this id :: " + enrollmentId));

        enrollmentRepository.delete(enrollment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
