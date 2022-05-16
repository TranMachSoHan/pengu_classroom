package rmit.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rmit.models.Account;
import rmit.models.Homework;
import rmit.repositories.AccountRepository;
import rmit.repositories.HomeworkRepository;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class S3Service implements FileServiceImpl{
    @Value("${amazon.s3.bucketName}")
    private String bucketName;

    @Value("${amazon.s3.endpoint}")
    private String endPoint;

    @Autowired
    private HomeworkRepository homeworkRepository;

    @Autowired
    private AccountRepository accountRepository;

    private final AmazonS3 s3;

    public S3Service(AmazonS3 s3) {
        this.s3 = s3;
    }

//    @Override
//    public String saveSubmission(MultipartFile multipartFile, Homework homework) {
//        String filename = generateName(multipartFile);
//        String extension = multipartFile.getOriginalFilename().split("\\.")[1];
//        try {
//            File convertedFile = convertMultipartToFile(multipartFile);
//            PutObjectRequest putObjectRequest;
//            if (extension.equals("pdf")) {
//                putObjectRequest =  new PutObjectRequest(bucketName, "submission/" + filename, convertedFile)
//                        .withCannedAcl(CannedAccessControlList.PublicRead);
//            } else {
//                putObjectRequest = new PutObjectRequest(bucketName, "profile-picture/" + filename, convertedFile)
//                        .withCannedAcl(CannedAccessControlList.PublicRead);
//            }
//            s3.putObject(putObjectRequest);
//            return "Successfully upload the file " + filename;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public String saveSubmission(MultipartFile multipartFile, Homework homework) {
        String filename = generateName(multipartFile);
        String fileLink = endPoint + "/submission/" + filename;
        String extension = multipartFile.getOriginalFilename().split("\\.")[1];
        if(!extension.equals("pdf")) return "Invalid submission type. Please upload a pdf file!";
        try {
            File convertedFile = convertMultipartToFile(multipartFile);
            PutObjectRequest putObjectRequest =  new PutObjectRequest(bucketName, "submission/" + filename, convertedFile)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            s3.putObject(putObjectRequest);
            homework.setSubmissionLink(fileLink);
            homework.setSubmissionTime(new Date());
            homework.setIsSubmitted(true);
            homeworkRepository.save(homework);
            return "Successfully upload the submission " + filename;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String saveProfilePicture(MultipartFile multipartFile, Account account) {
        String filename = generateName(multipartFile);
        String fileLink = endPoint + "/profile-picture/" + filename;
        String extension = multipartFile.getOriginalFilename().split("\\.")[1];
        if(!extension.equals("jpg") && !extension.equals("png")) {
            return "Invalid profile picture type. Please upload a jpg or png file!";
        }
        account.setProfile_picture(fileLink);
        accountRepository.save(account);
        try {
            File convertedFile = convertMultipartToFile(multipartFile);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, "profile-picture/" + filename, convertedFile)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            s3.putObject(putObjectRequest);
            return "Successfully upload the profile picture " + filename;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] downloadFile(String filename) {
        S3Object s3object = s3.getObject(bucketName, filename);
        S3ObjectInputStream objectInputStream = s3object.getObjectContent();
        try {
            return IOUtils.toByteArray(objectInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteFile(String filename) {
        s3.deleteObject(bucketName, filename);
        return "File deleted";
    }

    @Override
    public List<String> listAllFiles() {
        ListObjectsV2Result listObjectsV2Result = s3.listObjectsV2(bucketName);
        return  listObjectsV2Result.getObjectSummaries().stream()
                .map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }

    //convert multipart file to file
    private File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        return file;
    }

    //generate file name for each upload
    private String generateName(MultipartFile multipartFile) {
        return new Date().getTime() + "-" + multipartFile.getOriginalFilename().replace(" ","_");
    }

}