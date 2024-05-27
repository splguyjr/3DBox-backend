package CloudComputingD.DBox.controller;


import CloudComputingD.DBox.dto.*;
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

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createFolder(
            @RequestBody FolderCreateRequestDTO folderCreateRequestDTO
    ) {
        folderService.createFolder(folderCreateRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("child/file/{folderId}")
    public ResponseEntity<FolderFileResponseDTO> getFiles(
            @PathVariable("folderId") Long folderId
    ) {
        FolderFileResponseDTO folderFileResponseDTO = folderService.getFilesByFolderId(folderId);
        return new ResponseEntity<>(folderFileResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/child/folder/{folderId}")
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

    @DeleteMapping("/delete/{folderId}")
    public ResponseEntity<HttpStatus> deleteFolderPermanently(
            @PathVariable("folderId") Long folderId
    ) {
        folderService.deleteFolderFromTrash(folderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/trash")
    public ResponseEntity<FolderAndFileResponseDTO> getTrashFilesAndFolders(
            @RequestBody TrashRequestDTO trashRequestDTO
    ) {
        FolderAndFileResponseDTO folderAndFileResponseDTO = folderService.getTrashFilesandFolders(trashRequestDTO);
        return new ResponseEntity<>(folderAndFileResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/child/{folderId}")
    public ResponseEntity<FolderAndFileResponseDTO> getFilesAndFolders(
            @PathVariable("folderId") Long folderId
    ) {
        FolderAndFileResponseDTO folderAndFileResponseDTO = folderService.getFilesAndFolders(folderId);

        return new ResponseEntity<>(folderAndFileResponseDTO, HttpStatus.OK);
    }
}
