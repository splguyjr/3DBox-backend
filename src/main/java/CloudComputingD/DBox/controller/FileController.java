package CloudComputingD.DBox.controller;

import CloudComputingD.DBox.entity.File;
import CloudComputingD.DBox.service.FileService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Controller
public class FileController {
    private FileService fileService;
    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/file/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile multipartFile
    ) throws IOException {
        fileService.uploadFile(multipartFile);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/file/{fileId}")
    public ResponseEntity<?> getFileById(
            @PathVariable("fileId") Integer fileId
    ) {
        return ResponseEntity.ok(fileService.getFileById(fileId));
    }

    @PatchMapping(value="/file/{fileId}/name/{fileName}")
    public ResponseEntity<String> renameFile(
            @PathVariable("fileId") Integer fileId,
            @PathVariable("fileName") String newName
    ){
        return ResponseEntity.ok(fileService.renameFile(fileId, newName));
    }

    @PatchMapping(value="/file/trash/{fileId}")
    public ResponseEntity<Integer> trashFile(
            @PathVariable("fileId") Integer fileId
    ) {
        return ResponseEntity.ok(fileService.trashFile(fileId));
    }

    @PatchMapping(value="/file/restore/{fileId}")
    public ResponseEntity<Integer> restoreFile(
            @PathVariable("fileId") Integer fileId
    ) {
        return ResponseEntity.ok(fileService.restoreFile(fileId));
    }

    @DeleteMapping(value="/file/delete/{fileId}")
    public ResponseEntity<Integer> deleteFile(
            @PathVariable("fileId") Integer fileId
    ) {
        return ResponseEntity.ok(fileService.deleteFile(fileId));
    }

    @GetMapping(value="/file/download/{fileId}")
    public ResponseEntity<?> downloadFile(
            @PathVariable("fileId") Integer fileId
    ) throws IOException {
        String fileName = fileService.getFileName(fileId);
        ByteArrayOutputStream body = fileService.downloadFile(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(body.toByteArray());
    }
}
