package rmit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Enrollment;
import rmit.models.Student;
import rmit.repositories.StudentRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int accountId) throws ResourceNotFoundException {
        return studentRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + accountId));
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Collection<Enrollment> getEnrollmentByStudent(Integer studentId) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + studentId));
        return student.getEnrollments();
    }

    public Student updateStudent(Integer studentId, Student student) throws ResourceNotFoundException {
        Student account = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));
        account.updateStudent(student);
        final Student updatedAccount = studentRepository.save(account);
        return updatedAccount;
    }

    public void deleteStudent(Integer studentId)
            throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

        studentRepository.delete(student);
    }
}
