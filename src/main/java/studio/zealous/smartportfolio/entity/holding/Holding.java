package studio.zealous.smartportfolio.entity.holding;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import studio.zealous.smartportfolio.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "holdings")
public class Holding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // set manually
    @ManyToOne
    @JoinColumn(name = "user_id_fk", referencedColumnName = "id")
    private User user;

    @Column(name = "t1_quantity")
    private Integer t1Quantity;
    private Integer authorisedQuantity;
    private String product;
    private Integer quantity;
    private BigDecimal ltp;
    private BigDecimal haircut;
    private BigDecimal profitAndLoss;
    private String collateralType;
    private BigDecimal averagePrice;
    private String tradingSymbol;
    private BigDecimal pnlPercentage;
    private String exchange;
    private BigDecimal close;
    private String isin;
    private Integer realisedQuantity;
    private String symbolToken;
    private Integer collateralQuantity;

    // set manually
    private LocalDateTime createdAt;
}
