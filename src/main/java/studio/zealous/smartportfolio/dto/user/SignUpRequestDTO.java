package studio.zealous.smartportfolio.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SignUpRequestDTO {

    @JsonProperty("angelUserId")
    private String angelUserId;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("emailId")
    private String emailId;

    @JsonProperty("password")
    private String password;

}
