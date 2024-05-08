package CloudComputingD.DBox.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class File {

    private Integer id;
    private String name;
    private String type;
    private Integer file_size;
    private String parent_id;
    private LocalDateTime created_date;
    private LocalDateTime modified_date;
    private LocalDateTime deleted_date;
    private Boolean is_deleted;
    private String s3_key;
    private BigDecimal location_x;
    private BigDecimal location_y;

    public void setName(String name) {
        this.name = name;
    }

    public void setDeleted_date(LocalDateTime deleted_date) {
        this.deleted_date = deleted_date;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}
