package studio.zealous.smartportfolio.controller.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.zealous.smartportfolio.dto.ApiResponse;
import studio.zealous.smartportfolio.entity.UserTokensMapping;
import studio.zealous.smartportfolio.service.UserTokenMappingService;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserTokensMappingController {

    @Autowired
    UserTokenMappingService userTokenMappingService;

    @PostMapping("/register/apikey")
    public ResponseEntity<ApiResponse> registerApiKey(@RequestBody Map<String, String> requestBody) {
        String angelUserId = requestBody.get("angelUserId");
        String apiKey = requestBody.get("apiKey");
        userTokenMappingService.saveUserApiKey(angelUserId, apiKey);

        ApiResponse apiResponse = new ApiResponse(true, "API key registered successful", null);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

    }

    /*@PostMapping("/register/tokens")
    public ResponseEntity<ApiResponse> saveUserTokens(@RequestBody Map<String, String> requestBody){
        // Extract values from the request body
        String angelUserId = requestBody.get("angelUserId");
        String apiKey = requestBody.get("apiKey");
        String jwtToken = requestBody.get("jwtToken");
        String refreshToken = requestBody.get("refreshToken");

        // Create a new UserTokensMapping entity
        UserTokensMapping userTokensMapping = new UserTokensMapping();
        userTokensMapping.setAngelUserId(angelUserId);
        userTokensMapping.setApiKey(apiKey);
        userTokensMapping.setJwtToken(jwtToken);
        userTokensMapping.setRefreshToken(refreshToken);
        userTokensMapping.setCreatedAt(LocalDateTime.now());
        userTokensMapping.setUpdatedAt(LocalDateTime.now());

        // Save the entity to the database
        userTokensMappingRepository.save(userTokensMapping);

        // Return a successful response
        ApiResponse response = new ApiResponse(true, "User tokens saved successfully", null);
        return ResponseEntity.ok(response);
    }*/
}
