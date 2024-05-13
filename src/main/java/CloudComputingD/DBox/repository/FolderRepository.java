package CloudComputingD.DBox.repository;

import CloudComputingD.DBox.entity.File;
import CloudComputingD.DBox.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder,Long> {
    @Query("SELECT b FROM Folder a JOIN a.files b where a.id = :folderId")
    List<File> findByFolderId(@Param("folderId") Long folderId);

    @Query("SELECT f FROM Folder f WHERE f.parent_id = :folderId")
    List<Folder> findAllByParent_id(@Param("folderId") Long parentId);
}
