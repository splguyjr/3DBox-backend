package CloudComputingD.DBox.controller;


import CloudComputingD.DBox.dto.FolderChildResponseDTO;
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
        FolderFileResponseDTO folderFileResponseDTO = folderService.getFileByFolderId(folderId);
        return new ResponseEntity<>(folderFileResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/child/{folderId}")
    public ResponseEntity<FolderChildResponseDTO> getChildFolders(
            @PathVariable("folderId") Long folderId
    ) {
        FolderChildResponseDTO folderChildResponseDTO = folderService.getFoldersByParentId(folderId);

        return new ResponseEntity<>(folderChildResponseDTO, HttpStatus.OK);
    }

    @PatchMapping("/{folderId}/name/{folderName}")
    public ResponseEntity<HttpStatus> changeFolderName(
            @PathVariable("folderId") Long folderId,
            @PathVariable("folderName") String folderName
    ) {
        folderService.changeFolderName(folderId, folderName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/trash/{folderId}")
    public ResponseEntity<HttpStatus> moveFolderToTrash(
            @PathVariable("folderId") Long folderId
    ) {
        folderService.moveFolderToTrash(folderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/restore/{folderId}")
    public ResponseEntity<HttpStatus> restoreFolderFromTrash(
            @PathVariable("folderId") Long folderId
    ) {
        folderService.restoreFolderFromTrash(folderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
