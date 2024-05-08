package CloudComputingD.DBox.entity;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity //
public class File {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Integer file_size;

    @Column
    private String parent_id;

    @Column(nullable = false)
    private LocalDateTime created_date;

    @Column
    private LocalDateTime modified_date;

    @Column
    private LocalDateTime deleted_date;

    @Column
    private Boolean is_deleted;

    @Column(nullable = false)
    private String s3_key;

    @Column
    private BigDecimal location_x;

    @Column
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
