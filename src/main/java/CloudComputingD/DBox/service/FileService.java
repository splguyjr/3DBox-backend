package CloudComputingD.DBox.service;

import CloudComputingD.DBox.entity.File;
import CloudComputingD.DBox.repository.FileRepository;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository = new FileRepository();

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    /**
     * 파일 업로드
     */
    public void uploadFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        amazonS3Client.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);

        fileRepository.save(
                File.builder()
                        .name(multipartFile.getOriginalFilename())
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
    public void renameFile(Integer fileId, String newName) {
        File file = fileRepository.findById(fileId);
        file.setName(newName);
        fileRepository.save(file);
    }

    /**
     * 파일 휴지통 이동
     */
    @Transactional
    public void trashFile(Integer fileId) {
        File file = fileRepository.findById(fileId);
        file.setIs_deleted(true);
        file.setDeleted_date(LocalDateTime.now());
        fileRepository.save(file);
    }

    /**
     * 파일 복원
     */
    @Transactional
    public void restoreFile(Integer fileId) {
        File file = fileRepository.findById(fileId);
        file.setIs_deleted(false);
        file.setDeleted_date(null);
        fileRepository.save(file);
    }
}
