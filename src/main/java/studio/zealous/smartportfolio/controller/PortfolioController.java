package studio.zealous.smartportfolio.controller;

import com.angelbroking.smartapi.http.exceptions.SmartAPIException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studio.zealous.smartportfolio.dto.ApiResponse;
import studio.zealous.smartportfolio.service.PortfolioService;
import studio.zealous.smartportfolio.service.external.AngelBrokingService;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/service/portfolio")
public class PortfolioController {

    @Autowired
    AngelBrokingService angelBrokingService;

    @Autowired
    PortfolioService portfolioService;

    @PostMapping("/fetch")
    public ResponseEntity<ApiResponse> fetchPortfolio(@RequestBody Map<String, String> requestBody) throws SmartAPIException, IOException {
        String jwtToken = requestBody.get("jwtToken");
        String apiKey = requestBody.get("apiKey");
        ApiResponse apiResponse = angelBrokingService.getHoldings(jwtToken, apiKey);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updatePortfolio(@RequestHeader("Authorization") String authHeader, @RequestBody(required = false) Map<String, String> requestBody) throws SmartAPIException, IOException {
        //String jwtToken = requestBody.get("jwtToken");
        String jwtToken = authHeader.replace("Bearer ", "");

        return portfolioService.fetchAndUpdatePortfolio(jwtToken);
    }
}
