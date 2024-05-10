package CloudComputingD.DBox.service;

import CloudComputingD.DBox.entity.Folder;
import CloudComputingD.DBox.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class FolderService {
    private final FolderRepository folderRepository;

    @Autowired
    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public Folder getFolderInfo(Long folderId) {
        return folderRepository.findById(folderId).orElse(null);
    }

    public void createFolder(String folderName) {
        folderRepository.save(Folder.builder()
                .name(folderName)
                .created_date(LocalDateTime.now())
                .build());
    }

}
