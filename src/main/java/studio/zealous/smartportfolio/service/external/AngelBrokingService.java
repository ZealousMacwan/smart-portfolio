package studio.zealous.smartportfolio.service.external;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import studio.zealous.smartportfolio.dto.ApiResponse;
import studio.zealous.smartportfolio.util.ApiRequestHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AngelBrokingService {
    private static final String HOLDINGS_ENDPOINT = "https://apiconnect.angelbroking.com/rest/secure/angelbroking/portfolio/v1/getAllHolding";

    @Autowired
    private ApiRequestHelper apiRequestHelper;

    @Autowired
    ObjectMapper objectMapper;

    public ApiResponse getHoldings(String jwtToken, String apiKey) {
        Map<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("X-UserType", "USER");
        additionalHeaders.put("X-SourceID", "WEB");
        additionalHeaders.put("X-ClientLocalIP", "CLIENT_LOCAL_IP");
        additionalHeaders.put("X-ClientPublicIP", "CLIENT_PUBLIC_IP");
        additionalHeaders.put("X-MACAddress", "MAC_ADDRESS");

        //get api key for a given uer
        additionalHeaders.put("X-PrivateKey", apiKey);

        // Make the API request to fetch holdings
        ResponseEntity<String> response = apiRequestHelper.makeRequest(HOLDINGS_ENDPOINT, HttpMethod.GET, jwtToken, additionalHeaders, null);

        // Process the response
        try {
            JsonNode responseJson = objectMapper.readTree(response.getBody());
            boolean status = responseJson.get("status").asBoolean();
            String message = responseJson.get("message").asText();
            JsonNode data = responseJson.get("data");

            // Construct and return the ApiResponse
            ApiResponse apiResponse = new ApiResponse(status, message, data);
            return apiResponse;

        } catch (IOException e) {
            // Handle error in parsing response
            ApiResponse apiResponse = new ApiResponse(false, "Error processing response", null);
            return apiResponse;
        }

    }
}
