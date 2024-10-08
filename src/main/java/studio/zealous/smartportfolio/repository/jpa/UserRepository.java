package studio.zealous.smartportfolio.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import studio.zealous.smartportfolio.entity.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAngelUserId(String userId);
    Optional<User> findById(Long id);

    @Query(value = "SELECT id FROM user WHERE angel_user_id = :angelUserId", nativeQuery = true)
    Long getIdByAngelUserIdNative(@Param("angelUserId") String angelUserId);

}
