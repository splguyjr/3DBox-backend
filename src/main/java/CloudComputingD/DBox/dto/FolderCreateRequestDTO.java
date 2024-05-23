package CloudComputingD.DBox.dto;

import lombok.Builder;

@Builder
public record FolderCreateRequestDTO(String folderName, Long userId, Long parentId) {
}
