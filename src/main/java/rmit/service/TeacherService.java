package rmit.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Course;
import rmit.models.Student;
import rmit.models.Teacher;
import rmit.repositories.StudentRepository;
import rmit.repositories.TeacherRepository;

import java.util.Collection;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Transactional
    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }

    @Transactional
    public Teacher getTeacherById(int accountId) throws ResourceNotFoundException {
        return teacherRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + accountId));
    }

    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Integer accountId, Teacher teacher) throws ResourceNotFoundException {
        Teacher account = teacherRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + accountId));
        account.updateAccount(teacher);
        final Teacher updatedAccount = teacherRepository.save(account);
        return updatedAccount;
    }

    public void deleteTeacher(Integer accountId)
            throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + accountId));

        teacherRepository.delete(teacher);
    }

    public Collection<Course> getAllTeachingCourses(Integer teacherId) throws ResourceNotFoundException{
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + teacherId));
        return teacher.getCourses();
    }
}
