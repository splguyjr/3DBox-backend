package CloudComputingD.DBox.controller;


import CloudComputingD.DBox.dto.FolderFileResponseDTO;
import CloudComputingD.DBox.entity.File;
import CloudComputingD.DBox.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/folder")
@RestController
public class FolderController {

    private final FolderService folderService;

    @PostMapping("/create/{folderName}")
    public ResponseEntity<HttpStatus> createFolder(
            @PathVariable("folderName") String folderName
    ) {
        folderService.createFolder(folderName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{folderId}")
    public ResponseEntity<List<FolderFileResponseDTO>> getFiles(
            @PathVariable("folderId") Long folderId
    ) {
        List<FolderFileResponseDTO> fileResponseDTOs = folderService.getFileByFolderId(folderId);

        return new ResponseEntity<>(fileResponseDTOs, HttpStatus.OK);
    }
}
