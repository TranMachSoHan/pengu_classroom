package rmit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Account;
import rmit.models.Homework;
import rmit.service.AccountService;
import rmit.service.HomeworkService;
import rmit.service.S3Service;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class S3Controller {
    @Autowired
    private S3Service s3Service;

    @Autowired
    private HomeworkService homeworkService;

    @Autowired
    private AccountService accountService;

    @PostMapping("students/homeworks/{id}/upload-file")
    public String upload(@RequestParam("file")MultipartFile multipartFile,
                         @PathVariable("id") int homeworkId) throws ResourceNotFoundException {
        Homework homework = homeworkService.getHomeworkById(homeworkId);
        if(homework.getSubmissionLink() != null) return "Already submitted";
        return s3Service.saveSubmission(multipartFile,homework);
    }

    @PostMapping({"students/{id}/upload-profile-picture","teachers/{id}/upload-profile-picture"})
    public String uploadProfilePic(@RequestParam("picture")MultipartFile multipartFile,
                                   @PathVariable("id") int accountId) throws ResourceNotFoundException {
        Account account = accountService.getAccountById(accountId);
        return s3Service.saveProfilePicture(multipartFile,account);
    }

    @GetMapping({"students/download/{filename}","teachers/download/{filename}"})
    public ResponseEntity<byte[]> download(@PathVariable("filename") String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", MediaType.ALL_VALUE);
        headers.add("Content-disposition", "attachment: filename="+filename);
        byte[] bytes = s3Service.downloadFile(filename);
        return ResponseEntity.ok().headers(headers).body(bytes);
    }

    @DeleteMapping("{filename}")
    public String deleteFile(@PathVariable("filename") String filename) {
        return s3Service.deleteFile(filename);
    }

    @GetMapping("allFiles")
    public List<String> getAllFiles() {
        return s3Service.listAllFiles();
    }
}