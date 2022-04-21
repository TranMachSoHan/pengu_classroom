package rmit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rmit.exceptions.ResourceNotFoundException;
import rmit.message.ResponseFile;
import rmit.message.ResponseMessage;
import rmit.models.Homework;
import rmit.models.Submission;
import rmit.repositories.HomeworkRepository;
import rmit.repositories.SubmissionRepository;
import rmit.service.SubmissionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")


public class SubmissionController {
    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private HomeworkRepository homeworkRepository;

    @GetMapping("/submissions")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> submissions = submissionService.getAllSubmission().map(submission -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/v1/submissions/")
                    .path(submission.getId().toString())
                    .toUriString();

            return new ResponseFile(
                    submission.getName(),
                    fileDownloadUri,
                    submission.getType(),
                    submission.getData().length,
                    submission.getHomework());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(submissions);
    }

    @GetMapping("/submissions/{id}")
    public ResponseEntity<byte[]> getSubmissionById(@PathVariable Integer id) {
        Optional<Submission> optionalFileDB = submissionRepository.findById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + optionalFileDB.get().getName() + "\"")
                .body(optionalFileDB.get().getData());
    }

    @PostMapping("homeworks/{id}/upload")
    public ResponseEntity<ResponseMessage> uploadSubmission(@PathVariable(value = "id") int homeworkId, @RequestParam("file") MultipartFile file)
        throws ResourceNotFoundException {
        Homework homework = homeworkRepository.findById(homeworkId)
                .orElseThrow(() -> new ResourceNotFoundException("Homework not found for this id :: " + homeworkId));
        String message = "";
        try {
            submissionService.saveSubmission(file, homework);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

}
