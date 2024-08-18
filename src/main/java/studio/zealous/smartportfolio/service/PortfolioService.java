package studio.zealous.smartportfolio.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import studio.zealous.smartportfolio.config.properties.AngelOneProperties;
import studio.zealous.smartportfolio.dto.ApiResponse;
import studio.zealous.smartportfolio.dto.holding.ClientHoldingData;
import studio.zealous.smartportfolio.entity.holding.Holding;
import studio.zealous.smartportfolio.entity.holding.TotalHolding;
import studio.zealous.smartportfolio.service.external.AngelBrokingService;
import studio.zealous.smartportfolio.util.JwtUtil;
import studio.zealous.smartportfolio.util.converter.HoldingConverter;
import studio.zealous.smartportfolio.util.converter.TotalHoldingConverter;
import studio.zealous.smartportfolio.util.time.TimeUtil;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PortfolioService {

    @Autowired
    AngelBrokingService angelBrokingService;

    @Autowired
    AngelOneProperties angelOneProperties;

    @Autowired
    HoldingService holdingService;

    @Autowired
    TotalHoldingService totalHoldingService;

    @Autowired
    TotalHoldingConverter totalHoldingConverter;

    @Autowired
    HoldingConverter holdingConverter;

    @Autowired
    UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UserTokenMappingService userTokenMappingService;


    public ResponseEntity<ApiResponse> fetchAndUpdatePortfolio(String jwtToken) throws IOException {
        try {

            String apiKey = userTokenMappingService.getApiKey(jwtToken);

            // Fetch holdings data from Angel Broking service
            ApiResponse apiResponse = angelBrokingService.getHoldings(jwtToken, apiKey);

            // Check if the response is successful
            if (apiResponse.isStatus() && apiResponse.getData() != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode responseBody = objectMapper.readTree(apiResponse.getData().toString());

                ClientHoldingData clientHoldingData = objectMapper.convertValue(responseBody, ClientHoldingData.class);


                /* Logic to fetch user related to toke */
                String userId = userTokenMappingService.getAngelUserId(jwtToken);

                studio.zealous.smartportfolio.entity.User smartPortfolioUser = userService.getUserByAngelUserId(userId);

                TotalHolding totalHolding = totalHoldingConverter.toEntity(clientHoldingData.getTotalHoldingDto());
                List<Holding> holdings = holdingConverter.toListOfEntity(clientHoldingData.getHoldingDtos());
                for(Holding holding: holdings){
                    //Long userId = userService.getIdFromAngelUserId(user.getUserId());
                    Optional<Holding> existingHolding = holdingService.findExistingHoldingByUserIdFkTradingSymbolCreatedAt(smartPortfolioUser.getId(), holding.getTradingSymbol(), TimeUtil.nowWithZone());
                    if(existingHolding.isPresent()){
                        //update existing
                        holding.setId(existingHolding.get().getId());
                        //when we replace current holding it doesn't have user so we are setting explicitly
                        holding.setUser(smartPortfolioUser);
                        log.info("Found existing holding for the day, it will be updated");
                    }else{
                        //save new entry
                        holding.setUser(smartPortfolioUser);
                    }
                    holding.setCreatedAt(TimeUtil.nowWithZone());
                    holdingService.saveHolding(holding);
                }

                Optional<TotalHolding> existingTotalHolding = totalHoldingService.findExistingTotalHoldingByUserIdFkCreatedAt(smartPortfolioUser.getId(), TimeUtil.nowWithZone());
                if(existingTotalHolding.isPresent()){
                    totalHolding.setId(existingTotalHolding.get().getId());
                    totalHolding.setUser(smartPortfolioUser);
                    log.info("Found existing total holding for the day, it will be updated");
                }else{
                    //save new entry
                    totalHolding.setUser(smartPortfolioUser);
                }
                totalHolding.setCreatedAt(TimeUtil.nowWithZone());
                totalHoldingService.saveTotalHolding(totalHolding);

                // Return success response
                return ResponseEntity.ok(new ApiResponse(true, "Holdings updated successfully", null));
            } else {
                // Return failure response
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Failed to fetch holdings data", null));
            }
        } catch (Exception e) {
            log.error("Error processing holdings data", e);
            // Return error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Error processing holdings data", e.getMessage()));
        }

    }
}
