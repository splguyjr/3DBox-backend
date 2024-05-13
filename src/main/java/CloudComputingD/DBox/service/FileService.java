package CloudComputingD.DBox.service;

import CloudComputingD.DBox.entity.File;
import CloudComputingD.DBox.repository.FileRepository;
import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.net.http.HttpHeaders;
import java.time.LocalDateTime;

@Service
public class FileService {
    private final FileRepository fileRepository;
    private AmazonS3Client amazonS3Client;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
    @Autowired
    public void setS3Client(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }


    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    /**
     * 파일 업로드
     */
    @Transactional
    public void uploadFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        amazonS3Client.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);

        fileRepository.save(
                File.builder()
                        .name(originalFilename)
                        .type(multipartFile.getContentType())
                        .size((int)multipartFile.getSize())
                        .created_date(LocalDateTime.now())
                        .s3_key(amazonS3Client.getUrl(bucket, originalFilename).toString())
                        .build()
        );
    }

    /**
     * 파일 정보 조회
     */
    @Transactional(readOnly = true)
    public File getFileById(Integer fileId){
        return fileRepository.findById(fileId);
    }

    /**
     * 파일 이름 수정
     */
    @Transactional
    public String renameFile(Integer fileId, String newName) {
        File file = fileRepository.findById(fileId);
        file.setName(newName);
        fileRepository.save(file);
        return newName;
    }

    /**
     * 파일 휴지통 이동
     */
    @Transactional
    public Integer trashFile(Integer fileId) {
        File file = fileRepository.findById(fileId);
        file.setIs_deleted(true);
        file.setDeleted_date(LocalDateTime.now());
        fileRepository.save(file);
        return fileId;
    }

    /**
     * 파일 복원
     */
    @Transactional
    public Integer restoreFile(Integer fileId) {
        File file = fileRepository.findById(fileId);
        file.setIs_deleted(false);
        file.setDeleted_date(null);
        fileRepository.save(file);
        return fileId;
    }

    /**
     * 파일 영구 삭제
     */
    @Transactional
    public Integer deleteFile(Integer fileId) {
        File file = fileRepository.findById(fileId);
        String fileName = file.getName();
        amazonS3Client.deleteObject(bucket, fileName);
        return fileId;
    }

    /**
     * 파일 다운로드
     */

}
