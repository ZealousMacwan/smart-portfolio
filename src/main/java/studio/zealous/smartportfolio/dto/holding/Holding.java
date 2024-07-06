package studio.zealous.smartportfolio.dto.holding;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
/*
* Holdings Class
* */
public class Holding {

    @JsonProperty("t1quantity")
    private Integer t1Quantity;

    @JsonProperty("authorisedquantity")
    private Integer authorisedQuantity;

    @JsonProperty("product")
    private String product;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("ltp")
    private BigDecimal ltp;

    @JsonProperty("haircut")
    private BigDecimal haircut;

    @JsonProperty("profitandloss")
    private BigDecimal profitAndLoss;

    @JsonProperty("collateraltype")
    private String collateralType;

    @JsonProperty("averageprice")
    private BigDecimal averagePrice;

    @JsonProperty("tradingsymbol")
    private String tradingSymbol;

    @JsonProperty("pnlpercentage")
    private BigDecimal pnlPercentage;

    @JsonProperty("exchange")
    private String exchange;

    @JsonProperty("close")
    private BigDecimal close;

    @JsonProperty("isin")
    private String isin;

    @JsonProperty("realisedquantity")
    private Integer realisedQuantity;

    @JsonProperty("symboltoken")
    private String symbolToken;

    @JsonProperty("collateralquantity")
    private Integer collateralQuantity;
}
