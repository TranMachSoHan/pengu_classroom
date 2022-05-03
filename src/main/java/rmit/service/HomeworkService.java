package rmit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Homework;
import rmit.repositories.HomeworkRepository;

import java.util.List;

@Service
public class HomeworkService {
    @Autowired
    private HomeworkRepository homeworkRepository;

    public List<Homework> getAllHomeworks(){
        return this.homeworkRepository.findAll();
    }

    public Homework getHomeworkById(int homework_id)
            throws ResourceNotFoundException {
        Homework homework = homeworkRepository.findById(homework_id)
                .orElseThrow(() -> new ResourceNotFoundException("Homework not found for this id :: " + homework_id));
        return homework;
    }

    public Homework createHomework(Homework homework){
        return homeworkRepository.save(homework);
    }

    public Homework updateHomework(int homework_id,Homework homeworkDetails) throws ResourceNotFoundException {
        Homework homework = homeworkRepository.findById(homework_id)
                .orElseThrow(()-> new ResourceNotFoundException("Homework not found for this id :: " + homework_id));
        homework.updateHomework(homeworkDetails);
        return homeworkRepository.save(homework);
    }

    public Homework markHomework(int homework_id, float mark) throws ResourceNotFoundException {
        Homework homework = homeworkRepository.findById(homework_id)
                .orElseThrow(() -> new ResourceNotFoundException("Homework not found for this id :: " + homework_id));
        homework.setMark(mark);
        return homeworkRepository.save(homework);
    }

    public Homework editDescriptionHomework(int homework_id, String description) throws ResourceNotFoundException {
        Homework homework = homeworkRepository.findById(homework_id)
                .orElseThrow(() -> new ResourceNotFoundException("Homework not found for this id :: " + homework_id));
        homework.setDescription(description);
        return homeworkRepository.save(homework);
    }

    public void deleteHomework(int homework_id) throws ResourceNotFoundException{
        Homework homework = homeworkRepository.findById(homework_id)
                .orElseThrow(()-> new ResourceNotFoundException("Homework not found for this id :: " + homework_id));
        this.homeworkRepository.delete(homework);
    }
}