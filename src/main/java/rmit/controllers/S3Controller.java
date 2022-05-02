package rmit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rmit.service.S3Service;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class S3Controller {
//    @Autowired
//    private S3Service s3Service;
//
//    @PostMapping("upload")
//    public String upload(@RequestParam("file")MultipartFile multipartFile) {
//        return s3Service.saveFile(multipartFile);
//    }
//
//    @GetMapping("download/{filename}")
//    public ResponseEntity<byte[]> download(@PathVariable("filename") String filename) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-type", MediaType.ALL_VALUE);
//        headers.add("Content-disposition", "attachment: filename="+filename);
//        byte[] bytes = s3Service.downloadFile(filename);
//        return ResponseEntity.ok().headers(headers).body(bytes);
//    }
//
//    @DeleteMapping("{filename}")
//    public String deleteFile(@PathVariable("filename") String filename) {
//        return s3Service.deleteFile(filename);
//    }
//
//    @GetMapping("allFiles")
//    public List<String> getAllFiles() {
//        return s3Service.listAllFiles();
//    }
//




}
