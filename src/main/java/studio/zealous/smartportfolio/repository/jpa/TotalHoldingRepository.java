package studio.zealous.smartportfolio.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import studio.zealous.smartportfolio.entity.holding.Holding;
import studio.zealous.smartportfolio.entity.holding.TotalHolding;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TotalHoldingRepository extends JpaRepository<TotalHolding, Long> {
    @Query("SELECT t FROM TotalHolding t WHERE t.user.id = :userIdFk AND DATE(t.createdAt) = DATE(:createdAt)")
    Optional<TotalHolding> findByUserAndDate(@Param("userIdFk") Long userIdFk, @Param("createdAt") LocalDateTime createdAt);

    @Query("SELECT th FROM TotalHolding th WHERE th.user.angelUserId = :angelUserId")
    List<TotalHolding> findByAngelUserId(@Param("angelUserId") String angelUserId);
}
