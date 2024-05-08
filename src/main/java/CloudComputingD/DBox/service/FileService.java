package CloudComputingD.DBox.service;

import CloudComputingD.DBox.domain.File;
import CloudComputingD.DBox.repository.FileRepository;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FileService {
    private final FileRepository fileRepository = new FileRepository();

//    private final AmazonS3 amazonS3;
//
//
//    /**
//     * 파일 업로드
//     */
//    public void uploadFile(MultipartFile multipartFile) throws IOException {
//        String originalFilename = multipartFile.getOriginalFilename();
//
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentLength(multipartFile.getSize());
//        metadata.setContentType(multipartFile.getContentType());
//
//        amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
//        return amazonS3.getUrl(bucket, originalFilename).toString();
//    }

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
