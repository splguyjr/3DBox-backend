package CloudComputingD.DBox.dto;


import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FolderFileResponseDTO {

    private Long file_id;
    private String file_name;
    private String file_type;
    private Long file_size;
    private LocalDateTime created_date;
}
