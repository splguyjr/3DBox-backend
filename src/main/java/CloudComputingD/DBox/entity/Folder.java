package CloudComputingD.DBox.entity;

import jakarta.persistence.*;
import jakarta.validation.groups.Default;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "folder_id")
    @ToString.Exclude
    private List<File> files = new ArrayList<>();

    @Column
    private Long user_id;

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
    private Boolean is_deleted = false;


}
