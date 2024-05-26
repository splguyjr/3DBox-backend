package CloudComputingD.DBox.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder // 클래스 빌더 패턴으로 JPA 엔티티 객체를 생성한다.
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity // 해당 어노테이션이 적용된 클래스는 JPA가 엔티티로 인식하며 테이블과 링크될 클래스임을 나타낸다.
public class File {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Long size;

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

    @ManyToOne
    @JoinColumn(name = "folder_id")
    @ToString.Exclude
    private Folder folder;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

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
