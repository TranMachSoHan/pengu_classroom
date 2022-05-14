package rmit.service;

import org.springframework.web.multipart.MultipartFile;
import rmit.models.Account;
import rmit.models.Homework;
import rmit.models.Student;

import java.util.List;

public interface FileServiceImpl {
    String saveSubmission(MultipartFile multipartFile, Homework homework);

    String saveProfilePicture(MultipartFile multipartFile, Account account);

    byte[] downloadFile(String filename);

    String deleteFile(String filename);

    List<String> listAllFiles();
}