package CloudComputingD.DBox.repository;

import CloudComputingD.DBox.entity.User;
import CloudComputingD.DBox.entity.embeddable.OauthId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByOauthId(OauthId oauthId);
}