package studio.zealous.smartportfolio.dto.restresponse.holding;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import studio.zealous.smartportfolio.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
/*
* TotalHolding class
* */
public class TotalHoldingResponseDto {

    @JsonProperty("totalholdingvalue")
    private BigDecimal totalHoldingValue;

    @JsonProperty("totalprofitandloss")
    private BigDecimal totalProfitAndLoss;

    @JsonProperty("totalpnlpercentage")
    private BigDecimal totalPnlPercentage;

    @JsonProperty("totalinvvalue")
    private BigDecimal totalInvValue;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

}
