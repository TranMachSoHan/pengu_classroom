package rmit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Course;
import rmit.models.Enrollment;
import rmit.repositories.EnrollmentRepository;
import rmit.repositories.CourseRepository;
import java.util.List;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Enrollment> getAllEnrollments(){
        return this.enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(int enrollment_id)
            throws ResourceNotFoundException {
        Enrollment enrollment = enrollmentRepository.findById(enrollment_id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found for this id :: " + enrollment_id));
        return enrollment;
    }



    public Enrollment createEnrollment(Enrollment enrollment){
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment updateEnrollment(int enrollment_id,Enrollment enrollmentDetails) throws ResourceNotFoundException {
        Enrollment enrollment = enrollmentRepository.findById(enrollment_id)
                .orElseThrow(()-> new ResourceNotFoundException("Enrollment not found for this id :: " + enrollment_id));
        enrollment.updateEnrollment(enrollmentDetails);
        return enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(int enrollment_id) throws ResourceNotFoundException{
        Enrollment enrollment = enrollmentRepository.findById(enrollment_id)
                .orElseThrow(()-> new ResourceNotFoundException("Enrollment not found for this id :: " + enrollment_id));
        this.enrollmentRepository.delete(enrollment);
    }

}