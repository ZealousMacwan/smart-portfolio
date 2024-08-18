package studio.zealous.smartportfolio.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class ApiRequestHelper {

    private final RestTemplate restTemplate;

    public ApiRequestHelper(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> makeRequest(
            String url,
            HttpMethod method,
            String jwtToken,
            Map<String, String> additionalHeaders,
            Object body) {

        HttpHeaders headers = createHeaders(jwtToken, additionalHeaders);
        HttpEntity<Object> requestEntity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(url, method, requestEntity, String.class);
    }

    private HttpHeaders createHeaders(String jwtToken, Map<String, String> additionalHeaders) {
        HttpHeaders headers = new HttpHeaders();

        // Common headers
        headers.set("Authorization", "Bearer " + jwtToken);
        headers.set("Content-Type", "application/json");
        headers.set("Accept", "application/json");

        // Add additional headers if provided
        if (additionalHeaders != null) {
            additionalHeaders.forEach(headers::set);
        }

        return headers;
    }
}
