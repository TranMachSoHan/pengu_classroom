package rmit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Homework;
import rmit.models.Submission;
import rmit.repositories.HomeworkRepository;
import rmit.repositories.SubmissionRepository;

import java.util.stream.Stream;

@Service
public class SubmissionService {
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private HomeworkRepository homeworkRepository;

    public Stream<Submission> getAllSubmission(){
        return submissionRepository.findAll().stream();
    }

    public Submission getSubmissionById(int submissionId) throws ResourceNotFoundException {
        return submissionRepository.findById(submissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + submissionId));
    }

//    public Submission saveSubmission(MultipartFile multipartFile, Homework homework) throws Exception {
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        Submission submission = new Submission(fileName, multipartFile.getContentType(), multipartFile.getBytes(),homework);
//        homework.setSubmission(submission);
//        submission.setHomework(homework);
//        return submissionRepository.save(submission);
//
//
//    }
}
