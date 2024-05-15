package CloudComputingD.DBox.dto;

import CloudComputingD.DBox.entity.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class FileInfoResponseDTO {

    @Builder
    @AllArgsConstructor
    @Getter
    static public class Info {
            String name;
            String type;
            Long size;
            LocalDateTime created_date;

        public static FileInfoResponseDTO.Info of(File file) {
            return Info.builder()
                    .name(file.getName())
                    .type(file.getType())
                    .size(file.getSize())
                    .created_date(file.getCreated_date())
                    .build();
        }
    }
}
