package CloudComputingD.DBox.service;

import CloudComputingD.DBox.dto.FolderChildResponseDTO;
import CloudComputingD.DBox.dto.FolderFileResponseDTO;
import CloudComputingD.DBox.entity.File;
import CloudComputingD.DBox.entity.Folder;
import CloudComputingD.DBox.mapper.FolderMapper;
import CloudComputingD.DBox.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class FolderService {
    private final FolderRepository folderRepository;
    private final FolderMapper folderMapper;

    @Autowired
    public FolderService(FolderRepository folderRepository, FolderMapper folderMapper) {
        this.folderRepository = folderRepository;
        this.folderMapper = folderMapper;
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

    //folderId를 받아 해당 폴더에 속하는 File들의 list를 받아오고 mapper를 통해 ResponseDto로 변환하여 리턴
    public FolderFileResponseDTO getFileByFolderId(Long FolderId) {
        List<File> files = folderRepository.findFilesByFolderId(FolderId);

        List<FolderFileResponseDTO.FileDTO> responseFiles = files.stream()
                //.map(folderMapper::fileToFolderFileResponseDTO)
                .map(folderMapper::fileToFolderFileResponseDTO)
                .toList();

        return FolderFileResponseDTO.builder()
                .files(responseFiles)
                .build();
        /*
        List<FolderFileResponseDTO> list = new ArrayList<>();
        FolderFileResponseDTO.FileDTO
        for (File f : files) {
            folderMapper.fileToFolderFileResponseDTO(f);
            list.add
                    (fileDTO);
        }
        return list;
        */
    }

    public FolderChildResponseDTO getFoldersByParentId(Long folderId) {
        List<Folder> folders = folderRepository.findAllByParent_id(folderId);

        List<FolderChildResponseDTO.FolderDTO> responseFolders = folders.stream()
                .map(folderMapper::folderToFolderChildResponseDTO)
                .toList();

        return FolderChildResponseDTO.builder()
                .folders(responseFolders)
                .build();
    }

    public void changeFolderName(Long folderId, String folderName) {
        Folder folder = folderRepository.findByFolderId(folderId);
        folder.setName(folderName);
        folderRepository.save(folder);
    }
}

