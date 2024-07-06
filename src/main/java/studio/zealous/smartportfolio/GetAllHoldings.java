package studio.zealous.smartportfolio;


import com.angelbroking.smartapi.SmartConnect;
import com.angelbroking.smartapi.http.SessionExpiryHook;
import com.angelbroking.smartapi.http.exceptions.SmartAPIException;
import com.angelbroking.smartapi.models.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import studio.zealous.smartportfolio.config.properties.AngelOneProperties;

import java.io.IOException;

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
        System.out.println(response);
    }
}
