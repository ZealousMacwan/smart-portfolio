package studio.zealous.smartportfolio;


import com.angelbroking.smartapi.SmartConnect;
import com.angelbroking.smartapi.http.exceptions.SmartAPIException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import studio.zealous.smartportfolio.dto.holding.ClientHoldingData;
import studio.zealous.smartportfolio.dto.holding.Holding;

import java.io.IOException;
import java.util.HashMap;

@Component
public class GetAllHoldings {

    @Autowired
    SmartConnect smartConnect;

    /**
     * Get All Holdings
     */
    public void getAllHolding() throws SmartAPIException, IOException {
        // Returns Holdings.
        JSONObject response = smartConnect.getAllHolding();
        ObjectMapper objectMapper = new ObjectMapper();
        ClientHoldingData holdings = objectMapper.readValue(response.get("data").toString(), ClientHoldingData.class);
        System.out.println(response);
    }
}
