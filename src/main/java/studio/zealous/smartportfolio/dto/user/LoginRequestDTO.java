package studio.zealous.smartportfolio.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @JsonProperty("clientcode")
    private String clientCode;

    @JsonProperty("password")
    private String password;

    @JsonProperty("totp")
    private String totp;
}
