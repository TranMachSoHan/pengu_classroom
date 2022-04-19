package rmit.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Student;
import rmit.models.Teacher;
import rmit.repositories.StudentRepository;
import rmit.repositories.TeacherRepository;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }

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
}
