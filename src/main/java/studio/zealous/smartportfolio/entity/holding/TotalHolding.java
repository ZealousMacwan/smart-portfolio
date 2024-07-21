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
@Table(name = "total_holdings")
public class TotalHolding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id_fk", referencedColumnName = "id")
    private User user;

    private BigDecimal totalHoldingValue;
    private BigDecimal totalProfitAndLoss;
    private BigDecimal totalPnlPercentage;
    private BigDecimal totalInvValue;
    private LocalDateTime createdAt;
}
