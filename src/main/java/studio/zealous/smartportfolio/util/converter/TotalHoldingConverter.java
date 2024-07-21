package studio.zealous.smartportfolio.util.converter;


import org.springframework.stereotype.Component;
import studio.zealous.smartportfolio.dto.holding.TotalHoldingDto;
import studio.zealous.smartportfolio.entity.holding.TotalHolding;

@Component
public class TotalHoldingConverter {
    public TotalHolding toEntity(TotalHoldingDto dto){
        TotalHolding entity = new TotalHolding();
        entity.setTotalHoldingValue(dto.getTotalHoldingValue());
        entity.setTotalProfitAndLoss(dto.getTotalProfitAndLoss());
        entity.setTotalPnlPercentage(dto.getTotalPnlPercentage());
        entity.setTotalInvValue(dto.getTotalInvValue());
        // Set other entity fields like createdAt if needed
        return entity;
    }
    public TotalHoldingDto toDto(TotalHolding entity){
        TotalHoldingDto dto = new TotalHoldingDto();
        dto.setTotalHoldingValue(entity.getTotalHoldingValue());
        dto.setTotalProfitAndLoss(entity.getTotalProfitAndLoss());
        dto.setTotalPnlPercentage(entity.getTotalPnlPercentage());
        dto.setTotalInvValue(entity.getTotalInvValue());
        return dto;
    }
}
