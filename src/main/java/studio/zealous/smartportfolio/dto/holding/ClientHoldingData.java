package studio.zealous.smartportfolio.dto.holding;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class ClientHoldingData {
    @JsonProperty("totalholding")
    private TotalHolding totalHolding;

    @JsonProperty("holdings")
    private List<Holding> holdings;
}
