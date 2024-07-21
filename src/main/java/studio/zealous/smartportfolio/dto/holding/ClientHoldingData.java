package studio.zealous.smartportfolio.dto.holding;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientHoldingData {
    @JsonProperty("totalholding")
    private TotalHoldingDto totalHoldingDto;

    @JsonProperty("holdings")
    private List<HoldingDto> holdingDtos;
}
