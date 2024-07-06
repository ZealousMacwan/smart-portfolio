package studio.zealous.smartportfolio.dto.holding;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
/*
* TotalHolding class
* */
public class TotalHolding {
    @JsonProperty("totalholdingvalue")
    private BigDecimal totalHoldingValue;

    @JsonProperty("totalprofitandloss")
    private BigDecimal totalProfitAndLoss;

    @JsonProperty("totalpnlpercentage")
    private BigDecimal totalPnlPercentage;

    @JsonProperty("totalinvvalue")
    private BigDecimal totalInvValue;
}
