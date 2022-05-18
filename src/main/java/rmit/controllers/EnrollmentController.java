package rmit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Enrollment;
import rmit.service.EnrollmentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("enrollments")
    public List<Enrollment> getAllEnrollment() {
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("enrollments/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable(value = "id") Integer enrollmentId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(enrollmentService.getEnrollmentById(enrollmentId));
    }

    @PostMapping("enrollments")
    public Enrollment createEnrollment( @RequestBody Enrollment enrollment) {
        return enrollmentService.createEnrollment(enrollment);
    }

    @PutMapping("enrollments/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable(value = "id") Integer enrollmentId,
                                                       @RequestBody Enrollment enrollmentDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(this.enrollmentService.updateEnrollment(enrollmentId,enrollmentDetails));
    }

    @DeleteMapping("enrollments/{id}")
    public Map<String, Boolean> deleteEnrollment(@PathVariable(value = "id") Integer enrollmentId)
            throws ResourceNotFoundException {
        enrollmentService.deleteEnrollment(enrollmentId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}