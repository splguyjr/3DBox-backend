package CloudComputingD.DBox.repository;

import CloudComputingD.DBox.entity.File;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

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
