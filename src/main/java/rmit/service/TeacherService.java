package rmit.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Course;
import rmit.models.Homework;
import rmit.models.Teacher;
import rmit.repositories.HomeworkRepository;
import rmit.repositories.TeacherRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private HomeworkRepository homeworkRepository;

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
        account.updateTeacher(teacher);
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

    public Homework markHomework(int homeworkId, float mark, String feedback) throws ResourceNotFoundException {
        Optional<Homework> homework = homeworkRepository.findById(homeworkId);
        if(homework.isPresent()) {
            Homework existingHomework = homework.get();
            existingHomework.setMark(mark);
            existingHomework.setIsGraded(true);
            existingHomework.setFeedback(feedback);
            return homeworkRepository.save(existingHomework);
        } else {
            throw new ResourceNotFoundException("Homework not found for this id:: " + homeworkId);
        }
    }
}
