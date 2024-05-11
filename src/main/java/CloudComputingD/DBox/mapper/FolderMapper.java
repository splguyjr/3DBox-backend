package CloudComputingD.DBox.mapper;

import CloudComputingD.DBox.dto.FolderFileResponseDTO;
import CloudComputingD.DBox.entity.File;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FolderMapper {

    public FolderFileResponseDTO fileToFolderFileResponseDTO(File file) {

        Long id = file.getId();
        String name = file.getName();
        String type = file.getType();
        Long size = file.getSize();
        LocalDateTime date = file.getCreated_date();

        return FolderFileResponseDTO.builder()
                .file_id(id)
                .file_name(name)
                .file_type(type)
                .file_size(size)
                .created_date(date)
                .build();
    }
}
