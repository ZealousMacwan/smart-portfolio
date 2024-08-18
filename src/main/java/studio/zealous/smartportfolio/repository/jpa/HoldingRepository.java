package studio.zealous.smartportfolio.repository.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import studio.zealous.smartportfolio.entity.holding.Holding;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


public interface HoldingRepository extends JpaRepository<Holding, Long> {
    //@Query(value = "SELECT COUNT(*) > 0 FROM holdings h WHERE h.user_id_fk = :userIdFk AND h.trading_symbol = :tradingSymbol AND DATE(h.created_at) = :date", nativeQuery = true)
    //boolean existsByUserAndTradingSymbolAndDate(@Param("userIdFk") Long userIdFk, @Param("tradingSymbol") String tradingSymbol, @Param("date") LocalDate date);

//    @Query(value = "SELECT id FROM holdings h WHERE h.user_id_fk = :userIdFk AND h.trading_symbol = :tradingSymbol AND DATE(h.created_at) = :date", nativeQuery = true)
//    Long existsByUserAndTradingSymbolAndDate(@Param("userIdFk") Long userIdFk, @Param("tradingSymbol") String tradingSymbol, @Param("date") LocalDate date);

    @Query("SELECT h FROM Holding h WHERE h.user.id = :userIdFk AND h.tradingSymbol = :tradingSymbol AND DATE(h.createdAt) = DATE(:createdAt)")
    Optional<Holding> findByUserAndTradingSymbolAndDate(@Param("userIdFk") Long userIdFk, @Param("tradingSymbol") String tradingSymbol, @Param("createdAt") LocalDateTime createdAt);
}

