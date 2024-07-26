package studio.zealous.smartportfolio;


import com.angelbroking.smartapi.SmartConnect;
import com.angelbroking.smartapi.http.exceptions.SmartAPIException;
import com.angelbroking.smartapi.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import studio.zealous.smartportfolio.config.properties.AngelOneProperties;
import studio.zealous.smartportfolio.dto.holding.ClientHoldingData;
import studio.zealous.smartportfolio.entity.holding.Holding;
import studio.zealous.smartportfolio.entity.holding.TotalHolding;
import studio.zealous.smartportfolio.service.HoldingService;
import studio.zealous.smartportfolio.service.TotalHoldingService;
import studio.zealous.smartportfolio.service.UserService;
import studio.zealous.smartportfolio.util.converter.HoldingConverter;
import studio.zealous.smartportfolio.util.converter.TotalHoldingConverter;
import studio.zealous.smartportfolio.util.time.TimeUtil;


import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
@Slf4j
public class GetAllHoldings {

    @Autowired
    SmartConnect smartConnect;

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

    /**
     * Get All Holdings
     */
    public void getAllHolding() throws SmartAPIException, IOException {
        // Returns Holdings.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter TOTP code: ");
        Integer totp = scanner.nextInt();
        User user = smartConnect.generateSession(angelOneProperties.getClientcode(), angelOneProperties.getClientpassword(), String.valueOf(totp));
        smartConnect.setAccessToken(user.getAccessToken());
        smartConnect.setUserId(user.getUserId());

        JSONObject response = smartConnect.getAllHolding();
        System.out.println(response);
        System.out.println(user);
        System.out.println("User id:"+user.getUserId());


        ObjectMapper objectMapper = new ObjectMapper();
        ClientHoldingData clientHoldingData = objectMapper.readValue(response.get("data").toString(), ClientHoldingData.class);

        studio.zealous.smartportfolio.entity.User smartPortfolioUser = userService.getUserByAngelUserId(user.getUserId());


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

    }
}
