package CloudComputingD.DBox.entity;

import jakarta.persistence.*;
import jakarta.validation.groups.Default;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "folder", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<File> files = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @Column
    private Long parent_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime created_date;

    @Column
    private LocalDateTime modified_date;

    @Column
    private LocalDateTime deleted_date;

    @Column
    @ColumnDefault("false")
    private Boolean is_deleted;

    @Column
    @ColumnDefault("false")
    private Boolean is_root;

}
