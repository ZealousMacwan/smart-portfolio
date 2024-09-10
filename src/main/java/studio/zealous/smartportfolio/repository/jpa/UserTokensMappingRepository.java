package studio.zealous.smartportfolio.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import studio.zealous.smartportfolio.entity.User;
import studio.zealous.smartportfolio.entity.UserTokensMapping;

import java.util.Optional;

public interface UserTokensMappingRepository extends JpaRepository<UserTokensMapping, Long> {

    @Query("SELECT u.angelUserId.angelUserId FROM UserTokensMapping u WHERE u.jwtToken = :jwtToken")
    String findAngelUserIdByJwtToken(@Param("jwtToken") String jwtToken);

    @Query("SELECT u.apiKey FROM UserTokensMapping u WHERE u.jwtToken = :jwtToken")
    String findApiKeyByJwtToken(@Param("jwtToken") String jwtToken);

    @Query("SELECT utm FROM UserTokensMapping utm WHERE utm.angelUserId.angelUserId = :angelUserId AND utm.apiKey = :apiKey")
    UserTokensMapping findByAngelUserIdAndApiKey(@Param("angelUserId") String angelUserId, @Param("apiKey") String apiKey);

    @Query("SELECT u.apiKey FROM UserTokensMapping  u where u.angelUserId.angelUserId = :angelUserId")
    String findApiKeyByAngelUserId(@Param("angelUserId") String angelUserId);

    Optional<UserTokensMapping> findByJwtToken(String jwtToken);
}
