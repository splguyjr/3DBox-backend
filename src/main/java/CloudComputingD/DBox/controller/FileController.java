package CloudComputingD.DBox.controller;

import CloudComputingD.DBox.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
public class FileController {
    private FileService fileService;
    @Autowired
    public FileController(FileService memberService) {
        this.fileService = memberService;
    }

    @PostMapping(value = "/file")
    public ResponseEntity<?>uploadFile(
            @RequestPart("file") MultipartFile multipartFile
    ) throws IOException {
        fileService.uploadFile(multipartFile);
        return ResponseEntity.ok().build();
    }
}
