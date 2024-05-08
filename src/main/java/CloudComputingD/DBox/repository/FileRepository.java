package CloudComputingD.DBox.repository;

import CloudComputingD.DBox.domain.File;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FileRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(File file) {
        em.persist(file);
    }
    public File findById(Integer id) {
        return em.find(File.class, id);
    }
//    public List<File> findByUser(User user);
}
