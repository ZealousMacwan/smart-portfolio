package studio.zealous.smartportfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.zealous.smartportfolio.entity.holding.Holding;
import studio.zealous.smartportfolio.entity.holding.TotalHolding;
import studio.zealous.smartportfolio.repository.jpa.TotalHoldingRepository;
import studio.zealous.smartportfolio.util.converter.TotalHoldingConverter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TotalHoldingService {

    private final TotalHoldingRepository totalHoldingRepository;

    @Autowired
    TotalHoldingConverter totalHoldingConverter;

    @Autowired
    public TotalHoldingService(TotalHoldingRepository totalHoldingRepository){
        this.totalHoldingRepository = totalHoldingRepository;
    }

    public TotalHolding saveTotalHolding(TotalHolding totalHolding) {
        return totalHoldingRepository.save(totalHolding);
    }

    public Optional<TotalHolding> findExistingTotalHoldingByUserIdFkCreatedAt(Long userIdFk, LocalDateTime createdAt){
        return totalHoldingRepository.findByUserAndDate(userIdFk, createdAt);
    }

    public List<TotalHolding> getTotalHolding(String angelUserId) {
        return totalHoldingRepository.findByAngelUserId(angelUserId);
    }

}
