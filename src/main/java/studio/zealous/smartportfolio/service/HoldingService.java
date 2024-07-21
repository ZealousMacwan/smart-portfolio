package studio.zealous.smartportfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.zealous.smartportfolio.entity.holding.Holding;
import studio.zealous.smartportfolio.repository.jpa.HoldingRepository;
import studio.zealous.smartportfolio.util.converter.TotalHoldingConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HoldingService {

    private final HoldingRepository holdingRepository;

    @Autowired
    TotalHoldingConverter totalHoldingConverter;

    @Autowired
    public HoldingService(HoldingRepository holdingRepository) {
        this.holdingRepository = holdingRepository;
    }

    public List<Holding> findAllHoldings() {
        return holdingRepository.findAll();
    }

    public Holding saveHolding(Holding holding) {
        return holdingRepository.save(holding);
    }

    public void deleteHolding(Long holdingId) {
        holdingRepository.deleteById(holdingId);
    }

//    public Long existsHoldings(Long userIdFk, String tradingSymbol, LocalDate date){
//        return holdingRepository.existsByUserAndTradingSymbolAndDate(userIdFk, tradingSymbol, date);
//    }

    public Optional<Holding> findExistingHoldingByUserIdFkTradingSymbolCreatedAt(Long userIdFk, String tradingSymbol, LocalDateTime createdAt){
        return holdingRepository.findByUserAndTradingSymbolAndDate(userIdFk, tradingSymbol, createdAt);
    }

    // Other business logic methods as needed




}

