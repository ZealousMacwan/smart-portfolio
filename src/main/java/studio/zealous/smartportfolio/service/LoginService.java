package studio.zealous.smartportfolio.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import studio.zealous.smartportfolio.config.properties.AngelOneProperties;
import studio.zealous.smartportfolio.dto.user.LoginRequestDTO;
import studio.zealous.smartportfolio.entity.UserTokensMapping;

@Service
public class LoginService {

    @Autowired
    AngelOneProperties angelOneProperties;

    @Autowired
    UserTokenMappingService userTokenMappingService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final RestTemplate restTemplate = new RestTemplate();

    public ObjectNode login(LoginRequestDTO loginRequestDTO) {
 /*       SmartConnect smartConnect = new SmartConnect();
        smartConnect.setApiKey(angelOneProperties.getTradingapikey());
        User user = smartConnect.generateSession(loginRequestDTO.getClientCode(), loginRequestDTO.getPassword(), loginRequestDTO.getTotp());

        // Creating JSON response using ObjectNode
        ObjectNode dataPayload = objectMapper.createObjectNode();
        dataPayload.put("jwtToken", user.getAccessToken());
        dataPayload.put("refreshToken", user.getRefreshToken());
        dataPayload.put("feedToken", user.getFeedToken());

        ObjectNode apiResponse = objectMapper.createObjectNode();
        apiResponse.put("status", true);
        apiResponse.put("message", "SUCCESS");
        apiResponse.put("errorcode", "");
        apiResponse.set("data", dataPayload);*/

        String apiKey = userTokenMappingService.userTokensMappingRepository.findApiKeyByAngelUserId(loginRequestDTO.getClientCode());

        // Prepare the request payload
        ObjectNode requestPayload = objectMapper.createObjectNode();
        requestPayload.put("clientcode", loginRequestDTO.getClientCode());
        requestPayload.put("password", loginRequestDTO.getPassword());
        requestPayload.put("totp", loginRequestDTO.getTotp());

        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        headers.setAccept(java.util.Collections.singletonList(org.springframework.http.MediaType.APPLICATION_JSON));
        headers.set("X-UserType", "USER");
        headers.set("X-SourceID", "WEB");
        headers.set("X-ClientLocalIP", "CLIENT_LOCAL_IP");
        headers.set("X-ClientPublicIP", "CLIENT_PUBLIC_IP");
        headers.set("X-MACAddress", "MAC_ADDRESS");
        headers.set("X-PrivateKey", apiKey);

        // Create HttpEntity with headers and payload
        HttpEntity<ObjectNode> requestEntity = new HttpEntity<>(requestPayload, headers);

        // Call the external API
        String url = "https://apiconnect.angelbroking.com/rest/auth/angelbroking/user/v1/loginByPassword";

        ResponseEntity<ObjectNode> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ObjectNode.class);

        // Return response body
        ObjectNode responseBody =  responseEntity.getBody();

        if(responseBody.get("status").asBoolean()){
            String jwtToken = responseBody.get("data").get("jwtToken").textValue();
            String refreshToken = responseBody.get("data").get("refreshToken").textValue();
            userTokenMappingService.saveUserTokens(loginRequestDTO.getClientCode(), apiKey, jwtToken, refreshToken);
        }

        return responseBody;
    }
}
