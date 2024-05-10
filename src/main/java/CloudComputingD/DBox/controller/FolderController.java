package CloudComputingD.DBox.controller;


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

}
