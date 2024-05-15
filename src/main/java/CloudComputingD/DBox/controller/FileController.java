package CloudComputingD.DBox.controller;

import CloudComputingD.DBox.entity.File;
import CloudComputingD.DBox.service.FileService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@Controller
public class FileController {
    private FileService fileService;
    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/file/upload")
    public ResponseEntity<HttpStatus> uploadFile(
            @RequestParam("file") List<MultipartFile> multipartFiles
    ) throws IOException {
        fileService.uploadFile(multipartFiles);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/file/{fileId}")
    public ResponseEntity<?> getFile(
            @PathVariable("fileId") Long fileId
    ) {
        return new ResponseEntity<>(fileService.getFileById(fileId), HttpStatus.OK);
    }

    @PatchMapping(value="/file/{fileId}/name/{fileName}")
    public ResponseEntity<HttpStatus> renameFile(
            @PathVariable("fileId") Long fileId,
            @PathVariable("fileName") String newName
    ){
        fileService.renameFile(fileId, newName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value="/file/trash/{fileId}")
    public ResponseEntity<HttpStatus> trashFile(
            @PathVariable("fileId") Long fileId
    ) {
        fileService.trashFile(fileId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value="/file/restore/{fileId}")
    public ResponseEntity<HttpStatus> restoreFile(
            @PathVariable("fileId") Long fileId
    ) {
        fileService.restoreFile(fileId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value="/file/delete/{fileId}")
    public ResponseEntity<HttpStatus> deleteFile(
            @PathVariable("fileId") Long fileId
    ) {
        fileService.deleteFile(fileId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value="/file/download/{fileId}")
    public ResponseEntity<?> downloadFile(
            @PathVariable("fileId") Long fileId
    ) throws IOException {
        String fileName = fileService.getFileName(fileId);
        ByteArrayOutputStream body = fileService.downloadFile(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(body.toByteArray());
    }
}
