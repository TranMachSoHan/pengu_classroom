package rmit.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class S3Service implements FileServiceImpl{
    @Value("${amazon.s3.bucketName}")
    private String bucketName;

    private final AmazonS3 s3;

    public S3Service(AmazonS3 s3) {
        this.s3 = s3;
    }

    @Override
    public String saveFile(MultipartFile multipartFile) {
        String filename = generateName(multipartFile);
        try {
            File convertedFile = convertMultipartToFile(multipartFile);
            PutObjectRequest putObjectRequest =  new PutObjectRequest(bucketName,filename,convertedFile)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            s3.putObject(putObjectRequest);
            return "Successfully upload the file " + filename;
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

//    private MediaType contentType(String filename) {
//        String[] fileArrSplit = filename.split("\\.");
//        String fileExtension = fileArrSplit[fileArrSplit.length - 1];
//        switch (fileExtension) {
//            case "txt":
//                return MediaType.TEXT_PLAIN;
//            case "png":
//                return MediaType.IMAGE_PNG;
//            case "jpg":
//                return MediaType.IMAGE_JPEG;
//            default:
//                return MediaType.APPLICATION_OCTET_STREAM;
//        }
//    }


}
