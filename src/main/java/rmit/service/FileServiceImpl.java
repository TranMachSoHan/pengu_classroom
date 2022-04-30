package rmit.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileServiceImpl {
    String saveFile(MultipartFile multipartFile);
    byte[] downloadFile(String filename);
    String deleteFile(String filename);
    List<String> listAllFiles();
}
