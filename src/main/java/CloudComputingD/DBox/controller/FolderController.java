package CloudComputingD.DBox.controller;


import CloudComputingD.DBox.dto.FolderFileResponseDTO;
import CloudComputingD.DBox.entity.File;
import CloudComputingD.DBox.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<FolderFileResponseDTO> getFiles(
            @PathVariable("folderId") Long folderId
    ) {
        FolderFileResponseDTO fileResponseDTOs = folderService.getFileByFolderId(folderId);
        return new ResponseEntity<>(fileResponseDTOs, HttpStatus.OK);
    }
}
